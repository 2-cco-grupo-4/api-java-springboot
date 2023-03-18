package com.example.picmejava.model;

public class Album {
    private Integer id;
    private String titulo;
    private String tipo;
    private String descricao;
    private Integer idFotografo;

    public Album(String titulo, String tipo, String descricao, Integer idFotografo) {
        this.id = (int) (Math.random() * 10000);
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idFotografo = idFotografo;
    }

    public Integer getIdFotografo() {
        return idFotografo;
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setIdFotografo(Integer idFotografo) {
        this.idFotografo = idFotografo;
    }
}
