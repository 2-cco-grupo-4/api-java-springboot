package com.example.picmejava;

import java.util.ArrayList;
import java.util.List;
public class Tema {
    private String titulo;
    private List<Album> albuns;

    public Tema(String titulo) {
        this.titulo = titulo;
        this.albuns = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    // Adicionar album á um Tema
    public void adicionarAlbum(Album album) {
        this.albuns.add(album);
    }
}
