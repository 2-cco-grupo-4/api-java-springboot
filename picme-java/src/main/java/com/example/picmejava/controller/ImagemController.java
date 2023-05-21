package com.example.picmejava.controller;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Imagem controller",
        description = "Controller responsável pela entidade imagem"
)

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    public ImagemService imagemService;

    @Operation(summary = "Cadastrar uma nova imagem", description = "Passando os dados necessário, podemos cadastrar uma nova imagem")
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/{idAlbum}")
    public ResponseEntity<Imagem> cadastrar(@PathVariable Integer idAlbum, @RequestBody Imagem novaImagem){
        imagemService.cadastrar(idAlbum, novaImagem);
        return ResponseEntity.status(201).body(novaImagem);
    }

    @Operation(summary = "Listar imagens", description = "Passando o ID do album, podemos listar todas as imagens de determinado album")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<Lista<Imagem>> listar(@PathVariable Integer id){
        return ResponseEntity.status(200).body(imagemService.listar(id));
    }

    @Operation(summary = "Remover uma imagem", description = "Passando o ID da imagem, podemos excluir determinada imagem")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Imagem> deletar(@PathVariable int idImagem) throws Exception {
        imagemService.deletar(idImagem);
        return ResponseEntity.status(204).build();
    }

}
