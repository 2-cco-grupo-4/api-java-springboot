package com.example.picmejava.service.avaliacao;

import com.example.picmejava.model.utils.FilaObj;
import com.example.picmejava.model.utils.PilhaObj;
import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.repository.AvaliacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Tag(name = "Avaliação Service", description = "APIs relacionadas a operações de avaliação")
@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    private PilhaObj<Avaliacao> pilha = new PilhaObj<>(10);

    @Operation(summary = "Realizar uma avaliação")
    public void avaliar(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
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

    @Operation(summary = "Enfilera as avaliações")
    public FilaObj<Avaliacao> enfileirarAvaliacoes() {
        List<Avaliacao> avaliacoes = exibir();
        FilaObj<Avaliacao> fila = new FilaObj<>(avaliacoes.size());

        for (Avaliacao avaliacao : avaliacoes) {
            fila.push(avaliacao);
        }

        return fila;
    }

}
