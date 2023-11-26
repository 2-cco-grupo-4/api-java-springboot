package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.Tema;
import com.example.picmejava.service.album.dto.*;

import java.util.List;

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
        TemaMapper.toPerfilTemaDTO(dados.getTema());
        dto.setTema(TemaMapper.toPerfilTemaDTO(dados.getTema()));

        return dto;
    }

    public RetornoAlbumDTO toRetornoAlbumDTO(Album dados){
        RetornoAlbumDTO dto = new RetornoAlbumDTO();

        dto.setId(dados.getId());
        dto.setTema(TemaMapper.toPerfilTemaDTO(dados.getTema()));
        dto.setTitulo(dados.getTitulo());
        dto.setFotografo(fotografoMapper.toPerfilFotogradoDTO(dados.getFotografo()));
        if (dados.getImagems() != null){
            dto.setImagems(dados.getImagems().stream().map((imagem) -> imagemMapper.toPerfilImagemDTO(imagem)).toList());
        }

        return dto;
    }

    public Album toAlbum(CadastroAlbumDTO dados, Fotografo fotografo, Tema tema) {
        Album album = new Album();

        album.setFotografo(fotografo);
        album.setDescricao(dados.getDescricao());
        album.setTitulo(dados.getTitulo());
        album.setTema(tema);

        return album;
    }

    public CapaAlbumDTO toCapaAlbumDto(Album dados){
        CapaAlbumDTO dto = new CapaAlbumDTO();

        dto.setIdAlbum(dados.getId());
        if (dados.getImagems().size() > 0){
            dto.setPathCapa(dados.getImagems().get(0).getMediaUrl());
            dto.setOrigemImagem(dados.getImagems().get(0).getOrigemImagem());
            dto.setIdImagem(dados.getImagems().get(0).getId());
        }
//        dto.setPathCapa(dados.getImagems().get(0).getMediaUrl());

        return dto;
    }

    public Album objectToAlbum(Object[] object) {
        return new Album((Long) object[0], (String) object[1], (String) object[2], (Tema) object[3], (Fotografo) object[4], (List<Imagem>) object[5]);
    }
}
