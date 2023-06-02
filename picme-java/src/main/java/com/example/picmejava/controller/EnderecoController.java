package com.example.picmejava.controller;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.dto.CadastroEnderecoDTO;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;
import com.example.picmejava.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<RetornoEnderecoDTO> cadastrar(@RequestBody @Valid CadastroEnderecoDTO novoEndereco){
        return ResponseEntity.status(201).body(enderecoService.cadastrar(novoEndereco));
    }

    @GetMapping
    public ResponseEntity<List<RetornoEnderecoDTO>> listar(){
        return ResponseEntity.status(200).body(enderecoService.listar());
    }
}
