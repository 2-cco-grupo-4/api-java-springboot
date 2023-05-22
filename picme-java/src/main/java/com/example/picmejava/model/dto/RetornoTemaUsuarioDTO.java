package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoTemaUsuarioDTO {

    private List<PerfilTemaDTO> temas;
    private PerfilUsuarioDTO usuario;
}
