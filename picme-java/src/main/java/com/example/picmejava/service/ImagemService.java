package com.example.picmejava.service;

import com.example.picmejava.model.Imagem;

import java.util.ArrayList;
import java.util.List;

public class ImagemService {

    private List<Imagem> imagems;

    public ImagemService() {
        this.imagems = new ArrayList<>();
    }

    public Imagem cadastrar(Integer idAlbum, Imagem novaImagem){
        novaImagem.setIdAlbum(idAlbum);
        imagems.add(novaImagem);

        return novaImagem;
    }
}
