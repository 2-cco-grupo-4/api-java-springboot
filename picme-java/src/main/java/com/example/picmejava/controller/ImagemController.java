package com.example.picmejava.controller;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.ImagemService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/imagens")
public class ImagemController {

    ImagemService serviceImagem = new ImagemService();

    @PostMapping("/{idAlbum}")
    public Imagem cadastrar(@PathVariable Integer idAlbum, @RequestBody Imagem novaImagem){
        return serviceImagem.cadastrar(idAlbum, novaImagem);
    }

}
