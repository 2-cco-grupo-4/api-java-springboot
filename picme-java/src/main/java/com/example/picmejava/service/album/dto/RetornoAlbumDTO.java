package com.example.picmejava.service.album.dto;

import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;
import com.example.picmejava.service.imagem.dto.PerfilImagemDTO;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoAlbumDTO {

    private Long id;
    private String titulo;
    private PerfilTemaDTO tema;
    private PerfilFotografoDTO fotografo;
    private List<PerfilImagemDTO> imagems;

}
