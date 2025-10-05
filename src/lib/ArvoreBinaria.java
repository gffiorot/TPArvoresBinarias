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
 * @author GustavoFirme
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
        raiz = addRecursivo(raiz,novoValor);
    }

    private No addRecursivo(No atual, T novoValor){
        if (atual==null)
            return (new No(novoValor));

        int cmp = comp.compare(novoValor,atual.valor);

        if (cmp < 0) {
            atual.filhoEsquerdo = addRecursivo(atual.filhoEsquerdo,novoValor);
        } else if (cmp > 0) {
            atual.filhoDireito = addRecursivo(atual.filhoDireito,novoValor);
        } else {
            System.out.println("Esse valor já está na arvore!\n");
            return atual;
        }


        return atual;
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
        if (result == null) // não encontrou
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
            // Ao encontrar o valor salva ele numa variavel local para não dar erro no caso de 2 filhos
            T valorAuxRemovido = atual.valor;

            //Caso não tenha nenhum filho
            if (atual.filhoEsquerdo == null && atual.filhoDireito == null) {
                return null;
            }

            //Caso tenha 1 filho a direita
            if (atual.filhoEsquerdo == null)
                return atual.filhoDireito;

            //Caso tenha 1 filho a esquerda
            if (atual.filhoDireito == null)
                return atual.filhoEsquerdo;

            // 2 filhos
            No sucessor = encontrarMaior(atual.filhoEsquerdo); //Encontra o nó sucessor pelo método de achar o maior nó da subarvore a esquerda
            atual.valor = sucessor.valor;                      // Passa o nó sucessor ao local do nó removido
            atual.filhoEsquerdo = removerRecursivo(atual.filhoEsquerdo,sucessor.valor); // Remove o nó sucessor do local anterior
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
    private No encontrarMaior(No atual) {
        while (atual.filhoDireito != null) {
            atual = atual.filhoDireito;
        }
        return atual;
    }

    @Override
    public int altura() {
        return alturaRecursivo(raiz)-1; //De acordo com o IArvoreBinaria a raiz não conta para a altura
    }

    private int alturaRecursivo(No atual) {
        if (atual == null)
            return 0;

        int alturaEsquerda = alturaRecursivo(atual.filhoEsquerdo);
        int alturaDireita = alturaRecursivo(atual.filhoDireito);

        return 1+Math.max(alturaEsquerda, alturaDireita); //Math.max retorna o maior dos dois lados
    }


    @Override
    public int quantidadeNos() {
        // só precisa devolver o total de nós, não especifica que a raiz não conta
        return quantNoRecursivo(raiz);
    }

    private int quantNoRecursivo(No atual) {
        if (atual == null)
            return 0;

        int alturaEsquerda = quantNoRecursivo(atual.filhoEsquerdo);
        int alturaDireita = quantNoRecursivo(atual.filhoDireito);

        return 1+alturaEsquerda+alturaDireita; // única diferença do metodo altura
    }

    @Override
    public String caminharEmNivel() {
        if (raiz == null)
            return "[]";

        String caminho = "[";
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        int nivel = 0;

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();
            caminho += "Nível " + nivel++ + ": ";

            for (int i = 0; i < tamanhoNivel; i++) {
                No atual = fila.remove();
                caminho += atual.valor.toString()+" ";

                if (atual.filhoEsquerdo != null)
                    fila.add(atual.filhoEsquerdo);
                if (atual.filhoDireito != null)
                    fila.add(atual.filhoDireito);
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

    private String camOrdemRecursivo(String caminho,No atual) {
        if (atual == null)
            return caminho;

        caminho = camOrdemRecursivo(caminho,atual.filhoEsquerdo);
        caminho = caminho+atual.valor.toString()+"\n"; // No meio salve em ordem
        caminho = camOrdemRecursivo(caminho,atual.filhoDireito);

        return caminho;
    }


}