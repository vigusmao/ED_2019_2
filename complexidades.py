from time import time
N = 10000

start = time()
cont = 0
for i in range (1, N+1):
    for j in range(1, N+1):
        cont += 1
print("\ncont = %d" % cont)
print("duracao = %f" % (time() - start))

start = time()
cont = 0
for i in range (1, N+1):
    for j in range(i+1, N+1):
        cont += 1
print("\ncont = %d" % cont)
print("duracao = %f" % (time() - start))

start = time()
cont = 0
for i in range (1, N+1):
    for j in range(i, N+1, i):
        cont += 1
print("\ncont = %d" % cont)
print("duracao = %f" % (time() - start))
