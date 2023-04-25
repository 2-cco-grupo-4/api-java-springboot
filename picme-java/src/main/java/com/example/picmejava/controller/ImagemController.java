package com.example.picmejava.controller;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    public ImagemService imagemService;

    @PostMapping("/{id}")
    public ResponseEntity<Imagem> cadastrar(@PathVariable Integer idAlbum, @RequestBody Imagem novaImagem){
        imagemService.cadastrar(idAlbum, novaImagem);
        return ResponseEntity.status(201).body(novaImagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Imagem> remover(@PathVariable int idImagem) throws Exception {
        imagemService.deletar(idImagem);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lista<Imagem>> listar(@PathVariable Integer id){
        return ResponseEntity.status(200).body(imagemService.listar(id));
    }
}
