package com.example.picmejava.controller;


import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.model.utils.FilaObj;
import com.example.picmejava.service.avaliacao.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Avaliação Controller",
        description = "Controller responsável pela entidade Avaliação"
)
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;


    @Operation(summary = "Avaliar", description = "Realiza uma avaliação")
    @PostMapping
    public ResponseEntity<Avaliacao> avaliar(@RequestBody  Avaliacao avaliacao) {
        avaliacaoService.avaliar(avaliacao);
        return ResponseEntity.status(201).body(avaliacao);
    }

    @Operation(summary = "Exibir avaliações", description = "Obtém a lista de todas as avaliações")
    @GetMapping
    public ResponseEntity<List<Avaliacao>> exibir() {
        return  ResponseEntity.ok(avaliacaoService.exibir());
    }

    @Operation(summary = "Exibir avaliações enfileiradas",
            description = "Obtém a lista de todas as avaliações de forma enfileirada")
    @GetMapping("/enfileira")
    public ResponseEntity<FilaObj<Avaliacao>> verEmFila() {
        return ResponseEntity.status(200).body(avaliacaoService.enfileirarAvaliacoes());
    }

    @Operation(summary = "Desfazer avaliação", description = "Desfaz a última avaliação realizada")
    @DeleteMapping("/desfaz")
    public ResponseEntity<Avaliacao> desfazer() {
        return ResponseEntity.status(200).body(avaliacaoService.desfazer());
    }


}
