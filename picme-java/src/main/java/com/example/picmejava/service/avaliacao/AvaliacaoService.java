package com.example.picmejava.service.avaliacao;

import com.example.picmejava.celia.Pilha;
import com.example.picmejava.model.Avaliacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Avaliação Service", description = "APIs relacionadas a operações de avaliação")
public class AvaliacaoService {

    private Pilha<Avaliacao> pilha;

    public AvaliacaoService() {
        this.pilha = new Pilha<>(10);
    }

    @Operation(summary = "Realizar uma avaliação")
    public void avaliar(Avaliacao avaliacao) {
        pilha.push(avaliacao);
    }

    @Operation(summary = "Desfazer a última avaliação")
    public Avaliacao desfazer() {
        return pilha.pop();
    }

    @Operation(summary = "Exibir todas as avaliações")
    public List<Avaliacao> exibir() {
        return pilha.toList();
    }

}
