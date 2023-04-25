package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping("/{id}")

    public ResponseEntity<Album> cadastrar(@PathVariable Integer id, @RequestBody Album novoAlbum)throws Exception{
        albumService.cadastrar(id, novoAlbum);
        return ResponseEntity.status(201).body(novoAlbum);
    }

    @Operation(summary = "Atualizar um album", description = "Passando o ID do album e o seus novos valores, podemos atualizá-lo")
    @PutMapping("/{id}")
    public ResponseEntity<Album> atualizar(@PathVariable Integer id, @RequestBody @Valid Album albumAtualizado) throws Exception {
        return ResponseEntity.status(200).body(albumService.atualizar(id, albumAtualizado));
    }

    @Operation(summary = "Deletar um album", description = "Passando um ID de um album cadastrado, podemos deletá-lo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deletar(@PathVariable int id) throws Exception{
        albumService.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Listar albuns fotógrafo", description = "Passando o ID do fotógrafo podemos obter a lista de todos os seus albuns")
    @GetMapping("/{id}")
    public ResponseEntity<List<Album>> listarAlbumsFotografo(@PathVariable Integer id){
        return ResponseEntity.status(200).body(albumService.listar(id).toList());
    }

}
