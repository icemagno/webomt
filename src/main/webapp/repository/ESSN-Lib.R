graph_create <- function(a, b) {
  graph <- static.power.law.game(a,b,Inf)
  return(graph)
}

create_list <- function(i,vertexes,edges)
{
  lst <- list()
  for(x in 1:i)
  {
    lst[[x]] <- graph_create(vertexes,edges)
  }
  
  return (lst)
}

# Creates a new Email Network, where i is the number of subnetworks
# vertexes is the number of vertexes of each subnetwork and
# edges is the number of edges of each subnetwork
create_email <- function(i,vertexes,edges)
{
  lst <- create_list(i,vertexes,edges)
  email <- graph_union_centrality(lst)
  email <-graph_add_weights(email)
  email <- graph_metrics(email)
  email$origin <- i
  return (email)
}

create_social <- function(vertexes,edges)
{
  social <- graph_create(vertexes, edges)
  social <- graph_add_weights(social)
  social <- graph_metrics(social)
}

graph_centrality <- function(graph)
{
  graph$centrality <- centralization.degree(graph)$res
  graph$centrality_max <- match(max(graph$centrality),graph$centrality)
  return(graph)
}

graph_union_centrality <- function(lst) {
  union <- NULL
  if(length(lst)>1) {
    for (i in 1:length(lst) ) {
      graph <- lst[[i]]  
      graph <- graph_centrality(graph)
      lst[[i]] <- graph
      if (is.null(union)) 
        union <- graph
      else 
        union <- union + graph
    }
    sumi = 0
    for (i in 1:(length(lst)-1) ) {
      graphi <- lst[[i]]
      sumj = sumi + length(V(graphi))
      for (j in (i+1):length(lst) ) {
        graphj <- lst[[j]]
        union[sumi + graphi$centrality_max , sumj + graphj$centrality_max] <- 1
        sumj = sumj + length(V(graphj))
      }
      sumi = sumi + length(V(graphi))
    }
    
    union$origin <- length(lst)    
    
  }
  else
  {
    union <- lst[[1]]
    union$origin <- 1
  }
  
  return (union)
}

graph_add_weights <- function(graph) {
  graph <- graph_centrality(graph)
  E(graph)$weight <- runif(length(E(graph)), min=0, max=1)
  adj <- get.adjacency(graph, attr="weight", sparse=FALSE)
  for (i in 1:length(graph$centrality) ) {
    adj[i,] <- adj[i,]*graph$centrality[i]
  }
  graph <- graph.adjacency(adj, mode="undirected", weighted=TRUE)
  return(graph)
}

graph_metrics <- function(graph) {
  graph <- graph_centrality(graph)
  graph$matrix <- get.adjacency(graph, attr="weight", sparse=FALSE)
  graph$diameter <- diameter(graph, weights=NA)
  graph$diameter_w <- diameter(graph)
  graph$betweenness <- betweenness(graph, weights=NA)
  graph$closeness <- closeness(graph, weights=NA)
  graph$degree <- degree(graph)
  graph$betweenness_w <- betweenness(graph, directed=FALSE)
  graph$closeness_w <- closeness(graph)
  graph$strength_w <- graph.strength(graph)
  return (graph)
}

matrix_reciprical <- function(matrix) {
  for (i in 1:nrow(matrix)) {
    for (j in 1:ncol(matrix)) {
      if (matrix[i, j] != 0)
        matrix[i,j] = 1.0/matrix[i,j]
    }
  }
  return (matrix)
}

mergegraphs <- function(email.base, social.base) {
 
  result <- matrix(nrow = 0, ncol=8)
  
  for (i in 1:10) {
    memail <- email.base$matrix 
    memail <- memail/max(memail)
    
    msocial <- social.base$matrix
    msocial <- msocial/max(msocial)
    
    msocial <-  (i/10.0) * msocial

    mgeral <- memail + msocial

    memail <- matrix_reciprical(memail)
    msocial <- matrix_reciprical(msocial)
    mgeral <- matrix_reciprical(mgeral)
    
    email <- graph.adjacency(memail, mode="undirected", weighted=TRUE)
    social <- graph.adjacency(msocial, mode="undirected", weighted=TRUE)
    geral <- graph.adjacency(mgeral, mode="undirected", weighted=TRUE)

    email <- graph_metrics(email)
    social <- graph_metrics(social)
    geral <- graph_metrics(geral)

    line <- analyzegraphs(i, email, social, geral, "closeness_w", 
                   data.frame(a=email$closeness_w, b=social$closeness_w, c=geral$closeness_w))
    line[length(line)+1] <- i*10
    result <- rbind(result, line)
    
  }
  return (result)
}

analyzegraphs <- function(i, email, social, geral, label, ds)
{
  s_e <- shapiro.test(email$closeness_w)
  s_s <- shapiro.test(social$closeness_w)
  s_es <- shapiro.test(geral$closeness_w)
  
  we <- wilcox.test(ds$a, ds$c, alternative = "two.sided")
  ws <- wilcox.test(ds$b, ds$c, alternative = "two.sided")
  w_es <- wilcox.test(ds$a, ds$b, alternative = "two.sided")

  mypar <- par(mfrow=c(1, 4))
  
  result <- c(email$origin, length(V(email)),length(E(email)),length(V(social)),length(E(social)),w_es$p.value,we$p.value,ws$p.value)
    
  return (result)
}

runExperiment <- function(label,vertexes,subnet,e_edges,s_edges,n_exp)
{
  result <- matrix(ncol=9,nrow=0)
  data <- matrix(ncol=4,nrow=0)  
  
  for(i in 1:n_exp)
  {
    email <- create_email(subnet,vertexes,e_edges)
    social <- create_social(subnet*vertexes,s_edges)
    comp <- cbind(mergegraphs(email, social),subnet)
    result <- rbind(result,comp)
    subresult <- cbind(result[,8],result[,5],result[,6],result[,7])
  }
  
  data <- rbind(data,subresult)
  
  result_avg <- matrix(ncol=9,nrow=0)
  colnames(result_avg) <- c('Vertices','EG_Edges','SG_Edges','EG_SG_wpv','EG_MG_wpv','SG_MG_wpv','EG_SG_ratio','groups','Exp')
  
  for(i in 1:10)
  {
    val_e <- 0
    val_es <- 0
    val_s <- 0
    
    for(j in 1:nrow(data))
    {
      if(data[j,1]==i*10)
      {
        val_es <- val_es + data[j,2]
        val_e <- val_e + data[j,3]
        val_s <- val_s + data[j,4]
      }
    }
    
    val_es <- val_es/n_exp
    val_e <- val_e/n_exp
    val_s <- val_s/n_exp
    
    result_avg <- rbind(result_avg,c(vertexes*subnet,e_edges*subnet,s_edges,val_es,val_e,val_s,i*10,subnet,label))
  }
  
  mypar <- par(mfrow=c(1, 1))
  
  return(result_avg)
}

plot_avg_curves <- function(data)
{
  mypar <- par(mfrow=c(1, 1))
  
  plot(x=data[,7],y=data[,5],main="EG SG wpv", xlab="percent",ylab="p-value",ylim=c(0,1),xlim=c(0,100))
  abline(h = 0.05)
}

