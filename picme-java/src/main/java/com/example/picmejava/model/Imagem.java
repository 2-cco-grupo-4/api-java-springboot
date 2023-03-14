package com.example.picmejava.model;

public class Imagem {

    private Integer id;
    private String path;
    private String tipo;
    private String descricao;

    public Imagem(String path, String tipo, String descricao) {
        this.id = (int) (Math.random() * 1000);
        this.path = path;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

}
