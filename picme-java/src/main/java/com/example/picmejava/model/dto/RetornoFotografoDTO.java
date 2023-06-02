package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoFotografoDTO {

    private Long id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
    private List<PerfilAlbumDTO> albums;
    private List<PerfilTemaDTO> temas;
}
