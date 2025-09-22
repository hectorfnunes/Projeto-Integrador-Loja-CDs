package modelo;

import java.math.BigDecimal;

public class CD {
    private int id;
    private String titulo;
    private String artista;
    private String genero;
    private int ano;
    private BigDecimal preco;
    private int estoque;

    public CD() {}

    public CD(String titulo, String artista, String genero, int ano, BigDecimal preco, int estoque) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.ano = ano;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
}