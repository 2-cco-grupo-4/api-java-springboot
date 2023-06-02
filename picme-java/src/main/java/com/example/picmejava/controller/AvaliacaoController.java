package com.example.picmejava.controller;


import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.service.avaliacao.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController() {
        this.avaliacaoService = new AvaliacaoService();
    }


    @GetMapping("/avaliar")
    public ResponseEntity<Avaliacao> avaliar(Avaliacao avaliacao) {
        avaliacaoService.avaliar(avaliacao);
        return ResponseEntity.status(201).body(avaliacao);
    }

    @GetMapping("/exibir")
    public ResponseEntity<List<Avaliacao>> exibir() {
        return  ResponseEntity.ok(avaliacaoService.exibir());
    }

    @DeleteMapping("/desfazer")
    public ResponseEntity<Avaliacao> desfazer() {
        return ResponseEntity.status(200).body(avaliacaoService.desfazer());
    }


}
