def Size_Number(A,n):
	soma = 0
	for i in range(n):
		for j in range(n):
			soma = soma + A[i,j]

	return soma
