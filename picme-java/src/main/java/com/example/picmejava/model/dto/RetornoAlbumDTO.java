package com.example.picmejava.model.dto;

import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoAlbumDTO {

    private Integer id;
    private String titulo;
    private Tema tema;
    private PerfilFotografoDTO fotografo;
    private List<PerfilImagemDTO> imagems;

}
