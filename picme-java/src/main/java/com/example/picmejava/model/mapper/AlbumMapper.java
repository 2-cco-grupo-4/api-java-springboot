package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilAlbumDTO;
import com.example.picmejava.model.dto.AtualizarAlbumDTO;
import com.example.picmejava.model.dto.RetornoAlbumDTO;

public class AlbumMapper {

    FotografoMapper fotografoMapper = new FotografoMapper();
    ImagemMapper imagemMapper = new ImagemMapper();

    public Album toAlbum(Album dados, AtualizarAlbumDTO dadosAtualizados, Tema novoTema){

        if (novoTema != null){
            dados.setTema(novoTema);
        }
        if (dadosAtualizados.getTitulo() != null){
            dados.setTitulo(dadosAtualizados.getTitulo());
        }

        return dados;
    }

    public static PerfilAlbumDTO toAlbumDTO(Album dados){
        PerfilAlbumDTO dto = new PerfilAlbumDTO();

        dto.setId(dados.getId());
        dto.setTitulo(dados.getTitulo());
        dto.setTema(dados.getTema());

        return dto;
    }

    public RetornoAlbumDTO toRetornoAlbumDTO(Album dados){
        RetornoAlbumDTO dto = new RetornoAlbumDTO();

        dto.setId(dados.getId());
        dto.setTema(dados.getTema());
        dto.setTitulo(dados.getTitulo());
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(dados.getFotografo()));
        if (dados.getImagems() != null){
            dto.setImagems(dados.getImagems().stream().map((imagem) -> imagemMapper.toPerfilImagemDTO(imagem)).toList());
        }

        return dto;
    }
}
