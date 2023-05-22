package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilTemaDTO {

    private Integer id;
    private String nome;
    private List<PerfilUsuarioDTO> usuarios;
}
