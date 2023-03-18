package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.AlbumService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private AlbumService serviceAlbum;

    public AlbumController() {
        this.serviceAlbum = new AlbumService();
    }

    @PostMapping("/{idFotografo}")
    public Album cadastrar(@PathVariable Integer idFotografo, @RequestBody Album album) throws Exception{
        return serviceAlbum.cadastrar(idFotografo, album);
    }
}
