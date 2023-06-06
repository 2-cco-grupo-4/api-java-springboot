package com.example.picmejava.service.avaliacao;

import com.example.picmejava.model.Avaliacao;
import com.example.picmejava.repository.AvaliacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;
    @InjectMocks
    private AvaliacaoService avaliacaoService = new AvaliacaoService();

    public AvaliacaoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve adicionar uma avaliação à pilha")
    void adicionarAvaliacaoNaPilha() {
        Avaliacao avaliacao = new Avaliacao();

        AvaliacaoService avaliacaoServiceMock = Mockito.mock(AvaliacaoService.class);

        Mockito.doNothing().when(avaliacaoServiceMock).avaliar(Mockito.any(Avaliacao.class));

        avaliacaoServiceMock.avaliar(avaliacao);

        List<Avaliacao> pilhaAtual = avaliacaoServiceMock.exibir();
        assertNotNull(pilhaAtual);

        assertEquals(avaliacao, pilhaAtual.get(0));
    }



    @Test
    @DisplayName("Deve remover a última avaliação adicionada à pilha")
    void removerUltimaAvaliacaoDaPilha() {
        Avaliacao avaliacaoExistente = new Avaliacao();

        AvaliacaoService avaliacaoServiceMock = Mockito.mock(AvaliacaoService.class);

        Mockito.doNothing().when(avaliacaoServiceMock).avaliar(Mockito.any(Avaliacao.class));
        avaliacaoServiceMock.avaliar(avaliacaoExistente);

        Mockito.when(avaliacaoServiceMock.desfazer()).thenReturn(avaliacaoExistente);
        Avaliacao resultado = avaliacaoServiceMock.desfazer();

        assertNotNull(resultado);
        assertEquals(avaliacaoExistente, resultado);

        List<Avaliacao> pilhaAtual = avaliacaoServiceMock.exibir();
        assertTrue(pilhaAtual.isEmpty());
    }


    @Test
    @DisplayName("Deve retornar a lista de avaliações da pilha")
    void retornarListaDeAvaliacoesDaPilha() {
        List<Avaliacao> avaliacoesExistentes = new ArrayList<>();
        Avaliacao avaliacao = new Avaliacao();
        avaliacoesExistentes.add(avaliacao);

        Mockito.when(avaliacaoRepository.findAll()).thenReturn(avaliacoesExistentes);
        avaliacaoService.avaliar(avaliacao);
        List<Avaliacao> resultado = avaliacaoService.exibir();
        assertNotNull(resultado);
        assertEquals(resultado.size(), avaliacoesExistentes.size());
    }

    @Test
    @DisplayName("Deve retornar exceção caso a última avaliação não seja adicionada a pilha")
    void retornarExcecaoCasoUltimaAvaliacaoNaoSejaAdicionadaAPilha() {
        assertThrows(IllegalStateException.class, () -> avaliacaoService.desfazer());
    }
}