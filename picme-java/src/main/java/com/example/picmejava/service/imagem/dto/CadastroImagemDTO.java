package com.example.picmejava.service.imagem.dto;

import com.example.picmejava.model.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CadastroImagemDTO {

    public CadastroImagemDTO(String mediaUrl, String origemImagem, LocalDateTime updatedAt, Long idAlbum) {
        this.mediaUrl = mediaUrl;
        this.origemImagem = origemImagem;
        this.updatedAt = updatedAt;
        this.idAlbum = idAlbum;
    }

    private String mediaUrl;
    private String permalink;
    private String mediaType;
    private String origemImagem;
    private LocalDateTime updatedAt;
    private Long idAlbum;

}
