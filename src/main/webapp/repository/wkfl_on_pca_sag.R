#!/usr/bin/env Rscript
#---- Deixar trecho abaixo comentado no Sagitarii
#sagitariiWorkFolder <- "/Users/eogasawara/Dropbox/Eduardo/R/Star-Galaxy"

# Vers??o 11/09/2015

# ----------- SAGITARII REQUIREMENTS ---------------------------------------
inputFileFolder <- paste( sagitariiWorkFolder, "inbox", sep = "/")
outputFileFolder <- paste( sagitariiWorkFolder, "outbox", sep = "/")
paramFile <- paste( sagitariiWorkFolder, "sagi_input.txt", sep = "/")
outputFile <- paste( sagitariiWorkFolder, "sagi_output.txt", sep = "/")
outpuClassifica <- paste( outputFileFolder, "classifica.csv", sep = "/")
setwd(libraryFolder)

# ---------------------------------------------------------------------------

tab <- read.table( paramFile, header = TRUE, sep = ",")
metodo <- tab$metodo[1]
tamanho <- tab$tamanho[1]
par_r <- tab$par_r[1]
par_i <- tab$par_i[1]
arquivoTreino <- tab$treino[1]
arquivoTeste <- tab$teste[1]

trainFile <- paste( inputFileFolder, arquivoTreino, sep = "/")
load(trainFile)
testFile <- paste( inputFileFolder, arquivoTeste, sep = "/")
load(testFile)

source("classifica.R")

#x.train <- remove_outliers(x.train)

# x.train.raw <- x.train
# x.train <- normalize_minmax(x.train.raw)
# x.test <- normalize_minmax(x.train.raw,x.test)

if (FALSE) { # uso de lasso
  print("lasso")
  set.seed(1)
  x.list <- lasso(x.train, x.train$alvo~.)
  x.train <-x.list[[1]]
  x.train.columns <- x.list[[2]]
  x.test.alvo <- x.test$alvo
  x.test <- x.test[,x.train.columns]
  x.test$alvo <- x.test.alvo
}

if (FALSE) { # uso de pca
  print("pca") 
  set.seed(1)
  x.train.raw <- x.train
  
  if (arquivoTreino=="round1_training_set_r.rdata")
  {
    k=0.6
  } else if (arquivoTreino=="round2_training_set_r.rdata")
  {
    k=0.69
  } else if (arquivoTreino=="round3_training_set_r.rdata")
  {
    k=0.69
  }
  
  x.list <- pca(x.train.raw, varacum=k)
  x.train <-x.list[[1]]
  x.train.transf <- x.list[[2]]
  
  x.list <- pca(x.train.raw, test=x.test, transf = x.train.transf, varacum=k)
  x.test <- x.list[[1]]
  
  x.train.pca.raw <- x.train
  x.train <- normalize_minmax(x.train.pca.raw)
  x.test <- normalize_minmax(x.train.pca.raw,x.test)
}

tab$resultado <- "classifica.csv"
tab$resultadov <- 0

  if (metodo=="rn")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.rn2 <- rn2(cosmos.train, cosmos.test, tamanho, par_r, par_i)
      aa <- croc(x.rn2[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.rn2, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="rbfdot")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.svm4 <- svm4(cosmos.train, cosmos.test, cosmos.train$alvo~., "rbfdot", tamanho, par_r)
      aa <- croc(x.svm4[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.svm4, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="polydot")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.svm4 <- svm4(cosmos.train, cosmos.test, cosmos.train$alvo~., "polydot", tamanho, par_r)
      aa <- croc(x.svm4[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.svm4, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="tanhdot")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.svm4 <- svm4(cosmos.train, cosmos.test, cosmos.train$alvo~., "tanhdot", tamanho, par_r)
      aa <- croc(x.svm4[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.svm4, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="knn")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.knn <- knear(cosmos.train, cosmos.test, tamanho)
      aa <- croc(x.knn[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.knn, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="rf")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.rf <- rf(cosmos.train, cosmos.test, tamanho)
      aa <- croc(x.rf[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.rf, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  } else if (metodo=="nb")
  {
    set.seed(1)
    cosmos_results <- lapply(cosmos.folds, function(x) 
    {
      cosmos.train <- x.train[-x, ]
      cosmos.test <- x.train[x, ]
      cosmos.train <- remove_outliers(cosmos.train)
      x.train.raw <- cosmos.train
      cosmos.train <- normalize_minmax(x.train.raw)
      cosmos.test <- normalize_minmax(x.train.raw,cosmos.test)
      x.nb <- nb(cosmos.train, cosmos.test)
      aa <- croc(x.nb[,2], cosmos.test$alvo)
      aa <- unlist(slot(aa, "y.values"))
    })
    med <- mean(unlist(cosmos_results))
    vrc <- var(unlist(cosmos_results))
    aa <- med - vrc
    tab$resultadov[1] <- aa
    #write.table(x.nb, file=outpuClassifica, row.names=FALSE, quote = FALSE)
  }
  write.table(tab, file=outputFile, row.names = FALSE, quote = FALSE, sep = ",", col.names=TRUE)
