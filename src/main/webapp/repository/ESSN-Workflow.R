#!/usr/bin/env Rscript
#---- Deixar trecho abaixo comentado no Sagitarii
#sagitariiWorkFolder <- "/Users/eogasawara/Dropbox/Eduardo/R/Workflow-ESN"
#libraryFolder <- "/Users/eogasawara/Dropbox/Eduardo/R/Workflow-ESN"
setwd(libraryFolder)


# ----------- SAGITARII REQUIREMENTS ---------------------------------------
inputFileFolder <- paste( sagitariiWorkFolder, "inbox", sep = "/")
outputFileFolder <- paste( sagitariiWorkFolder, "outbox", sep = "/")
inputFile <- paste( sagitariiWorkFolder, "sagi_input.txt", sep = "/")
outputFile <- paste( sagitariiWorkFolder, "sagi_output.txt", sep = "/")
output <- paste( outputFileFolder, "social.", sep = "/")


# ---------------------------------------------------------------------------


library(igraph)
library(poweRlaw)
source("ESSN-Lib.R")

tab <- read.table( inputFile, header = TRUE, sep = ",")
experimento <- tab$experimento[1]
label <- tab$label
vertexes <- tab$vertexes[1]
subnet <- tab$subnet[1]
e_edges <- tab$e_edges[1]
s_edges <- tab$s_edges[1]
n_exp <- tab$n_exp[1]

results <- runExperiment(label,vertexes,subnet,e_edges,s_edges,n_exp)

results = as.data.frame(results)
results$experimento <- experimento

write.csv(results, file=outputFile, row.names = FALSE, quote = FALSE, sep = ",")



