package com.example.picmejava.service;
import com.example.picmejava.model.*;
import com.example.picmejava.model.dto.RetornoEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoCadastradaException;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;

import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.CadastroEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.*;
import com.example.picmejava.service.builder.EventoBuilder;
import com.example.picmejava.service.builder.FotografoBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {
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
    @DisplayName("Deve lançar exceção ao cadastrar evento com fotógrafo inexistente")
    void lancarExcecao_AoCadastrarEventoComFotografoInexistente() {
        CadastroEventoDTO novoEvento = new CadastroEventoDTO();
        Fotografo fotografoInexistente = new Fotografo();
        Mockito.when(fotografoRepository.findById(fotografoInexistente.getId())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(novoEvento);
        });

        Mockito.verify(fotografoRepository).findById(fotografoInexistente.getId());
        Mockito.verify(clienteRepository, Mockito.never()).findById(Mockito.anyInt());
        Mockito.verify(temaRepository, Mockito.never()).findById(Mockito.anyInt());
        Mockito.verify(eventoRepository, Mockito.never()).save(Mockito.any(Evento.class));
    }






    @Test
    @DisplayName("Deve retornar a lista de eventos vazia")
    void retornar_ListaDeEventosVazia() {
        Mockito.when(eventoRepository.findAll()).thenReturn(Collections.emptyList());

        List<RetornoEventoDTO> resultado = eventoService.listar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

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
    @DisplayName("Deve retornar excecao quando idTema invalido")
    void deveRetornarExcecaoQuandoIdTemaInvalido(){
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.of(evento.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(evento.getCliente()));
        Mockito.when(temaRepository.findById(Mockito.eq(cadastroEventoDTO.getIdTema()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
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
    void deveRetornarExcecaoQuandoIdClienteInvalido() {
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Mockito.when(fotografoRepository.findById(Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });
    }






}







