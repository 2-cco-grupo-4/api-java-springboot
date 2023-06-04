package com.example.picmejava.service.album.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroAlbumDTO {

    private String titulo;
    private String descricao;
    private Long idTema;
    private Long idFotografo;
}
