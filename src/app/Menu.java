package app;

import app.comparators.ComparadorAlunoPorNome;
import lib.ArvoreBinaria;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    Comparator<Aluno> compNome = new ComparadorAlunoPorNome();
    Scanner input = new Scanner(System.in);

    void menu(ArvoreBinaria<Aluno> arvore) {
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
                    Aluno existente = arvore.pesquisar(new Aluno(a.getMatricula(), "")); //Para verificar se ja existe a matricula
                    if (existente != null){
                        System.out.println("Aluno com essa matrícula ja existe: "+ existente);
                    }
                    else {
                        arvore.adicionar(a);
                        System.out.println("Aluno adicionado: "+a);
                    }
                    break;
                } case 2:{

                    msg = "como vc quer buscar o aluno \n (1)Matricula? \n (2)Nome "; //1 == matricula, 2 == nome
                    int valor1 = this.lerInt(msg);
                    if (valor1 == 1){
                        msg = "digite a matricula que quer buscar";
                        int valor = this.lerInt(msg);
                        Aluno pesquisado = new Aluno(valor,"");
                        Aluno k = arvore.pesquisar(pesquisado);
                        if (k != null){
                            System.out.println("Aluno encontrado: "+ k);
                        }
                        else {
                            System.out.println("Aluno não encontrado");
                        }

                    }
                    else if (valor1 == 2){
                        msg = "digite o nome do aluno que quer buscar";
                        String valor = this.lerLinha(msg);
                        Aluno pesquisado = new Aluno(-1,valor);

                        Aluno k = arvore.pesquisar(pesquisado,compNome);
                        if (k != null){
                            System.out.println("Aluno encontrado: "+ k);
                        }
                        else {
                            System.out.println("Aluno não encontrado");
                        }
                    }

                    break;
                }
                case 3: {
                    msg = "digite a matricula que quer remover ";
                    int valor = this.lerInt(msg);

                    Aluno pesquisado = arvore.pesquisar(new Aluno(valor,""));

                    if (pesquisado != null){
                        Aluno removido = arvore.remover(pesquisado);
                        System.out.println("Aluno removido: "+removido);
                    }
                    else {
                        System.out.println("Aluno não encontrado");
                    }
                    break;
                }
                case 4:{
                    System.out.println(  arvore.caminharEmOrdem());
                    break;
                }
                case 0: {
                    System.out.println("saindo");
                    input.close();
                    roda = false;
                    break;

                } default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private  int lerInt(String msg) {
        String linha = lerLinha(msg);

        return Integer.parseInt(linha);
    }

    private  String lerLinha(String msg) {
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

