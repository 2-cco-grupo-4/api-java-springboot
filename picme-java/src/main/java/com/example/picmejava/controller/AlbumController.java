package com.example.picmejava.controller;

import com.example.picmejava.model.Album;
import com.example.picmejava.service.album.dto.AtualizarAlbumDTO;
import com.example.picmejava.service.album.dto.CadastroAlbumDTO;
import com.example.picmejava.service.album.dto.CapaAlbumDTO;
import com.example.picmejava.service.album.dto.RetornoAlbumDTO;
import com.example.picmejava.service.album.AlbumService;

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
    public ResponseEntity<List<RetornoAlbumDTO>> listar(@RequestParam Long idFotografo){
        if (albumService.listarAlbunsFotografo(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(albumService.listarAlbunsFotografo(idFotografo));
    }

    @Operation(summary = "Listar capa de albuns de um fotógrafo", description = "Passando o ID fotógrafo podemos istar capa de albuns")
    @GetMapping("/capa")
    public ResponseEntity<List<CapaAlbumDTO>> listarCapaAlbunsFotografo(@RequestParam Long idFotografo){
        if (albumService.listarCapaAlbum(idFotografo).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(albumService.listarCapaAlbum(idFotografo));
    }
    @Operation(summary = "Listar albuns", description = "Podemos obter a lista de todos os albuns com árvore Binária ")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/listar-arvore")
    public ResponseEntity<List<RetornoAlbumDTO>> listarArvore(@RequestParam Long idFotografo){
        return ResponseEntity.status(200).body(albumService.listarArvore(idFotografo));
    }


    @Operation(summary = "Obter album", description = "Passando o ID do album podemos obter todas as suas informações")
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/album")
    public ResponseEntity<RetornoAlbumDTO> obterAlbum(@RequestParam Long idAlbum){
        return ResponseEntity.status(200).body(albumService.buscarPorIdRetornoAlbumDTO(idAlbum));
    }

//    @GetMapping("/album-arvore")
//    public ResponseEntity<BinaryTree> listarAlbumArvore(){
//        BinaryTree arvore = new BinaryTree();
//        List<RetornoAlbumDTO> albuns = albumService.listar();
//
//        for (RetornoAlbumDTO album : albuns) {
//            arvore.insert(album);
//        }
//
//        return ResponseEntity.ok(arvore);
//    }
//

}
