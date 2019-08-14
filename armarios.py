from time import time

ABERTO = True
FECHADO = False


def armarios_n2(n):
    abertos = []
    estados = [FECHADO] * (n+1)  # descartaremos a posicao zero

    for aluno in range(1, n+1):  # for (int aluno = 1; aluno <= n; aluno++) {
       armario = 1
       while armario <= n:
           if armario % aluno == 0:
               estados[armario] = not estados[armario]
           armario += 1
 
    for armario in range(1, n+1):
        if estados[armario] == ABERTO:  # (o armario estah aberto)
            abertos.append(armario)            

    return abertos 


def armarios_nlogn(n):
    abertos = []
    estados = [FECHADO] * (n+1)  # descartaremos a posicao zero

    for aluno in range(1, n+1):  # for (int aluno = 1; aluno <= n; aluno++) {
       armario = aluno
       while armario <= n:
           estados[armario] = not estados[armario]
           armario += aluno
 
    for armario in range(1, n+1):
        if estados[armario] == ABERTO:  # (o armario estah aberto)
            abertos.append(armario)

    return abertos

def armarios_n(n):
    abertos = []
    for armario in range(1, n+1):
        raiz = armario**0.5 
        if raiz == int(raiz):
            abertos.append(armario)
    return abertos

def armarios_sqrtn(n):
    abertos = []
    x = 1
    quadrado = 1 
    while quadrado <= n:
        abertos.append(quadrado)
        x += 1
        quadrado = x**2
        
    return abertos


#### MAIN

while True:
    quant_armarios = eval(input("Digite o numero de armarios: "))
    if quant_armarios <= 0:
        break
    
##    inicio = time()
##    resultado = armarios_n2(quant_armarios)
##    duracao = time() - inicio
##    print("\nAlgoritmo O(n^2)")
##    print("Ficaram %d armarios abertos do total de %d" % (len(resultado), quant_armarios))
##    print("Duracao: %f segundos" % duracao) 
##
##    inicio = time()
##    resultado = armarios_nlogn(quant_armarios)
##    duracao = time() - inicio
##    print("\nAlgoritmo O(n log n)")
##    print("Ficaram %d armarios abertos do total de %d" % (len(resultado), quant_armarios))
##    print("Duracao: %f segundos" % duracao) 

    inicio = time()
    resultado = armarios_n(quant_armarios)
    duracao = time() - inicio
    print("\nAlgoritmo O(n)")
    print("Ficaram %d armarios abertos do total de %d" % (len(resultado), quant_armarios))
    print("Duracao: %f segundos" % duracao) 

    inicio = time()
    resultado = armarios_sqrtn(quant_armarios)
    duracao = time() - inicio
    print("\nAlgoritmo O(sqrtn)")
    print("Ficaram %d armarios abertos do total de %d" % (len(resultado), quant_armarios))
    print("Duracao: %f segundos" % duracao) 

print("Tchau!!!")

    
