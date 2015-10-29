#!/usr/bin/env Rscript

inputFileFolder <- paste( sagitariiWorkFolder, "inbox", sep = "/")
outputFileFolder <- paste( sagitariiWorkFolder, "outbox", sep = "/")

inputFile <- paste( sagitariiWorkFolder, "sagi_input.txt", sep = "/")
inputData <- paste( inputFileFolder, "wine.data", sep = "/")
outputFile <- paste( sagitariiWorkFolder, "sagi_output.txt", sep = "/")

param <- read.table( inputFile, header = TRUE, sep = ",")
wine <- read.table( inputData, header = TRUE, sep = ",")

library(nnet)

source("data-preprocessing.R")

	X1 <- wine$X1
	wine$X1 <- NULL
	wine$X1 <- X1

	wine.clean <- remove_outliers(wine)

	wine.norm <- normalize_minmax(wine.clean)

	wine.fss = fss(wine.norm, wine.norm$X1~.)
	wine.fss$X1 = wine.norm$X1

	wine.lasso = lasso(wine.norm, wine.norm$X1~.)
	wine.lasso$X1 = wine.norm$X1 


	x <- wine.lasso

	data <- x[,1:ncol(x)-1]

	alvo <- x[,ncol(x)]
	alvo.class <- class.ind(alvo)

	train <- sample(1:nrow(data),.8*nrow(data))
	test <- -train


	data.train <- data[train,]
	data.test <- data[test,]

	alvo.class.train <- alvo.class[train,]
	alvo.class.test <- alvo.class[test,]

require(nnet)

param$resultado <- NULL
param$filename <- NULL
for (i in 1:nrow(param) ) {
  tnet <- nnet(data.train, alvo.class.train, size=param[i,"size"], decay=param[i,"decay"], maxit=param[i,"maxit"])
  pnet <- predict(tnet, data.test, type="raw")
  roc_data <- croc(pnet, alvo.class.test)
  tx <- unlist(slot(roc_data, "y.values"))
  
  newTx <- gsub(",", ".", tx[2])  
  
  param$resultado[i] <- newTx
  filename <- paste(paste("result-",as.character(i), sep=""), ".csv", sep="")
  fullFileName <-  paste(outputFileFolder, filename, sep="/")
  param$filename[i] <- filename
  write.table(pnet, file=fullFileName, row.names = FALSE, col.names = TRUE, sep = ",", dec = ".", quote = TRUE)
}
param$dataset <- NULL
write.table(param, file=outputFile, row.names = FALSE, col.names = TRUE, sep = ",", dec = ".", quote = FALSE)







