package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario implements Identificavel{
    private String tokenSolicitacao;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Album> albums;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo");
    }

    @Override
    public void adicionar(Tema tema) {
        if (getTemas() == null){
            setTemas(new ArrayList<>());
        }
        getTemas().add(tema);
    }

    public void adicionarAlbum(Album album){
        if (getAlbums() == null){
            setAlbums(new ArrayList<>());
        }
        getAlbums().add(album);
    }
}
