package com.example.picmejava.service.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AtualizarUsuarioDTO {

    @Size(max = 32, min = 1)
    private String nome;

    @Size(max = 32, min = 8)
    private String senha;

    @Size(max = 15, min = 11)
    private String numCelular;
    @Past
    private LocalDate dataNasc;
    private String tokenSolicitacao;

}

