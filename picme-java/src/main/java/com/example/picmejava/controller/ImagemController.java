package com.example.picmejava.controller;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.imagem.dto.CadastroImagemDTO;
import com.example.picmejava.service.imagem.dto.FeedImagemDTO;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import com.example.picmejava.service.imagem.ImagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping
    public ResponseEntity<RetornoImagemDTO> cadastrar(@RequestBody CadastroImagemDTO novaImagem){
        return ResponseEntity.status(201).body(imagemService.cadastrar(novaImagem.getIdAlbum(), novaImagem));
    }

    @Operation(summary = "Listar imagens", description = "Passando o ID do album, podemos listar todas as imagens de determinado album")
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<RetornoImagemDTO>> listar(){
        return ResponseEntity.status(200).body(imagemService.listar());
    }

    @Operation(summary = "Listar paths das imagens e IDs dos álbuns")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/paths")
    public ResponseEntity<List<FeedImagemDTO>> listarFeed() {
        List<FeedImagemDTO> pathsAndIds = imagemService.listarFeed();
        return ResponseEntity.status(200).body(pathsAndIds);
    }

    @Operation(summary = "Remover uma imagem", description = "Passando o ID da imagem, podemos excluir determinada imagem")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        imagemService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping(
            value = "{id}/imagem",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        imagemService.putImage(id, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(
            value = "{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public

}
