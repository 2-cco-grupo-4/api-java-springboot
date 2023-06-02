package com.example.picmejava.service.builder;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.album.dto.AtualizarAlbumDTO;
import com.example.picmejava.service.album.dto.CadastroAlbumDTO;

import java.util.List;

public class AlbumBuilder {

    public AlbumBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Album criarAlbum(){
        Album album = new Album();

        album.setTitulo("Album 01");
        album.setTema(TemaBuilder.criarTema());
        album.setFotografo(FotografoBuilder.criarFotografo());
        album.setDescricao("Descrição do album 01");

        return album;
    }

    public static AtualizarAlbumDTO criarAtualizarAlbum(){
        AtualizarAlbumDTO atualizarAlbum = new AtualizarAlbumDTO();

        atualizarAlbum.setIdTema(1L);
        atualizarAlbum.setTitulo("Album 01");

        return atualizarAlbum;
    }

    public static CadastroAlbumDTO criarCadastroAlbum(){
        CadastroAlbumDTO dto = new CadastroAlbumDTO();

        dto.setDescricao("Descrição do album 01");
        dto.setTitulo("Album 01");
        dto.setIdFotografo(1L);
        dto.setIdTema(1L);

        return dto;
    }

    public static List<Album> criarListaAlbum(){
        return List.of(
                new Album(1L, "Album 01", TemaBuilder.criarTema(),
                        "Descrição album 01", FotografoBuilder.criarFotografo(),null ),
                new Album(1L, "Album 02", TemaBuilder.criarTema(),
                        "Descrição album 02", FotografoBuilder.criarFotografo(),null ),
                new Album(1L, "Album 03", TemaBuilder.criarTema(),
                        "Descrição album 03", FotografoBuilder.criarFotografo(),null )
        );
    }
}
