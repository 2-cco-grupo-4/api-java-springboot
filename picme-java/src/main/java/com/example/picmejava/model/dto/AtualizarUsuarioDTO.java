package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AtualizarUsuarioDTO {

    private String nome;
    private String senha;
    private String numCelular;
    private LocalDate dataNasc;
}

