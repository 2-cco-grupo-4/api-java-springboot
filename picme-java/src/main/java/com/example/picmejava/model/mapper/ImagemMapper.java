package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.dto.PerfilImagemDTO;
import com.example.picmejava.model.dto.RetornoImagemDTO;

public class ImagemMapper {


    public PerfilImagemDTO toPerfilImagemDTO(Imagem dados){
        PerfilImagemDTO dto = new PerfilImagemDTO();

        dto.setId(dados.getId());
        dto.setPath(dados.getPath());
        dto.setTipo(dados.getTipo());
        dto.setDescricao(dados.getDescricao());

        return dto;
    }

    public RetornoImagemDTO toRetornoImagemDTO(Imagem dados){
        RetornoImagemDTO dto = new RetornoImagemDTO();

        dto.setAlbum(AlbumMapper.toAlbumDTO(dados.getIdAlbum()));
        dto.setDescricao(dados.getDescricao());
        dto.setTipo(dados.getTipo());
        dto.setPath(dados.getPath());
        dto.setId(dados.getId());

        return dto;
    }
}
