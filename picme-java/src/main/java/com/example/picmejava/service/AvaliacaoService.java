package com.example.picmejava.service;

import com.example.picmejava.celia.Fila;
import com.example.picmejava.celia.Pilha;
import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    private Pilha<Avaliacao> pilha;

    public AvaliacaoService() {
        this.pilha = new Pilha<>(10);
    }

    public void avaliar(Avaliacao avaliacao) {
        avaliacaoRepository.save(avaliacao);
        pilha.push(avaliacao);
    }

    public Avaliacao desfazer() {
        return pilha.pop();
    }

    public List<Avaliacao> exibir() {
        return pilha.toList();
    }

    public Fila<Avaliacao> enfileirarAvaliacoes() {
        List<Avaliacao> avaliacoes = exibir();
        Fila<Avaliacao> fila = new Fila<>(avaliacoes.size());

        for (Avaliacao avaliacao : avaliacoes) {
            fila.push(avaliacao);
        }

        return fila;
    }



}
