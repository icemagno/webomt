def Degree(G,k):
	A = G.adjacency_matrix()
	A = Matrix(A)
	t = A.nrows()
	#t = len(A[0])
	deg = range(t)
	#print range(t)
	for i in range(t):
		deg[i] = 0
	for i in range(t):
		for j in range(t):
			deg[i] = deg[i] + A[i,j]
	deg.sort()
	indice = t-k
	x = deg[indice]
	return x