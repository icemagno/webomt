#
# REMOCAO DE OUTLIERS
#
remove_outliers <- function(x1)
{
  q <- as.data.frame(lapply(x1[-ncol(x1)], quantile))
  for (h in ncol(x1)-1)
  {
    IQR <- q[4,h] - q[2,h]
    q1 <- q[2,h] - 3*IQR
    q2 <- q[4,h] + 3*IQR
    cond <- x1[,h] >= q1 & x1[,h] <= q2
    x1 <- x1[cond,]
    return (x1)
  }
}
#
# NORMALIZACAO MIN-MAX
#
normalize_minmax <- function(x1)
{
  normalize_mm <- function(x1)
  {
    return ((x1-min(x1))/(max(x1)-min(x1)))
  }
  x1_mm <- as.data.frame(lapply(x1, normalize_mm))
  return (x1_mm)
}

#
# FORWARD STEPWISE SELECTION
#
fss <- function(x1, reg)
{
  library(leaps)
  
  selec_col <- function(cols)
  {
    n <- names(cols)
    n1 <- n[-1]
    return (n1)
  }
  regfit.fwd <- regsubsets(reg, x1, nvmax=ncol(x1)-1, method="forward")  
  summary(regfit.fwd)
  reg.summaryfwd <- summary(regfit.fwd)
  b1 <- which.max(reg.summaryfwd$adjr2)
  t <- coef(regfit.fwd,b1)
  vec <- selec_col(t)
  return (x1[,vec])
}

#
# LASSO
#
lasso <- function(x1, reg)
{
  library(glmnet)
  m <- model.matrix(reg,x1)[,-1]  
  n <- x1[,ncol(x1)]
  set.seed(1)
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
  return (x1[,vec])
}

croc <- function(prob, label)
{
  require(ROCR)
  
  pred <- prediction(prob, label)
  perf <- performance(pred, "tpr", "fpr")
  plot(perf)
  auc <- performance(pred, "auc")
  return (auc)
}
