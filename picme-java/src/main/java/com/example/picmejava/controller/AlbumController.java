package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarAlbumDTO;
import com.example.picmejava.model.dto.CadastroAlbumDTO;
import com.example.picmejava.model.dto.RetornoAlbumDTO;
import com.example.picmejava.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Album Controller",
        description = "Controller responsável pela entidade Album"
)

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Operation(summary = "Cadastrar um novo album", description = "Passando o ID do fotógrafo e o JSON do album, podemos cadastrar um novo album")
    @SecurityRequirement(name = "Bearer")

    @PostMapping
    public ResponseEntity<RetornoAlbumDTO> cadastrar(@RequestBody @Valid CadastroAlbumDTO novoAlbum){
        return ResponseEntity.status(201).body(albumService.cadastrar(novoAlbum));
    }

    @Operation(summary = "Atualizar um album", description = "Passando o ID do album e o seus novos valores, podemos atualizá-lo")
    @SecurityRequirement(name = "Bearer")

    @PutMapping("/{id}")
    public ResponseEntity<RetornoAlbumDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarAlbumDTO albumAtualizado){
        return ResponseEntity.status(200).body(albumService.atualizar(id, albumAtualizado));
    }

    @Operation(summary = "Deletar um album", description = "Passando um ID de um album cadastrado, podemos deletá-lo")
    @SecurityRequirement(name = "Bearer")

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deletar(@PathVariable long id){
        albumService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Listar albuns fotógrafo", description = "Passando o ID do fotógrafo podemos obter a lista de todos os seus albuns")
    @SecurityRequirement(name = "Bearer")

    @GetMapping
    public ResponseEntity<List<RetornoAlbumDTO>> listar(){
        if (albumService.listar().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(albumService.listar());
    }

}
