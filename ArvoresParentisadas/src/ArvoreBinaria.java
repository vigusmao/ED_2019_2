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

        // cria e empilha a raiz da árvore
        NoArvoreBinaria raiz = arvore.adicionarNo(valor, null, false);
        pilha.push(raiz);

        int pos = posFinalRaiz;
        boolean bypassarFilhoEsquerdo = false;

        while (pos < tamanhoString) {
            int posProximoAbreParentese = arvoreAsString.indexOf('(', pos);
            if (pos == posProximoAbreParentese) {
                pos++;     // apenas avance;
                continue;  // nada precisa ser feito diante de um '('
            }
            int posProximoFechaParentese = arvoreAsString.indexOf(')', pos);
            if (pos == posProximoFechaParentese) {
                if (arvoreAsString.charAt(pos - 1) == '(') {
                    bypassarFilhoEsquerdo = true;  // indica que o próximo filho será direito
                } else {
                    pilha.pop();
                }
                pos++;
                continue;
            }

            int posFimProximoValor = Math.min(
                    posProximoAbreParentese == -1 ? tamanhoString : posProximoAbreParentese,
                    posProximoFechaParentese == -1 ? tamanhoString : posProximoFechaParentese);

            int proximoValor = Integer.parseInt(
                    arvoreAsString.substring(pos, posFimProximoValor));

            NoArvoreBinaria noPai = pilha.peek();
            NoArvoreBinaria novoNo = arvore.adicionarNo(proximoValor, noPai,
                    !bypassarFilhoEsquerdo && noPai.getEsq() == null);
            pilha.push(novoNo);

            bypassarFilhoEsquerdo = false;  // reseta o flag
            pos = posFimProximoValor;  // avança o cursor para depois do fim do valor lido
        }

        return arvore;
    }
}
