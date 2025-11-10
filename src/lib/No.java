package lib;

public class No<T> {
    private T valor;
    private No<T> filhoEsquerdo;
    private No<T> filhoDireito;

    public No(T valor) {
        this.setValor(valor);
        this.setFilhoEsquerdo(null);
        this.setFilhoDireito(null);
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public void setFilhoEsquerdo(No<T> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public No<T> getFilhoDireito() {
        return filhoDireito;
    }

    public void setFilhoDireito(No<T> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }
}
