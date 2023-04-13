package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilFotografoDTO {
    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
    private String tokenSolicitacao;
}
