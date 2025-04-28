package lib;

import java.util.Comparator;

public class ArvoreBinaria<T> implements IArvoreBinaria<T> {

    private No<T> raiz;
    private Comparator<T> comparador;

    public ArvoreBinaria(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    @Override
    public void adicionar(T novoValor) {
        No<T> novoNo = new No<>(novoValor);
        if (raiz == null) {
            raiz = novoNo;
            return;
        }
        No<T> atual = raiz;
        while (true) {
            int comparacao = comparador.compare(novoValor, atual.valor);
            if (comparacao < 0) {
                if (atual.esquerdo == null) {
                    atual.esquerdo = novoNo;
                    return;
                }
                atual = atual.esquerdo;
            } else if (comparacao > 0) {
                if (atual.direito == null) {
                    atual.direito = novoNo;
                    return;
                }
                atual = atual.direito;
            } else {
                // Se for igual... o que faremos??? Será admitido alguns valores iguais? Vai ser acreditado que não teremos que nos preocupar com isso?
                return;
            }
        }
    }

    @Override
    public T pesquisar(T valor) {
        No<T> atual = raiz;

        while (atual != null) {
            int comparacao = comparador.compare(valor, atual.valor);

            if (comparacao == 0) {
                return atual.valor;
            }
            else if (comparacao < 0) {
                atual = atual.esquerdo;
            }
            else {
                atual = atual.direito;
            }
        }
        return null;
    }

    @Override
    public T pesquisar(T valor, Comparator comparador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T remover(T valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        return altura(raiz);
    }
    private int altura(No<T> no) {
        if (no == null) {
            return 0;
        }
        int alturaLadoEsquerdo = altura(no.esquerdo);
        int alturaLadoDireito = altura(no.direito);
        if (alturaLadoEsquerdo > alturaLadoDireito) {
            return alturaLadoEsquerdo + 1;
        } else {
            return alturaLadoDireito + 1;
        }
    }

    @Override
    public int quantidadeNos() {
        return quantidadeNos(raiz);
    }
    private int quantidadeNos(No<T> no) {
        if (no == null) {
            return 0;
        }
        return 1 + quantidadeNos(no.esquerdo) + quantidadeNos(no.direito);
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

