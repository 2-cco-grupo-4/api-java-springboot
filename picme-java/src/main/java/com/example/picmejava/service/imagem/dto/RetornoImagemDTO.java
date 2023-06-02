package com.example.picmejava.service.imagem.dto;

import com.example.picmejava.service.album.dto.PerfilAlbumDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoImagemDTO {
    private Long id;
    private String path;
    private String tipo;
    private String descricao;
    private PerfilAlbumDTO album;
}
