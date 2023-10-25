package com.example.picmejava.service.imagem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedImagemDTO {
    private Long imagemId;
    private String path;
    private Long albumId;
    private String fotografo;

    public FeedImagemDTO(Long imagemId, String path, Long albumId, String fotografo) {
        this.imagemId = imagemId;
        this.path = path;
        this.albumId = albumId;
        this.fotografo = fotografo;
    }
}
