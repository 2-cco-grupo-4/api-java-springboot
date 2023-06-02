package com.example.picmejava.service.album.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilAlbumDTO {

    private Long id;
    private String titulo;
    private PerfilTemaDTO tema;
}
