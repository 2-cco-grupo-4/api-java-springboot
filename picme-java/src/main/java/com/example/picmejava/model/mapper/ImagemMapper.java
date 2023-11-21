package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.album.AlbumService;
import com.example.picmejava.service.imagem.dto.CadastroImagemDTO;
import com.example.picmejava.service.imagem.dto.PerfilImagemDTO;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

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

    public Imagem toImagem(CadastroImagemDTO dados, Album album) {
        Imagem imagem = new Imagem();

        imagem.setMediaUrl(dados.getMediaUrl());
        imagem.setIdAlbum(album);
        imagem.setOrigemImagem(dados.getOrigemImagem());
        imagem.setMediaType(dados.getMediaType());
        imagem.setPermalink(dados.getPermalink());
        imagem.setUpdatedAt(dados.getUpdatedAt());


        return imagem;
    }

//    public Imagem toImage(Long id, String imageId) {
//        Imagem imagem = new Imagem();
//
//        imagem.setOrigemImagem("S3");
//        imagem.setMediaUrl(imageId);
//        imagem.set
//
//        return imagem;
//    }


}
