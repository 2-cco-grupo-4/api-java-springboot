package com.example.picmejava.controller;

import com.example.picmejava.service.temaUsuario.dto.CadastroTemaClienteDTO;
import com.example.picmejava.service.temaUsuario.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.service.temaUsuario.dto.RetornoTemaClienteDTO;
import com.example.picmejava.service.temaUsuario.dto.RetornoTemaFotografoDTO;
import com.example.picmejava.service.temaUsuario.TemaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/escolha-tema")
public class TemaUsuarioController {

    @Autowired
    private TemaUsuarioService temaUsuarioService;

    @PostMapping("/fotografo")
    public ResponseEntity<RetornoTemaFotografoDTO> cadastrar(@RequestBody CadastroTemaFotografoDTO novoTemaFotografo){
        return ResponseEntity.status(201).body(temaUsuarioService.cadastrarTemaFotografo(novoTemaFotografo));
    }

    @PostMapping("/cliente")
    public ResponseEntity<RetornoTemaClienteDTO> cadastrar(@RequestBody CadastroTemaClienteDTO novoTemaCliente){
        return ResponseEntity.status(201).body(temaUsuarioService.cadastrarTemaCliente(novoTemaCliente));
    }
}
