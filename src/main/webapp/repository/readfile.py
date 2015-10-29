## função que calcula os valores dos invariantes de grafos
## Parâmetros: 
## arquivo:  arquivo texto com a matriz de adjacência gerada pelo geng
## chave: string contendo os caracteres correspondentes 
## das funções a serem geradas
##        -a   : número cromatico
##        -b   : número cromatico do grafo complementar
##        -c   : tamanho da maior clique
##        -d   : tamanho da maior clique do grafo complementar
##        -e k : k-ésimo maior grau do grafo
##        -f k : k-ésimo maior grau do grafo complementar
##
import getopt
def readfile(arquivo, args):
	f = open(arquivo,'r') #leitura do arquivo
	f.readline()
	l = [ map(int,line.split(' ')) for line in f ]
	args = args.split()
	optlist, args = getopt.getopt(args, 'abcde:f:')
	for o, a in optlist:
        if o == "-a":
            ChromaticNumberNeeded = True
        elif o == "-b":
            ChromaticNumberComplementNeeded = True
            Flag = 1
        elif o == "-c":
            LargestCliqueSizeNeeded = True
        elif o == "-d":
            LargestCliqueSizeComplementNeeded = True
        elif o == "-e":
            kLargestDegree = True
            ParamkLargestDegree = a
        elif o == "-f":
            kLargestDegreeComplement = True
            ParamkLargestDegreeComplement = a
        else:
            assert False, "unhandled option"
	if ChromaticNumberComplementNeeded or LargestCliqueSizeComplementNeeded or kLargestDegreeComplement
	   lc = l - 
	g = open('saida.txt','w')
	
	g.close()
	f.close()
	
	return l