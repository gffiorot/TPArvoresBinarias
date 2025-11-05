package lib;

import java.util.Comparator;

public class ArvoreAVL <T> extends ArvoreBinaria<T>{

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }

    protected int fatorBalanco(No<T> atual) {
        if (atual == null)
            return 0;

        int alturaEsq = alturaRecursivo(atual.getFilhoEsquerdo());
        int alturaDir = alturaRecursivo(atual.getFilhoDireito());

        return alturaDir - alturaEsq;
    }

    private No<T> rotacaoDireita(No<T> r) {
        No<T> aux = r.getFilhoEsquerdo();
        r.setFilhoEsquerdo(aux.getFilhoDireito());
        aux.setFilhoDireito(r);
        return aux;
    }
    private No<T> rotacaoEsquerda(No<T> r) {
        No<T> aux = r.getFilhoDireito();
        r.setFilhoDireito(aux.getFilhoEsquerdo());
        aux.setFilhoEsquerdo(r);
        return aux;
    }
    private No<T> rotacaoDireitaEsquerda(No<T> r) {
        r.setFilhoDireito(rotacaoDireita(r.getFilhoDireito()));
        return rotacaoEsquerda(r);
    }
    private No<T> rotacaoEsquerdaDireita(No<T> r) {
        r.setFilhoEsquerdo(rotacaoEsquerda(r.getFilhoEsquerdo()));
        return rotacaoDireita(r);
    }

    @Override
    public void adicionar(T novoValor) {
        raiz = addRecursivo(raiz,novoValor);
    }

    @Override
    protected No<T> addRecursivo(No<T> atual, T novo){
        atual = super.addRecursivo(atual,novo);
        int fb = fatorBalanco(atual);

        if (fb>1){
            if (fatorBalanco(atual.getFilhoDireito())>=0)
                atual = this.rotacaoEsquerda(atual);
            else
                atual = this.rotacaoDireitaEsquerda(atual);

        }
        else if (fb< -1){
            if (fatorBalanco(atual.getFilhoEsquerdo())<=0)
                atual = this.rotacaoDireita(atual);
            else
                atual = this.rotacaoEsquerdaDireita(atual);
        }

        return atual;
    }

}