package com.example.picmejava.controller;

import com.example.picmejava.service.evento.SessaoService;
import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoControllerTest {
    @Mock
    private SessaoService sessaoService;

    @InjectMocks
    private EventoController eventoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve cadastrar um novo evento")
    void cadastrarEvento() {
        CadastroSessaoDTO novoEvento = new CadastroSessaoDTO();
        RetornoEventoDTO retornoEvento = new RetornoEventoDTO();

        when(sessaoService.cadastrar(novoEvento)).thenReturn(retornoEvento);

        ResponseEntity<RetornoEventoDTO> response = eventoController.cadastrar(novoEvento);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(retornoEvento, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar a lista de eventos")
    void listarEventos() {
        List<RetornoEventoDTO> listaEventos = new ArrayList<>();

        when(sessaoService.listar()).thenReturn(listaEventos);

        ResponseEntity<List<RetornoEventoDTO>> response = eventoController.listar();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(listaEventos, response.getBody());
    }
}
