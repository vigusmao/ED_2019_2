#include<stdlib.h>
#include<stdio.h>

typedef struct _lista_encadeada LISTA_ENCADEADA;
typedef struct _no_lista NO_LISTA;

struct _no_lista {
    int dado;  // qualquer campo de dados do tipo desejado

    struct _no_lista* prox;
    struct _no_lista* ant;

    LISTA_ENCADEADA* minha_lista;

};

struct _lista_encadeada {
    int tamanho;
    int capacidade;
    NO_LISTA* primeiro;
    NO_LISTA* ultimo;
};

LISTA_ENCADEADA* criar_lista(int capacidade) {
    LISTA_ENCADEADA* lista = (LISTA_ENCADEADA*) malloc(sizeof(LISTA_ENCADEADA));
    lista->capacidade = capacidade;
    lista->primeiro = NULL;
    lista->ultimo = NULL;
    lista->tamanho = 0;
    return lista;
}

NO_LISTA* append(LISTA_ENCADEADA* lista, int elemento) {
    if (lista->tamanho == lista->capacidade) {
        return NULL;  // overflow: não vai inserir; poderia talvez lançar uma exceção
    }
    NO_LISTA* novo_no = (NO_LISTA*) malloc(sizeof(NO_LISTA));
    novo_no->minha_lista = lista;
    novo_no->dado = elemento;
    novo_no->prox = NULL;
    novo_no->ant = lista->ultimo;
    if (lista->tamanho > 0) {
        lista->ultimo->prox = novo_no;
    } else {
        lista->primeiro = novo_no;    
    }
    lista->ultimo = novo_no;
    lista->tamanho++;
    return novo_no;
}

void remover_no(LISTA_ENCADEADA* lista, NO_LISTA* no) {
    if (no == NULL) {
       return;  // nao faz sentido remover um nó nulo
    }
    if (no->minha_lista != lista) {
       return;  // poderia talvez lançar uma exceção 
    }

    if (no == lista->ultimo) {
        lista->ultimo = no->ant;
    } else {
        no->prox->ant = no->ant;
    }
    
    if (no == lista->primeiro) {
        lista->primeiro = no->prox;
    } else {
        no->ant->prox = no->prox;
    }

    lista->tamanho--;
    free(no);
}

int index_of(LISTA_ENCADEADA* lista, int elemento) {
    int indice = 0;
    NO_LISTA* no_corrente = lista->primeiro;
    while(no_corrente != NULL) {
        if(no_corrente->dado == elemento) {
            return indice;   
        }
        no_corrente = no_corrente->prox;
        indice++;
    }
    return -1;
}    

NO_LISTA* buscar(LISTA_ENCADEADA* lista, int elemento) {
    NO_LISTA* no_corrente = lista->primeiro;
    while(no_corrente != NULL) {
        if(no_corrente->dado == elemento) {
            return no_corrente;   
        }
        no_corrente = no_corrente->prox;
    }
    return NULL;
}

void remover(LISTA_ENCADEADA* lista, int elemento) {
    remover_no(lista, buscar(lista, elemento));
} 

void imprimir_lista(LISTA_ENCADEADA* lista) {
    printf("[");
    NO_LISTA* no_corrente = lista->primeiro;
    while(no_corrente != NULL) {
        printf("%d", no_corrente->dado);
        if(no_corrente != lista->ultimo) {
            printf(", ");   
        }
        no_corrente = no_corrente->prox;
    }    
    printf("] -- tamanho: %d", lista->tamanho);
}    

int main() {
    LISTA_ENCADEADA* lista = criar_lista(100);
    imprimir_lista(lista); printf("\n");

    int i;
    for(i=1; i<=5; i++) {
        append(lista, i);
        imprimir_lista(lista); printf("\n");
    }

    printf("indice do 4 --> %d\n", index_of(lista, 4));
    remover(lista, 3);  // vamos remover o elemento de valor 3
    imprimir_lista(lista); printf("\n");
    printf("indice do 4 --> %d\n", index_of(lista, 4));
    
    imprimir_lista(lista); printf("\n");
    

    printf("\n\n");

}
