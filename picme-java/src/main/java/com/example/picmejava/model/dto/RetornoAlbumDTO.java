package com.example.picmejava.model.dto;

import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoAlbumDTO {

    private Integer id;
    private String titulo;
    private Tema tema;
    private PerfilFotografoDTO fotografo;

}
