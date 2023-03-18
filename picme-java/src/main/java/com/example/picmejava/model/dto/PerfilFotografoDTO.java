package com.example.picmejava.model.dto;

import lombok.Data;

@Data
public class PerfilFotografoDTO {
    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
}
