package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.imagem.dto.PerfilImagemDTO;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;

public class ImagemMapper {


    public PerfilImagemDTO toPerfilImagemDTO(Imagem dados){
        PerfilImagemDTO dto = new PerfilImagemDTO();

        dto.setId(dados.getId());
        dto.setPath(dados.getMediaUrl());
        dto.setTipo(dados.getMediaType());
        dto.setDescricao(dados.getCaption());

        return dto;
    }

    public RetornoImagemDTO toRetornoImagemDTO(Imagem dados){
        RetornoImagemDTO dto = new RetornoImagemDTO();

        dto.setAlbum(AlbumMapper.toAlbumDTO(dados.getIdAlbum()));
        dto.setDescricao(dados.getCaption());
        dto.setTipo(dados.getMediaType());
        dto.setPath(dados.getMediaUrl());
        dto.setId(dados.getId());

        return dto;
    }
}
