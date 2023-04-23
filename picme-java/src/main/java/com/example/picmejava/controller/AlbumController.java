package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/{id}")

    public ResponseEntity<Album> cadastrar(@PathVariable Integer id, @RequestBody Album novoAlbum)throws Exception{
        albumService.cadastrar(id, novoAlbum);
        return ResponseEntity.status(201).body(novoAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Integer id, @RequestBody @Valid Album albumAtualizado) throws Exception {
        return ResponseEntity.status(200).body(albumService.atualizar(id, albumAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deletar(@PathVariable int id) throws Exception{
        albumService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Album>> listarAlbumsFotografo(@PathVariable Integer id){
        return ResponseEntity.status(200).body(albumService.listar(id).toList());
    }

}
