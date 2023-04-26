package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum TemaEnum {
    ANIVERSARIO("Aniversário"),
    CASAMENTO("Casamento"),
    DEBUTANTE("Debutante");

    @Schema(
            description = "ENUM do nome do tema",
            example = "CASAMENTO"
    )
    private String nome;

    private TemaEnum(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
