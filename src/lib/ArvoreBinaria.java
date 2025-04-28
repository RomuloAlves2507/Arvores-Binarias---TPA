package lib;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.Node;

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
        No<T> atual = raiz;
        No<T> pai = raiz;
        boolean filho_esquerdo = true;

        while (atual != null && comparador.compare(valor, atual.getValor()) != 0) {
            pai = atual;
            if (comparador.compare(valor, atual.getValor()) < 0)
            {
                filho_esquerdo = true;
                atual = atual.getEsquerdo();
            } else {
                filho_esquerdo = false;
                atual = atual.getDireito();
            }
        }

        if (atual == null) {return null;}

        // CASO 1 : *** NÓ SEM FILHOS (É UMA FOLHA) ***
        if (atual.getEsquerdo() == null && atual.getDireito() == null) {
            if (atual == raiz) raiz = null;
            else if (filho_esquerdo) pai.setEsquerdo(null);
            else pai.setDireito(null);
        }
        
        // CASO 2 : *** NÓ COM FILHOS APENAS NA ESQUERDA ***
        else if (atual.getDireito() == null) {
            if (atual == raiz) {
                raiz = atual.getEsquerdo();
            } else if (filho_esquerdo) {
                pai.setEsquerdo(atual.getEsquerdo());
            } else {
                pai.setDireito(atual.getEsquerdo());
            }
        }

        // CASO 3 : *** NÓ COM FILHOS APENAS NA DIREITA ***
        else if (atual.getEsquerdo() == null) {
            if (atual == raiz) {
                raiz = atual.getDireito();
            } else if (filho_esquerdo) {
                pai.setEsquerdo(atual.getDireito());
            } else {
                pai.setDireito(atual.getDireito());
            }
        }

        // CASO 4 : *** NÓ COM FILHOS EM AMBOS LADOS ***
        else {
            No<T> successor = no_sucessor(atual);
            if (atual == raiz) raiz = successor;
            else if (filho_esquerdo) pai.setEsquerdo(successor);
            else pai.setDireito(successor);
            successor.setEsquerdo(atual.getEsquerdo());
        }

        return atual.getValor(); 
    }

    private No<T> no_sucessor(No<T> apagar) { 
        No<T> parentOfSuccessor = apagar;
        No<T> successor = apagar;
        No<T> current = apagar.getDireito();
    
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.getEsquerdo();
        }
    
        if (successor != apagar.getDireito()) {
            parentOfSuccessor.setEsquerdo(successor.getDireito());
            successor.setDireito(apagar.getDireito());
        }

        return successor;    
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

    /**
     * Retorna uma String contendo os elementos da árvore em nível (Raiz -> Filhos -> Níveis subsequentes).
     *
     * @return uma String com os elementos da árvore em nível
     */
    @Override
    public String caminharEmNivel() {
        StringBuilder sb = new StringBuilder();
        if (raiz != null) {
            Queue<No<T>> fila = new LinkedList<>();
            fila.add(raiz);  
            caminharEmNivel(fila, sb); 
        }
        return sb.toString().trim();
    }
    
    /**
     * Método auxiliar recursivo que realiza o percurso em nível na árvore binária,
     * adicionando os valores visitados ao StringBuilder fornecido.
     * 
     * @param fila a fila que contém os nós da árvore a serem visitados
     * @param sb o StringBuilder usado para acumular os valores
     */
    private void caminharEmNivel(Queue<No<T>> fila, StringBuilder sb) {
        if (fila.isEmpty()) return;

        No<T> atual = fila.poll();  
        sb.append(atual.getValor()).append(" "); 
    
        if (atual.getEsquerdo() != null) fila.add(atual.getEsquerdo());
        if (atual.getDireito() != null) fila.add(atual.getDireito());

        caminharEmNivel(fila, sb);
    }
    

    /**
     * Retorna uma String contendo os elementos da árvore em ordem (Esquerda -> Raiz -> Direita).
     *
     * @return uma String com os elementos da árvore em ordem
     */
    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder();
        caminharEmOrdem(raiz, sb);
        return sb.toString().trim();
    }

    /**
     * Método auxiliar recursivo que realiza o percurso em ordem na árvore binária,
     * adicionando os valores visitados ao StringBuilder fornecido.
     *
     * @param no o nó atual da árvore sendo visitado
     * @param sb o StringBuilder usado para acumular os valores
     */
    private void caminharEmOrdem(No<T> no, StringBuilder sb) {
        if (no == null) return;

        caminharEmOrdem(no.getEsquerdo(), sb);
        sb.append(no.getValor()).append(" ");
        caminharEmOrdem(no.getDireito(), sb);
    }
}

