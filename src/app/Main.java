package app;

import app.comparators.*;
import lib.ArvoreBinaria;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        // Criando comparadores
        Comparator<Aluno> compMatricula = new ComparadorAlunoPorMatricula();

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

        System.out.println("\n=== Adicionando alunos para exemplo ===\n");
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

        // Caminhar por nível após remoção
        System.out.println("Alunos por nivel");
        System.out.println(arvore.caminharEmNivel());

        Menu menu = new Menu();
        menu.menu(arvore);

    }
}
