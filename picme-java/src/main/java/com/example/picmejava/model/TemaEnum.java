package com.example.picmejava.model;

public enum TemaEnum {
    ANIVERSARIO("Aniversário"),
    CASAMENTO("Casamento"),
    DEBUTANTE("Debutante");

    private String nome;

    private TemaEnum(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
