package app;

import app.comparators.*;
import lib.ArvoreBinaria;
import java.util.Comparator;
import java.util.Scanner;

import java.util.Comparator;

public class Menu { int  menu(ArvoreBinaria<Aluno> arvore) {
    Scanner input = new Scanner(System.in);
    Comparator<Aluno> compMatricula = new ComparadorAlunoPorMatricula();
    Comparator<Aluno> compNome = new ComparadorAlunoPorNome();


    int opcao;

    boolean roda = true;
    while (roda){
        String msg = "*********************\n" +
                "Escolha uma opção\n" +
                "1) Adicionar Novo Aluno\n" +
                "2) Pesquisar Aluno\n" +
                "3) Remover Aluno\n"+
                "4) Imprimir a Arvore\n"+
                "0) Sair\n";
        opcao = lerInt(msg);
        switch (opcao){
            case 1:{
                Aluno a = this.prepararadd();
                arvore.adicionar(a);
                System.out.println("Aluno adicionado , mat :"+ a.getMatricula() + " nome :  " + a.getNome());
                break;
            } case 2:{

                msg = "como vc quer buscar o aluno \n (1)Matricula? \n (2)Nome "; //1 == matricula, 2 == nome
                int valor1 = this.lerInt(msg);
                if (valor1 == 1){
                    msg = "digite a matricula que quer buscar";
                    int valor = this.lerInt(msg);
                    Aluno pesquisado = new Aluno(valor,"");
                    Aluno k = arvore.pesquisar(pesquisado);
                    System.out.println(k.getNome());

                }
                else if (valor1 == 2){
                    msg = "digite o nome do aluno que quer buscar";
                    String valor = this.lerLinha(msg);
                    Aluno pesquisado = new Aluno(-1,valor);

                    Aluno k = arvore.pesquisar(pesquisado,compNome);
                    System.out.println(k.getMatricula());}

                break;
            }
            case 3: {
                msg = "digite a matricula que quer remover ";
                int valor = this.lerInt(msg);
                Aluno pesquisado = new Aluno(valor,"");
                pesquisado = arvore.pesquisar(pesquisado);
                Aluno k = arvore.remover(pesquisado);
                System.out.println("Matricula removida: " + pesquisado.getMatricula() + "\n" +  "Nome removido " + pesquisado.getNome());
                break;
            }
            case 4:{
                System.out.println(  arvore.caminharEmOrdem());
                break;
            }
            case 0: {
                System.out.println("saindo");
                roda = false;
                break;

            } default:
                System.out.println("Opção inválida.");
        }
    }
    return 0;
}

    private  int lerInt(String msg) {
        String linha = lerLinha(msg);

        return Integer.parseInt(linha);
    }

    private  String lerLinha(String msg) {
        Scanner input = new Scanner(System.in);
        System.out.println(msg);
        return input.nextLine();
    }
    private Aluno prepararadd(){


        String msg = "digite a matricula ";
        int matriculanova = lerInt(msg) ;
        msg = "digite o nome ";
        String nomenovo = lerLinha(msg) ;
        Aluno alunonovo = new Aluno(matriculanova , nomenovo);
        return  alunonovo;
    }
}
