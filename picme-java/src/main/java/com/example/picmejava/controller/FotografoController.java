package com.example.picmejava.controller;

import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.dto.PerfilFotografoDTO;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.service.FotografoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Fotografo Controller",
        description = "Controller responsável pela entidade Fotografo"
)

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private FotografoService serviceFotografo;
    private FotografoMapper fotografoMapper = new FotografoMapper();

    @Operation(summary = "Cadastrar um novo cliente", description = "Passando os dados necessários, podemos cadastrar um novo cliente")
    @PostMapping()
    public ResponseEntity<PerfilFotografoDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoFotografo){
        return ResponseEntity.status(201).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.cadastrar(novoFotografo)
        ));
    }

    @GetMapping ResponseEntity<List<PerfilFotografoDTO>> listar(){
        return ResponseEntity.status(200).body(serviceFotografo.listar().stream().map(
                        fotografo -> fotografoMapper.toPerfilFotogradoDTO(fotografo))
                .toList());
    }

    @PutMapping("/atualizar/{idFotografo}")
    public ResponseEntity<PerfilFotografoDTO> atualizar(
            @PathVariable Integer idFotografo, @RequestBody @Valid AtualizarUsuarioDTO fotografoAtualizado
    ){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.atualizar(idFotografo, fotografoAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilFotografoDTO> login(@RequestBody LoginUsuarioDTO buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.login(buscarFotografo)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilFotografoDTO> logoff(@RequestBody LoginUsuarioDTO buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }
}
