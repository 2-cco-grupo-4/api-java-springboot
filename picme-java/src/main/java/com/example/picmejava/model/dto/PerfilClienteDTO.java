package com.example.picmejava.model.dto;

import lombok.Data;

@Data
public class PerfilClienteDTO {
    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
}
