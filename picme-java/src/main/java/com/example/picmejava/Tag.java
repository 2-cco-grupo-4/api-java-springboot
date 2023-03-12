package com.example.picmejava;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private String titulo;
    private List<Imagem> imagens;

    public Tag(String titulo) {
        this.titulo = titulo;
        this.imagens = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

}
