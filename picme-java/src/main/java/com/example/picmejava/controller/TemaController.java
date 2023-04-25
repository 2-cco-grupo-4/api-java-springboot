package com.example.picmejava.controller;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Tema;
import com.example.picmejava.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temas")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @PostMapping
    public ResponseEntity<Tema> cadastrar(@RequestBody Tema novoTema){
        return ResponseEntity.status(200).body(temaService.cadastrar(novoTema));
    }

    @GetMapping
    public ResponseEntity<List<Tema>> listar() throws Exception{
        return ResponseEntity.status(200).body(temaService.listar());
    }
}
