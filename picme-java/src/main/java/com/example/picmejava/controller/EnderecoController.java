package com.example.picmejava.controller;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco novoEndereco){
        return ResponseEntity.status(201).body(enderecoService.cadastrar(novoEndereco));
    }
}
