package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private List<Album> albums;

    public AlbumService() {
        this.albums = new ArrayList<>();
    }

    public Album adicionar(Album album){
        albums.add(album);
        return album;
    }

    public Album buscarPorId(Integer id){
        for (Album album : albums){
            if (id.equals(album.getId())){
                return album;
            }
        }
        return null;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
