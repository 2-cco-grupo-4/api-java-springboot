package com.example.picmejava.service.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
