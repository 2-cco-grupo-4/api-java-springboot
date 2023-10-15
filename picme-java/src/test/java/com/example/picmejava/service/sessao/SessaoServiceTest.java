package com.example.picmejava.service.sessao;

import com.example.picmejava.model.Sessao;
import com.example.picmejava.service.evento.SessaoService;
import com.example.picmejava.service.sessao.builder.SessaoBuilder;
import com.example.picmejava.service.evento.dto.CadastroSessaoDTO;
import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
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
class SessaoServiceTest {

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
    private SessaoService sessaoService;

    @Test
    @DisplayName("Deve retornar evento quando cadastrar com dados validos")
    void deveRetonarEventoQuandoCadastrarComDadosValidos(){
        Sessao sessao = SessaoBuilder.criarEvento();

        Mockito.when(eventoRepository.save(Mockito.any(Sessao.class))).thenReturn(sessao);

        Sessao resultado = eventoRepository.save(sessao);

        assertNotNull(resultado);
        assertEquals(sessao, resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando idFotografo invalido")
    void deveRetornarExcecaoQuandoIdFotografoInvalido(){
        CadastroSessaoDTO cadastroSessaoDTO = SessaoBuilder.criarCadastroEvento();
        Sessao sessao = SessaoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroSessaoDTO.getIdFotografo()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoService.cadastrar(cadastroSessaoDTO);
        });

        assertEquals("Fotografo não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar excecao quando idCliente invalido")
    void deveRetornarExcecaoQuandoIdClienteInvalido(){
        CadastroSessaoDTO cadastroSessaoDTO = SessaoBuilder.criarCadastroEvento();
        Sessao sessao = SessaoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroSessaoDTO.getIdFotografo()))).thenReturn(Optional.of(sessao.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoService.cadastrar(cadastroSessaoDTO);
        });

        assertEquals("Cliente não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar excecao quando idTema invalido")
    void deveRetornarExcecaoQuandoIdTemaInvalido(){
        CadastroSessaoDTO cadastroSessaoDTO = SessaoBuilder.criarCadastroEvento();
        Sessao sessao = SessaoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroSessaoDTO.getIdFotografo()))).thenReturn(Optional.of(sessao.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sessao.getCliente()));
        Mockito.when(temaRepository.findById(Mockito.eq(cadastroSessaoDTO.getIdTema()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoService.cadastrar(cadastroSessaoDTO);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar lista com tres itens quando tres itens cadastrados")
    void deveRetornarTresItensQuandoTresItensCadastrados(){
        int tamanhoEsperado = 3;
        List<Sessao> sessoes = SessaoBuilder.criarListaEvento();

        Mockito.when(eventoRepository.findAll()).thenReturn(sessoes);

        List<RetornoEventoDTO> resultado = sessaoService.listar();

        assertNotNull(resultado);
        assertEquals(tamanhoEsperado, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItemCadastrado(){
        int tamanhoEsperado = 0;

        Mockito.when(eventoRepository.findAll()).thenReturn(new ArrayList<>());

        List<RetornoEventoDTO> resultado = sessaoService.listar();

        assertTrue(resultado.isEmpty());
        assertEquals(tamanhoEsperado, resultado.size());
    }

}