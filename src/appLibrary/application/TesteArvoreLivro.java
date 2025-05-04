package appLibrary.application;

import appLibrary.model.entities.Livro;
import appLibrary.model.comparators.ComparadorID;
import lib.ArvoreBinaria;

public class TesteArvoreLivro {
    public static void main(String[] args) {
        ArvoreBinaria<Livro> arvore = new ArvoreBinaria<>(new ComparadorID());

        Livro l1 = new Livro(10, "1984", "George Orwell", "Ficção");
        Livro l2 = new Livro(5, "Dom Casmurro", "Machado de Assis", "Romance");
        Livro l3 = new Livro(15, "O Hobbit", "Tolkien", "Fantasia");
        Livro l4 = new Livro(3, "A Revolução dos Bichos", "Orwell", "Ficção");
        Livro l5 = new Livro(7, "Memórias Póstumas", "Machado", "Romance");

        // Inserções
        arvore.adicionar(l1);
        arvore.adicionar(l2);
        arvore.adicionar(l3);
        arvore.adicionar(l4);
        arvore.adicionar(l5);


        // Imprimir
        System.out.println("Imprimindo em ordem: " + arvore.caminharEmOrdem());
        System.out.println("Imprimindo em nivel: " + arvore.caminharEmNivel());
        System.out.println("Altura: " + arvore.altura());


        // Pesquisas
        System.out.println("\nPesquisa Livro com ID 7: " + arvore.pesquisar(l5));  // deve encontrar
        System.out.println("Pesquisa Livro com ID 20 (inexistente): " + arvore.pesquisar(new Livro(20, "", "", "")));  // deve retornar null

        // Remoção
        System.out.println("\nRemoção do livro com ID 3: " + arvore.remover(l4));  // nó folha
        System.out.println("Remoção do livro com ID 5: " + arvore.remover(l2));  // nó com 1 filho
        System.out.println("Remoção do livro com ID 10: " + arvore.remover(l1)); // nó com 2 filhos


        System.out.println("\nVerifica se livro ID 10 ainda existe: " + arvore.pesquisar(l1));
    }
}
