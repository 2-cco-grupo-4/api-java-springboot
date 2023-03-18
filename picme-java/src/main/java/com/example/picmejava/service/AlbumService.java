package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private List<Album> albums;

    public AlbumService() {
        this.albums = new ArrayList<>();
    }

    public Album cadastrar(Integer idFotografo, Album novoAlbum){
            novoAlbum.setIdFotografo(idFotografo);
            albums.add(novoAlbum);

            return novoAlbum;
    }

    public Album buscarPorId(Integer idAlbum){
        for (Album album : albums){
            if (album.getId().equals(idAlbum)){
                return album;
            }
        }
        return null;
    }

    public String deletar(Integer idAlbum){
        Album album = this.buscarPorId(idAlbum);
        if (!album.equals(null)){
            albums.remove(album);
            return "Album Removido!";
        }
        return "Album nao encontrado!";
    }
}
