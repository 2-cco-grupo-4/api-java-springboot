package com.example.picmejava.controller;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.ImagemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private ImagemService imagemService;

    public ImagemController(ImagemService imagemService) {
        this.imagemService = imagemService;
    }

    @PostMapping()
    public Imagem criarImagem(@RequestBody Imagem imagem){
        return imagemService.criarImagem(imagem);
    }
}
