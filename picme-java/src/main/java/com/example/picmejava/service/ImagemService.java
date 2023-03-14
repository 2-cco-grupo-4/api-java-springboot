package com.example.picmejava.service;

import com.example.picmejava.model.Imagem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagemService {

    private List<Imagem> imagems;

    public ImagemService() {
        this.imagems = new ArrayList<>();
    }

    public Imagem criarImagem(Imagem imagem){
        imagems.add(imagem);
        return imagem;
    }

    public List<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(List<Imagem> imagems) {
        this.imagems = imagems;
    }
}
