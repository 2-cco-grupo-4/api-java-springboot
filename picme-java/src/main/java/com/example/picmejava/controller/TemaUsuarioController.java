package com.example.picmejava.controller;

import com.example.picmejava.model.dto.CadastroTemaUsuarioDTO;
import com.example.picmejava.model.dto.RetornoTemaUsuarioDTO;
import com.example.picmejava.service.TemaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema-usuario")
public class TemaUsuarioController {

    @Autowired
    private TemaUsuarioService temaUsuarioService;

    @PostMapping
    public ResponseEntity<RetornoTemaUsuarioDTO> cadastrar(@RequestBody CadastroTemaUsuarioDTO novoTemaUsuario){
        return ResponseEntity.status(201).body(temaUsuarioService.cadastrar(novoTemaUsuario));
    }
}
