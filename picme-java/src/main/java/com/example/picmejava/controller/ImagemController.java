package com.example.picmejava.controller;

import com.example.picmejava.s3.S3;
import com.example.picmejava.service.imagem.ImagemService;
import com.example.picmejava.service.imagem.dto.CadastroImagemDTO;
import com.example.picmejava.service.imagem.dto.FeedImagemDTO;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ImagemController.class);

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

    @Operation(summary = "Forçar teste de imagens de um album para o feed", description = "Passando o ID do album, podemos listar todas as imagens de determinado album")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/feed/{id}")
    public ResponseEntity<List<FeedImagemDTO>> listarFeed(@PathVariable Long id){
        return ResponseEntity.status(200).body(imagemService.listarFeed(id));
    }

    @Operation(summary = "Remover uma imagem", description = "Passando o ID da imagem, podemos excluir determinada imagem")
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        imagemService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping(
            value = "/{id}/upload",
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
            value = "/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImage(
            @PathVariable Long id
    ) {
        logger.info("Recebendo requisição do id: " + id);
        byte[] image = imagemService.getImage(id);

        return ResponseEntity.ok(image);
    }

    @PostMapping(
            value = "album/{id}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadImageAlbum(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        imagemService.putImageAlbum(id, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(
            value = "album/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImageAlbum(
            @PathVariable Long id
    ) {
        logger.info("Recebendo requisição do id: " + id);
        byte[] image = imagemService.getImageAlbum(id);

        return ResponseEntity.ok(image);
    }

    @PostMapping(
            value = "album/{id}/multiUpload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadMultiImageAlbum(
            @PathVariable Long id,
            @RequestParam("file") List<MultipartFile> files
    ) {
        imagemService.putListImagemAlbum(id, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
