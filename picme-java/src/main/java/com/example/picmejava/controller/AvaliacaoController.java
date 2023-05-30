package com.example.picmejava.controller;

import com.example.picmejava.celia.Pilha;
import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Avaliação Controller",
        description = "Controller responsável pela entidade Avaliação"
)
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private AvaliacaoService avaliacaoService;

    public AvaliacaoController() {
        this.avaliacaoService = new AvaliacaoService();
    }

    @Operation(summary = "Avaliar", description = "Realiza uma avaliação")
    @GetMapping("/avaliar")
    public ResponseEntity<Avaliacao> avaliar(@Valid Avaliacao avaliacao) {
        avaliacaoService.avaliar(avaliacao);
        return ResponseEntity.status(201).body(avaliacao);
    }

    @Operation(summary = "Exibir avaliações", description = "Obtém a lista de todas as avaliações")
    @GetMapping("/exibir")
    public ResponseEntity<List<Avaliacao>> exibir() {
        return ResponseEntity.ok(avaliacaoService.exibir());
    }

    @Operation(summary = "Desfazer avaliação", description = "Desfaz a última avaliação realizada")
    @DeleteMapping("/desfazer")
    public ResponseEntity<Avaliacao> desfazer() {
        return ResponseEntity.status(200).body(avaliacaoService.desfazer());
    }
}
