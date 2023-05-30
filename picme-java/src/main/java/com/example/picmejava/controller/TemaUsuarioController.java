package com.example.picmejava.controller;

import com.example.picmejava.model.dto.CadastroTemaClienteDTO;
import com.example.picmejava.model.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.model.dto.RetornoTemaClienteDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;
import com.example.picmejava.service.TemaUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escolha-tema")
@Tag(
        name = "Tema do Usuário Controller",
        description = "Controller responsável pela escolha de temas do usuário"
)
public class TemaUsuarioController {

    @Autowired
    private TemaUsuarioService temaUsuarioService;

    @Operation(summary = "Cadastrar tema de fotógrafo", description = "Cadastra um novo tema para fotógrafo")
    @PostMapping("/fotografo")
    public ResponseEntity<RetornoTemaFotografoDTO> cadastrar(@RequestBody CadastroTemaFotografoDTO novoTemaFotografo){
        return ResponseEntity.status(201).body(temaUsuarioService.cadastrarTemaFotografo(novoTemaFotografo));
    }

    @Operation(summary = "Cadastrar tema de cliente", description = "Cadastra um novo tema para cliente")
    @PostMapping("/cliente")
    public ResponseEntity<RetornoTemaClienteDTO> cadastrar(@RequestBody CadastroTemaClienteDTO novoTemaCliente){
        return ResponseEntity.status(201).body(temaUsuarioService.cadastrarTemaCliente(novoTemaCliente));
    }
}
