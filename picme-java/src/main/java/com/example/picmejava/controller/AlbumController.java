package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    public ResponseEntity<Album> cadastrar(@RequestBody @Valid Album novoAlbum){
        albumService.cadastrar(novoAlbum);
        return ResponseEntity.status(201).body(novoAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Integer id, @RequestBody @Valid Album albumAtualizado) throws Exception {
        return ResponseEntity.status(200).body(albumService.atualizar(id, albumAtualizado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable Integer id) throws Exception {
        return ResponseEntity.status(200).body(albumService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deletar(@PathVariable int id) throws Exception{
        return ResponseEntity.status(200).body(albumService.deletar(id));
    }


}
