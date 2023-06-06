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
    @NotBlank
    @Size(max = 32, min = 1)
    private String nome;
    @NotBlank
    @Size(max = 32, min = 8)
    private String senha;
    @NotBlank
    @Size(max = 15, min = 11)
    private String numCelular;
    @Past
    @NotBlank
    private LocalDate dataNasc;
}

