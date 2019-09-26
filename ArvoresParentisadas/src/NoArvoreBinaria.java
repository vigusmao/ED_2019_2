public class NoArvoreBinaria {

    private ArvoreBinaria arvore;

    private NoArvoreBinaria pai;
    private NoArvoreBinaria esq;
    private NoArvoreBinaria dir;

    private int valor;

    NoArvoreBinaria(int valor, ArvoreBinaria arvore) {
        this.arvore = arvore;
        this.valor = valor;
    }

    void adicionarFilho(NoArvoreBinaria filho, boolean esquerdo) {
        if (esquerdo) {
            this.esq = filho;
        } else {
            this.dir = filho;
        }
    }

    ArvoreBinaria getArvore() {
        return this.arvore;
    }

    public int getValor() {
        return this.valor;
    }

    public NoArvoreBinaria getEsq() {
        return esq;
    }

    public NoArvoreBinaria getDir() {
        return dir;
    }

    public void setPai(NoArvoreBinaria pai) {
        this.pai = pai;
    }

}
