package model;

import java.util.Comparator;

public class ComparadorNome implements Comparator<Livro> {

    @Override
    public int compare(Livro l1, Livro l2) {
        return l1.getNome().compareToIgnoreCase(l2.getNome());
    }
}

