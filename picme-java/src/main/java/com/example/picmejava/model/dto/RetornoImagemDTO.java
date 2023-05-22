package com.example.picmejava.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoImagemDTO {
    private Integer id;
    private String path;
    private String tipo;
    private String descricao;
    private PerfilAlbumDTO album;
}