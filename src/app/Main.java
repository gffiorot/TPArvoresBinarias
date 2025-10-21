package app;

import app.comparators.*;
import lib.ArvoreBinaria;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        // Criando comparadores
        Comparator<Aluno> compMatricula = new ComparadorAlunoPorMatricula();
        Comparator<Aluno> compNome = new ComparadorAlunoPorNome();

        // Criando árvore ordenada por matrícula
        ArvoreBinaria<Aluno> arvore = new ArvoreBinaria<>(compMatricula);

        // Adicionando alunos
        Aluno a1 = new Aluno(10, "Alice");
        Aluno a2 = new Aluno(5, "Bob");
        Aluno a3 = new Aluno(15, "Carol");
        Aluno a4 = new Aluno(7, "David");
        Aluno a5 = new Aluno(3, "Eve");
        Aluno a6 = new Aluno(4, "Fred");
        Aluno a7 = new Aluno(6, "Jack");
        Aluno a8 = new Aluno(8, "John");
        Aluno a9 = new Aluno(13, "Jane");
        Aluno a10 = new Aluno(2, "Jose");

        System.out.println("=== Adicionando alunos ===");
        arvore.adicionar(a1);
        arvore.adicionar(a2);
        arvore.adicionar(a3);
        arvore.adicionar(a4);
        arvore.adicionar(a5);
        arvore.adicionar(a6);
        arvore.adicionar(a7);
        arvore.adicionar(a8);
        arvore.adicionar(a9);
        arvore.adicionar(a10);

        // Tentando adicionar duplicado
        arvore.adicionar(a3);

        // Caminhar em ordem
        System.out.println("\n=== Caminhar em ordem ===");
        System.out.println(arvore.caminharEmOrdem());

        // Caminhar por nível
        System.out.println("\n=== Caminhar em nível ===");
        System.out.println(arvore.caminharEmNivel());

        // Altura e quantidade de nós
        System.out.println("\nAltura da árvore: " + arvore.altura());
        System.out.println("Quantidade de nós: " + arvore.quantidadeNos());

        // Pesquisar por matrícula (usando comparator da árvore)
        System.out.println("\n=== Pesquisar por matrícula (comparator da árvore) ===");
        Aluno busca1 = arvore.pesquisar(new Aluno(7, ""));
        System.out.println(busca1 != null ? "Encontrado: " + busca1 : "Não encontrado");

        // Pesquisar usando outro comparator (por nome)
        System.out.println("\n=== Pesquisar por nome (outro comparator) ===");
        Aluno busca2 = arvore.pesquisar(new Aluno(0, "David"), compNome);
        System.out.println(busca2 != null ? "Encontrado: " + busca2 : "Não encontrado");

        // Pesquisar um que não existe
        System.out.println("\n=== Pesquisar aluno inexistente ===");
        Aluno busca3 = arvore.pesquisar(new Aluno(99, "Zara"));
        System.out.println(busca3 != null ? "Encontrado: " + busca3 : "Não encontrado");

        // Remover alunos
        System.out.println("\n=== Removendo alunos ===");
        Aluno removido1 = arvore.remover(new Aluno(5, "")); // Bob
        System.out.println("Removido: " + (removido1 != null ? removido1 : "Não encontrado"));

        Aluno removido2 = arvore.remover(new Aluno(99, "")); // inexistente
        System.out.println("Removido: " + (removido2 != null ? removido2 : "Não encontrado"));

        // Caminhar em ordem após remoção
        System.out.println("\n=== Caminhar em ordem após remoção ===");
        System.out.println(arvore.caminharEmOrdem());

        // Caminhar por nível após remoção
        System.out.println("\n=== Caminhar em nível após remoção ===");
        System.out.println(arvore.caminharEmNivel());

        // Altura e quantidade de nós após remoção
        System.out.println("\nAltura da árvore: " + arvore.altura());
        System.out.println("Quantidade de nós: " + arvore.quantidadeNos());

        Menu menu = new Menu();
        menu.menu(arvore);

    }
}
