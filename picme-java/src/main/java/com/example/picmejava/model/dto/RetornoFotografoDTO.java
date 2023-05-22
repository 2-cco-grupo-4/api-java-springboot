package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoFotografoDTO {

    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
    private List<PerfilAlbumDTO> albums;
}
