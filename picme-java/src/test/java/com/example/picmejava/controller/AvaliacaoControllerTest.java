//package com.example.picmejava.controller;
//
//import com.example.picmejava.model.Avaliacao;
//import com.example.picmejava.model.utils.FilaObj;
//import com.example.picmejava.service.avaliacao.AvaliacaoService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//@ExtendWith(MockitoExtension.class)
//class AvaliacaoControllerTest {
//
//    @Mock
//    private AvaliacaoService avaliacaoService;
//
//    @InjectMocks
//    private AvaliacaoController avaliacaoController;
//
//    @Test
//    @DisplayName("Deve realizar uma avaliação")
//
//    void avaliar() {
//        Avaliacao avaliacao = new Avaliacao();
//
//        ResponseEntity<Avaliacao> response = avaliacaoController.avaliar(avaliacao);
//
//        verify(avaliacaoService, times(1)).avaliar(avaliacao);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(avaliacao, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve obter a lista de todas as avaliações")
//    void exibir() {
//        Avaliacao avaliacao1 = new Avaliacao();
//        Avaliacao avaliacao2 = new Avaliacao();
//        List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
//
//        when(avaliacaoService.exibir()).thenReturn(avaliacoes);
//
//        ResponseEntity<List<Avaliacao>> response = avaliacaoController.exibir();
//
//        verify(avaliacaoService, times(1)).exibir();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(avaliacoes, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve obter a lista de todas as avaliações enfileiradas")
//    void verEmFila() {
//        Avaliacao avaliacao1 = new Avaliacao();
//        Avaliacao avaliacao2 = new Avaliacao();
//        List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);
//        FilaObj<Avaliacao> filaEsperada = new FilaObj<>(2);
//
//        when(avaliacaoService.enfileirarAvaliacoes()).thenReturn(filaEsperada);
//
//        ResponseEntity<FilaObj<Avaliacao>> response = avaliacaoController.verEmFila();
//
//        verify(avaliacaoService, times(1)).enfileirarAvaliacoes();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(filaEsperada, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve desfazer a última avaliação")
//    void desfazer() {
//        Avaliacao avaliacao = new Avaliacao();
//
//        when(avaliacaoService.desfazer()).thenReturn(avaliacao);
//
//        ResponseEntity<Avaliacao> response = avaliacaoController.desfazer();
//
//        verify(avaliacaoService, times(1)).desfazer();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(avaliacao, response.getBody());
//    }
//}
