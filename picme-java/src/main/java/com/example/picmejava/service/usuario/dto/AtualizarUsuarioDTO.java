package com.example.picmejava.service.usuario.dto;

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

