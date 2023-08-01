package com.example.picmejava.service.usuario.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
public class CadastroUsuarioDTO {

    @NotBlank
    @Size(max = 120, min = 3)
    private String nome;
    @CPF
    @NotBlank
    private String cpf;
    @Past
    private LocalDate dataNasc;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 32, min = 8)
    private String senha;
    @NotBlank
    @Size(max = 15, min = 10)
    private String numCelular;
}
