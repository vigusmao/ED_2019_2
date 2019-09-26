import java.util.Stack;

public class ArvoreBinaria {

    NoArvoreBinaria raiz;

    int size;

    public NoArvoreBinaria adicionarNo(
            int valor, NoArvoreBinaria pai, boolean ehFilhoEsq) {
        if (pai != null && pai.getArvore() != this) {
            throw new RuntimeException("A árvore do pai é outra!");
        }

        NoArvoreBinaria no = new NoArvoreBinaria(valor, this);

        no.setPai(pai);

        if (pai != null) {
            pai.adicionarFilho(no, ehFilhoEsq);
        } else {
            // o nó que está sendo adicionado é a raiz da árvore
            this.raiz = no;
        }

        this.size++;

        return no;
    }

    private void imprimirSubArvore(NoArvoreBinaria noCorrente,
                                   StringBuilder sb) {
        if (noCorrente != this.raiz) {
            sb.append('(');
        }
        if (noCorrente != null) {
            sb.append(noCorrente.getValor());

            NoArvoreBinaria filhoEsq = noCorrente.getEsq();
            NoArvoreBinaria filhoDir = noCorrente.getDir();

            if (filhoEsq != null || filhoDir != null) {
                imprimirSubArvore(filhoEsq, sb);
                if (filhoDir != null) {
                    imprimirSubArvore(filhoDir, sb);
                }
            }
        }
        if (noCorrente != this.raiz) {
            sb.append(')');
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        imprimirSubArvore(this.raiz, sb);
        return sb.toString();
    }

    public static ArvoreBinaria fromString(String arvoreAsString) {
        int tamanhoString = arvoreAsString.length();
        ArvoreBinaria arvore = new ArvoreBinaria();
        Stack<NoArvoreBinaria> pilha = new Stack<>();

        int posFinalRaiz = arvoreAsString.indexOf('(');
        if (posFinalRaiz == -1) {
            posFinalRaiz = tamanhoString;
        }

        int valor = Integer.valueOf(
            arvoreAsString.substring(0, posFinalRaiz));

        // crio a raiz da árvore
        arvore.adicionarNo(valor, null, false);

        int pos = posFinalRaiz;
        while (pos < tamanhoString) {
            // ToDo Descubro a posição do próximo parêntese
            // ToDo Se é maior que a posição corrente, leio o número até lá
            //      (e trato esse número, criando um nó e empilhando)
            // Caso a posição corrente seja um abre-parêntese, não faz nada
            // Caso a posição corrente seja um fecha-parêntese,
            //       ... se a posição anterior for abre-parêntese, cria um filho esq nulo
            //       ... senão desempilha
        }



    }

}
