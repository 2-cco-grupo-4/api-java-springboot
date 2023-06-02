package com.example.picmejava.service.usuario.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.album.dto.PerfilAlbumDTO;
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
