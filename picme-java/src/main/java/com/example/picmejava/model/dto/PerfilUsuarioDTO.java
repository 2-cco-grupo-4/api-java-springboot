package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilUsuarioDTO {
    private Long id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
}
