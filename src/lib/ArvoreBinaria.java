/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Gustavo Firme, Rafael Deps, David de Assis
 */
public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    protected No<T> raiz = null;
    protected Comparator<T> comp;

    public ArvoreBinaria(Comparator<T> comp) {
        this.comp = comp;
        this.raiz = null;
    }

    @Override
    public void adicionar(T novoValor) {
        raiz = addRecursivo(raiz,novoValor);
    }

    protected No<T> addRecursivo(No<T> atual, T novoValor){
        if (atual==null)
            return (new No<T>(novoValor));

        int cmp = comp.compare(novoValor, atual.getValor());

        if (cmp < 0) {
            atual.setFilhoEsquerdo(addRecursivo(atual.getFilhoEsquerdo(),novoValor));
        } else if (cmp > 0) {
            atual.setFilhoDireito(addRecursivo(atual.getFilhoDireito(),novoValor));
        }


        return atual;
    }

    /**
     *
     *
     * Método de pesquisa utilizando o comparator da lista
     * Retorna Null se não achar
     */
    @Override
    public T pesquisar(T valor) {
        No<T> result = pesqBinaria(raiz,valor);
        if (result == null)
            return null;
        return (result.getValor());
    }

    /**
     *
     *
     * Método de pesquisa utilizando um comparator específico
     * Retorna Null se não achar
     */
    @Override
    public T pesquisar(T valor, Comparator comparador) {
        No<T> result = null;

        if (comparador.getClass() != comp.getClass()) {
            result = pesqPercorre(this.raiz,valor,comparador);
        }
        else {
            result = pesqBinaria(this.raiz,valor);

        }
        if (result == null) // não encontrou
            return null;
        return result.getValor();
    }

    /**
     *
     *
     * Método auxiliar para o caso de pesquisar a lista com o comparator correto
     */
    private No<T> pesqBinaria(No<T> atual,T valor) {
        while (atual != null) {
            if (comp.compare(valor, atual.getValor()) < 0) {
                atual = atual.getFilhoEsquerdo();
            }
            else if (comp.compare(valor, atual.getValor()) > 0)
            {atual = atual.getFilhoDireito();
            }
            else return atual;
        }


        return null;
    }

    /**
     *
     *
     * Método auxiliar para o caso de pesquisar a lista com o comparator errado
     */
    private No<T> pesqPercorre(No<T> atual,T valor, Comparator comparador) {
        if (atual == null)
            return null;

        if (comparador.compare(valor, atual.getValor()) == 0) //Se for o valor procurado retorna o No
            return atual;

        No<T> aux = pesqPercorre(atual.getFilhoEsquerdo(), valor, comparador); //Verifica recursivamente a esquerda
        if (aux != null) // Só acontece se o valor for igual ao procurado
            return aux;

        return pesqPercorre(atual.getFilhoDireito(), valor, comparador); //Verifica recursivamente a direita
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
    private No<T> removerRecursivo(No<T> atual, T valor) {
        if (atual == null)
            return null;

        int cmp = comp.compare(valor, atual.getValor());

        if (cmp < 0) {
            atual.setFilhoEsquerdo(removerRecursivo(atual.getFilhoEsquerdo(),valor));
        } else if (cmp > 0) {
            atual.setFilhoDireito(removerRecursivo(atual.getFilhoDireito(),valor));
        } else {
            // Ao encontrar o valor salva ele numa variavel local para não dar erro no caso de 2 filhos
            T valorAuxRemovido = atual.getValor();

            //Caso não tenha nenhum filho
            if (atual.getFilhoEsquerdo() == null && atual.getFilhoDireito() == null) {
                valorRemovido = valorAuxRemovido;
                return null;
            }

            //Caso tenha 1 filho a direita
            if (atual.getFilhoEsquerdo() == null){
                valorRemovido = valorAuxRemovido;
                return atual.getFilhoDireito();
            }

            //Caso tenha 1 filho a esquerda
            if (atual.getFilhoDireito() == null){
                valorRemovido = valorAuxRemovido;
                return atual.getFilhoEsquerdo();
            }

            // 2 filhos
            No<T> sucessor = encontrarMaior(atual.getFilhoEsquerdo()); //Encontra o nó sucessor pelo método de achar o maior nó da subarvore a esquerda
            atual.setValor(sucessor.getValor());                      // Passa o nó sucessor ao local do nó removido
            atual.setFilhoEsquerdo(removerRecursivo(atual.getFilhoEsquerdo(), sucessor.getValor())); // Remove o nó sucessor do local anterior
            valorRemovido = valorAuxRemovido; // Só depois do sucessor tomar o lugar muda a variavel global
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
    private No<T> encontrarMaior(No<T> atual) {
        while (atual.getFilhoDireito() != null) {
            atual = atual.getFilhoDireito();
        }
        return atual;
    }

    @Override
    public int altura() {
        return alturaRecursivo(raiz)-1; //De acordo com o IArvoreBinaria a raiz não conta para a altura
    }

    protected int alturaRecursivo(No<T> atual) {
        if (atual == null)
            return 0;

        int alturaEsquerda = alturaRecursivo(atual.getFilhoEsquerdo());
        int alturaDireita = alturaRecursivo(atual.getFilhoDireito());

        return 1+Math.max(alturaEsquerda, alturaDireita); //Math.max retorna o maior dos dois lados
    }


    @Override
    public int quantidadeNos() {
        // só precisa devolver o total de nós, não especifica que a raiz não conta
        return quantNoRecursivo(raiz);
    }

    private int quantNoRecursivo(No<T> atual) {
        if (atual == null)
            return 0;

        int alturaEsquerda = quantNoRecursivo(atual.getFilhoEsquerdo());
        int alturaDireita = quantNoRecursivo(atual.getFilhoDireito());

        return 1+alturaEsquerda+alturaDireita; // única diferença do metodo altura
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null)
            return "[]";

        String caminho = "[";
        Queue<No<T>> fila = new LinkedList<>();
        fila.add(raiz);

        int nivel = 0;

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            caminho += "Nível " + nivel++ + ": ";

            for (int i = 0; i < tamanhoNivel; i++) {
                No<T> atual = fila.remove();
                caminho += atual.getValor().toString()+" ";

                if (atual.getFilhoEsquerdo() != null)
                    fila.add(atual.getFilhoEsquerdo());
                if (atual.getFilhoDireito() != null)
                    fila.add(atual.getFilhoDireito());
            }

            if (!fila.isEmpty())
                caminho += "\n";
        }

        return caminho+"]";

    }


    @Override
    public String caminharEmOrdem() {
        String caminho = camOrdemRecursivo("[",raiz);
        return caminho+"]";
        }

    private String camOrdemRecursivo(String caminho,No<T> atual) {
        if (atual == null)
            return caminho;

        caminho = camOrdemRecursivo(caminho, atual.getFilhoEsquerdo());
        caminho = caminho+ atual.getValor().toString()+"\n"; // No meio salve em ordem
        caminho = camOrdemRecursivo(caminho, atual.getFilhoDireito());

        return caminho;
    }


}