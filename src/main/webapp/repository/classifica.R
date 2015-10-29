#
# FUNÇÕES DA CLASSIFICAÇÃO DE ESTRELAS E GALÁXIAS
#
# Versão 11/09/2015
# REMOCAO DE OUTLIERS
#
remove_outliers <- function(x.train)
{
  q <- as.data.frame(lapply(x.train[-ncol(x.train)], quantile))
  i <- ncol(x.train)-1
  for (h in 1:i)
  {
    IQR <- q[4,h] - q[2,h]
    q1 <- q[2,h] - 3*IQR
    q2 <- q[4,h] + 3*IQR
    cond <- x.train[,h] >= q1 & x.train[,h] <= q2
    x.train <- x.train[cond,]
    return (x.train)
  }
}
#
# NORMALIZACAO MIN-MAX
#
normalize_minmax <- function(x.train, x.test=NULL)
{
  if(is.null(x.test))
  {
    normalize_mm1 <- function(x.train)
    {
      return ((x.train-min(x.train))/(max(x.train)-min(x.train)))
    }
    x.mm <- as.data.frame(lapply(x.train, normalize_mm1))
  }
  else
  {
    normalize_mm2 <- function(x.test)
    {
      vmin <- x.test[length(x.test)-1]
      vmax <- x.test[length(x.test)]
      return ((x.test-vmin)/(vmax-vmin))
    }
    xmin <- apply(x.train, 2, min)
    xmax <- apply(x.train, 2, max)
    x.test <- rbind(x.test, xmin)
    x.test <- rbind(x.test, xmax)
    x.mm <- as.data.frame(lapply(x.test, normalize_mm2))
    temp1 <- nrow(x.mm)
    temp2 <- nrow(x.mm)-1
    x.mm <- x.mm[-temp1,]
    x.mm <- x.mm[-temp2,]
  }
  return (x.mm)
}
#
# NORMALIZACAO Z-SCORE
#
normalize_zscore <- function(x.train, x.test=NULL)
{
  if(is.null(x.test))
  {
    x.zs <- as.data.frame(scale(x.train))
  }
  else
  {
    normalize_zs2 <- function(x.test)
    {
      vmean <- x.test[length(x.test)-1]
      vsd <- x.test[length(x.test)]
      return ((x.test-vmean)/vsd)
    }
    xmean <- apply(x.train, 2, mean)
    xsd <- apply(x.train, 2, sd)
    x.test <- rbind(x.test, xmean)
    x.test <- rbind(x.test, xsd)
    x.zs <- as.data.frame(lapply(x.test, normalize_zs2))
    temp1 <- nrow(x.zs)
    temp2 <- nrow(x.zs)-1
    x.zs <- x.zs[-temp1,]
    x.zs <- x.zs[-temp2,]
  }
  return (x.zs)
}
#
# FORWARD STEPWISE SELECTION
#
fss <- function(x.train, classe)
{
  library(leaps)
  
  selec_col <- function(cols)
  {
    n <- names(cols)
    n1 <- n[-1]
    return (n1)
  }
  regfit.fwd <- regsubsets(classe, x.train, nvmax=ncol(x.train)-1, method="forward")  
  summary(regfit.fwd)
  reg.summaryfwd <- summary(regfit.fwd)
  b1 <- which.max(reg.summaryfwd$adjr2)
  t <- coef(regfit.fwd,b1)
  vec <- selec_col(t)
  return (list(x.train[,vec], vec))
}
#
# LASSO
#
lasso <- function(x.train, classe)
{
  library(glmnet)
  
  m <- model.matrix(classe, x.train)[,-1]  
  n <- x.train[,ncol(x.train)]
  #set.seed(1)
  train <- sample(1:nrow(m),nrow(m)/2)
  test <- (-train)
  grid =10^ seq (10,-2, length =100)
  lasso.mod <- glmnet(m[train,],n[train],alpha=1,lambda=grid)
  cv.out =cv.glmnet (m[train,],n[train],alpha =1)
  bestlam =cv.out$lambda.min
  lasso.pred=predict (lasso.mod ,s=bestlam ,newx=m[test ,])
  out <- glmnet(m,n,alpha=1,lambda=grid)
  lasso.coef=predict (out,type ="coefficients", s=bestlam)
  l <- lasso.coef[(lasso.coef[,1])!=0,0]
  vec <- rownames(l)[-1]
  data <- x.train[,vec]
  data$alvo <- x.train$alvo
  return (list(data, vec))
}
#
# PCA
#
pca <- function(x.train, test = NULL, transf = NULL, varacum=0.2)
{

  if (is.null(test)) {
    xmeu <- x.train[, -ncol(x.train)]
    pcameu <- prcomp(xmeu, center=TRUE, scale.=TRUE)
    cumvar <- cumsum(pcameu$sdev^2/sum(pcameu$sdev^2))
    pcameuindex <- min(which(cumvar > varacum))
    xpca <- array(0, dim=c(nrow(xmeu),pcameuindex))
    transf <- as.matrix(pcameu$rotation[, 1:pcameuindex])
    M <- as.matrix(xmeu)
    alvo <- x.train[, ncol(x.train)]
  }
  else {
    xmeu <- test[, -ncol(test)]
    alvo <- test[, ncol(test)]
    M <- as.matrix(xmeu)
  }
  xpca <- M %*% transf
  xpca_df <- as.data.frame(xpca)
  dataset <- data.frame(xpca_df)
  dataset$alvo <- alvo
  return (list(dataset, transf))
}

#
# REDES NEURAIS COM PARAMETROS FIXOS
#
rn <- function(x.train, x.test)
{
  require(nnet)
  
  data.train <- x.train[,1:ncol(x.train)-1]
  alvo <- x.train[,ncol(x.train)]
  alvo.class <- class.ind(alvo)
  tnet <- nnet(data.train, alvo.class, size=10, decay=5e-4, maxit=200)
  data.test <- x.test[,1:ncol(x.test)-1]
  pnet <- predict(tnet, data.test, type="raw")
  return (pnet)
}
#
# REDES NEURAIS COM ESCOLHA DE PARAMETROS
#
rn2 <- function(x.train, x.test, sz, dc, it)
{
  require(nnet)

  data.train <- x.train[,1:ncol(x.train)-1]
  alvo <- x.train[,ncol(x.train)]
  alvo.class <- class.ind(alvo)
  tnet <- nnet(data.train, alvo.class, size=sz, decay=dc, maxit=it)
  data.test <- x.test[,1:ncol(x.test)-1]
  pnet <- predict(tnet, data.test, type="raw")
  return (pnet)  
}
#
# SVM COM PARAMETROS FIXOS
#
svm <- function(x.train, x.test, alvo)
{
  require(kernlab)
  
  data.test <- x.test[,1:ncol(x.test)-1]
  rbf <- rbfdot(sigma=0.1)
  tsvm <- ksvm(alvo, data=x.train, kernel=rbf, C=10, type="C-bsvc", prob.model=TRUE)
  psvm <- predict(tsvm, data.test, type="probabilities")
  return (psvm)
}
#
# SVM COM ESCOLHA DE PARAMETROS
#
svm2 <- function(x.train, x.test, alvo, kn, c, tp)
{
  require(kernlab)
  
  data.test <- x.test[,1:ncol(x.test)-1]
  #rbfdot <- rbfdot(sigma=0.1)
  tsvm <- ksvm(alvo, data=x.train, kernel=kn, C=c, type=tp, prob.model=TRUE)
  psvm <- predict(tsvm, data.test, type="probabilities")
  return (psvm)
}
#
# SVM COM TIPO DETERMINADO
#
svm4 <- function(x.train, x.test, alvo, kn, c, par)
{
  require(kernlab)
  
  if (c==1) { ct=1 }
  if (c==2) { ct=2/(ncol(x.train)-1) }
  if (c==3) { ct=1/(ncol(x.train)-1) }
  
  data.test <- x.test[,1:ncol(x.test)-1]
  if (kn=="rbfdot")
  {
    tsvm <- ksvm(alvo, data=x.train, kernel=kn, C=ct, type="C-svc", kpar=list(sigma=par), prob.model=TRUE)
  }
  if (kn=="polydot")
  {
    tsvm <- ksvm(alvo, data=x.train, kernel=kn, C=ct, type="C-svc", kpar=list(degree=par), prob.model=TRUE)
  }
  if (kn=="tanhdot")
  {
    tsvm <- ksvm(alvo, data=x.train, kernel=kn, C=ct, type="C-svc", kpar=list(scale=par), prob.model=TRUE)
  }
  psvm <- predict(tsvm, data.test, type="probabilities")
  return (psvm)
}
#
# K NEAREST NEIGHBOURS
#
knear <- function(x.train, x.test, viz)
{
  require(class)
  
  data.train <- x.train[,1:ncol(x.train)-1]
  data.test <- x.test[,1:ncol(x.test)-1]
  alvo <- as.factor(x.train$alvo)
  x.knn1 <- knn(data.train, data.test, alvo, k=viz, prob=TRUE)
  x.pknn <- matrix(0, nrow=length(x.knn1), ncol=2)
  at <- attr(x.knn1,"prob")
  for (k in 1:length(x.knn1))
  {
    if (x.knn1[k] == 0)
    {
      x.pknn[k,1] <- at[k]
      x.pknn[k,2] <- 1 - at[k]
    }
    else if (x.knn1[k] == 1)
    {
      x.pknn[k,1] <- 1 - at[k]
      x.pknn[k,2] <- at[k]
    }
  }
  return (x.pknn)
}
#
# RANDOM FOREST
#
rf <- function(x.train, x.test, nt)
{
  require(randomForest)
  
  data.train <- x.train[,1:ncol(x.train)-1]
  data.test <- x.test[,1:ncol(x.test)-1]
  alvo <- as.factor(x.train$alvo)
  x.trf <- randomForest(data.train, alvo, ntree=nt)
  x.prf <- predict(x.trf, data.test, type="prob")
  return (x.prf)
}
#
# NAIVE BAYES
#
nb <- function(x.train, x.test)
{
  require(e1071)
  
  data.train <- x.train[,1:ncol(x.train)-1]
  data.test <- x.test[,1:ncol(x.test)-1]
  alvo <- as.factor(x.train$alvo)
  x.tnb <- naiveBayes(data.train, alvo, laplace=0)
  x.pnb <- predict(x.tnb, data.test, type="raw")
  return (x.pnb)
}
#
# AVALIA REDES NEURAIS
#
avalia_rn <- function(pred, alvo.teste)
{
  ntrueest <- 0
  ntruegal <- 0
  nfalseest <- 0
  nfalsegal <- 0
  tabela <- round(table(alvo.teste))
  for (i in 1:length(alvo.teste))
  {
    obj <- ifelse(pred[i,1] > 0.9, 0, 1)
    if ((alvo.teste[i] - obj) == 0)
    {
      if (obj == 0)
      {
        ntruegal <- ntruegal + 1
      }
      if (obj == 1)
      {
        ntrueest <- ntrueest + 1
      }
    }
    else
    {
      if (obj == 0)
      {
        nfalsegal <- nfalsegal + 1
      }
      if (obj == 1)
      {
        nfalseest <- nfalseest + 1
      }
    }
  }
  compl_gal <- (ntruegal/tabela[1])*100
  pur_gal <- (ntruegal/(ntruegal+nfalsegal))*100
  compl_est <- (ntrueest/tabela[2])*100
  pur_est <- (ntrueest/(ntrueest+nfalseest))*100
  sprintf("Completeza_galaxias=%.2f Pureza_galaxias=%.2f Completeza_estrelas=%.2f Pureza_estrelas=%.2f", compl_gal, pur_gal, compl_est, pur_est)
  #return (list(compl_gal, pur_gal, compl_est, pur_est))
}
#
# CURVA ROC
#
croc <- function(prob, label)
{
  require(ROCR)
  
  pred <- prediction(prob, label)
  perf <- performance(pred, "tpr", "fpr")
  plot(perf)
  auc <- performance(pred, "auc")
  return (auc)
}
