package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CadastroUsuarioDTO {

    private String nome;
    private String cpf;
    private LocalDate dataNasc;
    private String email;
    private String senha;
    private String numCelular;
}
