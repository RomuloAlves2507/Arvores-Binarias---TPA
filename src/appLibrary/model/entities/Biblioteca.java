package appLibrary.model.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Livro> livros;


    public Biblioteca(){
        livros = new ArrayList<Livro>();
        usuarios = new ArrayList<Usuario>();
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public ArrayList<Livro> getLivros() {
        return livros;
    }


    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public void adicionarLivro(Livro livro){
        
    }

    public void adicionarUsuario(Usuario usuario){

    }
    
    public void buscarLivro(String titulo){

    }

    public void buscarLivro(String titulo){

    }
}
