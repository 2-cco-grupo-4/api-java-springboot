package com.example.picmejava.service.evento;

import com.example.picmejava.model.Evento;
import com.example.picmejava.service.evento.builder.EventoBuilder;
import com.example.picmejava.service.evento.dto.CadastroEventoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private FotografoRepository fotografoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private TemaRepository temaRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    @DisplayName("Deve retornar evento quando cadastrar com dados validos")
    void deveRetonarEventoQuandoCadastrarComDadosValidos(){
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(eventoRepository.save(Mockito.any(Evento.class))).thenReturn(evento);

        Evento resultado = eventoRepository.save(evento);

        assertNotNull(resultado);
        assertEquals(evento, resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando idFotografo invalido")
    void deveRetornarExcecaoQuandoIdFotografoInvalido(){
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });

        assertEquals("Fotografo não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar excecao quando idCliente invalido")
    void deveRetornarExcecaoQuandoIdClienteInvalido(){
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.of(evento.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar excecao quando idTema invalido")
    void deveRetornarExcecaoQuandoIdTemaInvalido(){
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.of(evento.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(evento.getCliente()));
        Mockito.when(temaRepository.findById(Mockito.eq(cadastroEventoDTO.getIdTema()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar lista com tres itens quando tres itens cadastrados")
    void deveRetornarTresItensQuandoTresItensCadastrados(){
        int tamanhoEsperado = 3;
        List<Evento> eventos = EventoBuilder.criarListaEvento();

        Mockito.when(eventoRepository.findAll()).thenReturn(eventos);

        List<RetornoEventoDTO> resultado = eventoService.listar();

        assertNotNull(resultado);
        assertEquals(tamanhoEsperado, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItemCadastrado(){
        int tamanhoEsperado = 0;

        Mockito.when(eventoRepository.findAll()).thenReturn(new ArrayList<>());

        List<RetornoEventoDTO> resultado = eventoService.listar();

        assertTrue(resultado.isEmpty());
        assertEquals(tamanhoEsperado, resultado.size());
    }

}