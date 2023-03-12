package com.example.picmejava;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String titulo;
    private String descricao;
    private Fotografo fotografo;
    private Tema tema;
    private List<Imagem> fotos;


    public Album(String titulo, String descricao, Fotografo fotografo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.fotografo = fotografo;
        this.fotos = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public List<Imagem> getFotos() {
        return fotos;
    }

    // Adicionar foto ao album
    public String adicionarFoto(Imagem foto) {
        this.fotos.add(foto);
        return "Foto adicionada ao álbum";
    }


}