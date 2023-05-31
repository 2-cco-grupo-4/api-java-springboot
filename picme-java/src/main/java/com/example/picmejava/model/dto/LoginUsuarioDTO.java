package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioDTO {
    private String email;
    private String senha;
    private boolean isAutenticado;
}
