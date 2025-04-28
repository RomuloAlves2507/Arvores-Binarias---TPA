package model;

public class Livro {
    private Integer id;
    private String nome;
    private String autor;
    private String categoria;

    public Livro(Integer id, String titulo, String autor, String categoria) {
        this.nome = titulo;
        this.autor = autor;
        this.id = id;
        this.categoria = categoria;
    }
    public String getNome() {
        return nome;
    }
    public String getAutor() {
        return autor;
    }
    public Integer getId() {
        return id;
    }
    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return ("id=" + id +
                ", nome=" + nome +
                ", autor=" + autor +
                ", categoria=" + categoria
        );
    }
}

