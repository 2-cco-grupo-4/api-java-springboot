package com.example.picmejava.service;

import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.celia.Pilha;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoServiceTest {

    private AvaliacaoService avaliacaoService = new AvaliacaoService();

    @Test
    @DisplayName("Deve adicionar uma avaliação à pilha")
    void adicionarAvaliacaoNaPilha() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacaoService.avaliar(avaliacao);
        List<Avaliacao> pilhaAtual = avaliacaoService.exibir();
        assertNotNull(pilhaAtual);
        assertEquals(1, pilhaAtual.size());
        assertEquals(avaliacao, pilhaAtual.get(0));
    }

    @Test
    @DisplayName("Deve remover a última avaliação adicionada à pilha")
    void removerUltimaAvaliacaoDaPilha() {
        Avaliacao avaliacaoExistente = new Avaliacao();
        avaliacaoService.avaliar(avaliacaoExistente);
        Avaliacao resultado = avaliacaoService.desfazer();
        assertNotNull(resultado);
        assertEquals(avaliacaoExistente, resultado);
        List<Avaliacao> pilhaAtual = avaliacaoService.exibir();
        assertTrue(pilhaAtual.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar a lista de avaliações da pilha")
    void retornarListaDeAvaliacoesDaPilha() {
        List<Avaliacao> avaliacoesExistentes = new ArrayList<>();
        Avaliacao avaliacao = new Avaliacao() ;
        avaliacao.setId(1L);
        avaliacao.setDescricao("òtima avaliação");
        avaliacao.setValor(10.00);
        avaliacoesExistentes.add(avaliacao);

        avaliacaoService.avaliar(avaliacao);
        List<Avaliacao> resultado = avaliacaoService.exibir();
        assertNotNull(resultado);
        assertEquals(avaliacoesExistentes.size(), resultado.size());
        assertEquals(avaliacoesExistentes, resultado);
    }
}
