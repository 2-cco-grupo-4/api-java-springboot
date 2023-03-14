package com.example.picmejava.model;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private Integer id;
    private String titulo;
    private String tipo;
    private String descricao;
    private List<Imagem> imagems;

    public Album(String titulo,
                 String tipo,
                 String descricao) {
        this.id = (int) (Math.random() * 10000);
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.imagems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
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

    public List<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(List<Imagem> imagems) {
        this.imagems = imagems;
    }
}
