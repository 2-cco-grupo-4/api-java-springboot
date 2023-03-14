package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.AlbumService;
import com.example.picmejava.service.ImagemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService serviceAlbum;
    private ImagemService serviceImagem;
    public AlbumController() {
        this.serviceAlbum = new AlbumService();
        this.serviceImagem = new ImagemService();
    }

    @PostMapping()
    public Album criarAlbum(@RequestBody Album album){
        return serviceAlbum.adicionar(album);
    }

    @PatchMapping("/{id}")
    public Album adicionarImagemAoAlbum(@PathVariable Integer id, @RequestBody Imagem imagem){
        Album album = serviceAlbum.buscarPorId(id);
        if (!album.equals(null)){
            imagem = serviceImagem.criarImagem(imagem);
            album.getImagems().add(imagem);
            album = serviceAlbum.adicionar(album);
            return album;
        }else {
            return null;
        }
    }



}
