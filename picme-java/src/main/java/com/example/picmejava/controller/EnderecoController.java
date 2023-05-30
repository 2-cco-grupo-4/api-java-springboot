package com.example.picmejava.controller;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;
import com.example.picmejava.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Endereço Controller",
        description = "Controller responsável pela entidade Endereço"
)
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Cadastrar um novo endereço", description = "Cadastra um novo endereço")
    @PostMapping
    public ResponseEntity<RetornoEnderecoDTO> cadastrar(@RequestBody Endereco novoEndereco){
        return ResponseEntity.status(201).body(enderecoService.cadastrar(novoEndereco));
    }

    @Operation(summary = "Listar endereços", description = "Obtém a lista de todos os endereços")
    @GetMapping
    public ResponseEntity<List<RetornoEnderecoDTO>> listar(){
        return ResponseEntity.status(200).body(enderecoService.listar());
    }
}
