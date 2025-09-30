/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Comparator;

/**
 *
 * @author victoriocarvalho
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    private class No{
        private T valor;
        private No filhoEsquerdo;
        private No filhoDireito;

        private No(T valor) {
            this.valor = valor;
            this.filhoEsquerdo = null;
            this.filhoDireito = null;
        }


    }

    protected No raiz = null;
    protected Comparator<T> comp;

    public ArvoreBinaria(Comparator<T> comp) {
        this.comp = comp;
        this.raiz = null;
    }

    @Override
    public void adicionar(T novoValor) {
        if (raiz == null) {
            raiz = new No(novoValor);
            return;
        }
        No aux = raiz; //Usado para percorrer e simbolizar onde o novo nó vai ficar
        No pai = null; //Usado para apontar onde o novo nó vai estar

        while (aux != null) {
            pai = aux;
            if (comp.compare(novoValor, aux.valor) < 0) {
                aux = aux.filhoEsquerdo;
            }
            else if (comp.compare(novoValor, aux.valor) > 0) {
                aux = aux.filhoDireito;
            }
            else {
                System.out.println("Esse valor já está na arvore!\n");
                return;
            }
        }
        if (comp.compare(novoValor, pai.valor) < 0) {
            pai.filhoEsquerdo = new No(novoValor);
        } else {
            pai.filhoDireito = new No(novoValor);
        }
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método de pesquisa utilizando o comparator da lista
     * Retorna Null se não achar
     */
    @Override
    public T pesquisar(T valor) {
        No result = pesqBinaria(raiz,valor);
        if (result == null)
            return null;
        return (result.valor);
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método de pesquisa utilizando um comparator específico
     * Retorna Null se não achar
     */
    @Override
    public T pesquisar(T valor, Comparator comparador) {
        No result = null;
        if (comparador.getClass() != comp.getClass()) {
            result = pesqPercorre(this.raiz,valor,comparador);
        }
        else {
            result = pesqBinaria(this.raiz,valor);

        }
        if (result == null)
            return null;
        return result.valor;
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método auxiliar para o caso de pesquisar a lista com o comparator correto
     */
    private No pesqBinaria(No atual,T valor) {
        while (atual != null) {
            if (comp.compare(valor, atual.valor) < 0) {
                atual = atual.filhoEsquerdo;
            }
            else if (comp.compare(valor, atual.valor) > 0)
            {atual = atual.filhoDireito;
            }
            else return atual;
        }


        return null;
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método auxiliar para o caso de pesquisar a lista com o comparator errado
     */
    private No pesqPercorre(No atual,T valor, Comparator comparador) {
        if (atual == null)
            return null;

        if (comparador.compare(valor, atual.valor) == 0) //Se for o valor procurado retorna o No
            return atual;

        No aux = pesqPercorre(atual.filhoEsquerdo, valor, comparador); //Verifica recursivamente a esquerda
        if (aux != null) // Só acontece se o valor for igual ao procurado
            return aux;

        return pesqPercorre(atual.filhoDireito, valor, comparador); //Verifica recursivamente a direita
    }


    private T valorRemovido; // Variavel auxiliar local

    @Override
    public T remover(T valor) {
        valorRemovido = null;
        raiz = removerRecursivo(raiz,valor);
        return (valorRemovido);
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método auxiliar do método remover para ser possivel recursão na parte de remover
     */
    private No removerRecursivo(No atual, T valor) {
        if (atual == null)
            return null;

        int cmp = comp.compare(valor, atual.valor);

        if (cmp < 0) {
            atual.filhoEsquerdo = removerRecursivo(atual.filhoEsquerdo,valor);
        } else if (cmp > 0) {
            atual.filhoDireito = removerRecursivo(atual.filhoDireito,valor);
        } else {
            valorRemovido = valor; // Ao encontrar o valor salva ele numa

            if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
                return null;                 //Caso não tenha nenhum filho
            }

            if (atual.filhoEsquerdo == null) //Caso tenha 1 filho a direita
                return atual.filhoDireito;

            if (atual.filhoDireito == null) //Caso tenha 1 filho a esquerda
                return atual.filhoEsquerdo;

            // 2 filhos
            No sucessor = encontrarMaior(atual.filhoEsquerdo); //Encontra o nó sucessor pelo método de achar o maior nó da subarvore a esquerda
            atual.valor = sucessor.valor;                      // Passa o nó sucessor ao local do nó removido
            atual.filhoEsquerdo = removerRecursivo(atual.filhoEsquerdo,sucessor.valor); // Remove o nó sucessor do local anterior
        }

        return atual;
    }

    /**
     *
     * @author GustavoFirme
     *
     * Método auxiliar do método removerRecursivo para encontrar o
     * nó substitutivo no caso de o removido ter 2 filhos
     */
    private No encontrarMaior(No atual) {
        while (atual.filhoDireito != null) {
            atual = atual.filhoDireito;
        }
        return atual;
    }


    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public int quantidadeNos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String caminharEmNivel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }

    @Override
    public String caminharEmOrdem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }

}