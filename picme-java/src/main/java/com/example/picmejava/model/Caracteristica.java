package com.example.picmejava.model;

public class Caracteristica {
    private Integer id;
    private String categoria;

    public Caracteristica(String categoria) {
        this.id = (int) (Math.random() * 10000);
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
