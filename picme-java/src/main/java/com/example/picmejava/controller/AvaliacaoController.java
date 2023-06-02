package com.example.picmejava.controller;


import com.example.picmejava.celia.Fila;
import com.example.picmejava.celia.Pilha;
import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController() {
        this.avaliacaoService = new AvaliacaoService();
    }


    @PostMapping("/avaliar")
    public ResponseEntity<Avaliacao> avaliar(@RequestBody  Avaliacao avaliacao) {
        avaliacaoService.avaliar(avaliacao);
        return ResponseEntity.status(201).body(avaliacao);
    }

    @GetMapping("/exibir")
    public ResponseEntity<List<Avaliacao>> exibir() {
        return  ResponseEntity.ok(avaliacaoService.exibir());
    }


    @GetMapping("/enfileirar")
    public ResponseEntity<Fila<Avaliacao>> verEmFila() {
        return ResponseEntity.status(200).body(avaliacaoService.enfileirarAvaliacoes());
    }


    @DeleteMapping("/desfazer")
    public ResponseEntity<Avaliacao> desfazer() {
        return ResponseEntity.status(200).body(avaliacaoService.desfazer());
    }


}
