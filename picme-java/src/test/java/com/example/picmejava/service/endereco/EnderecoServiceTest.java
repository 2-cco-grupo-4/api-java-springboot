package com.example.picmejava.service.endereco;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.service.endereco.builder.EnderecoBuilder;
import com.example.picmejava.service.endereco.dto.CadastroEnderecoDTO;
import com.example.picmejava.service.endereco.dto.RetornoEnderecoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.EnderecoRepository;
import com.example.picmejava.repository.EventoRepository;
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
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Deve retornar Endereco quanda cadastrar endereço e dados validos")
    void deveRetornarEnderecoQuandoCadastrarEDadosValidos(){
        Endereco endereco = EnderecoBuilder.criarEndereco();
        CadastroEnderecoDTO cadastroEndereco = EnderecoBuilder.criarCadastroEndereco();

        Mockito.when(eventoRepository.findById(endereco.getEvento().getId())).thenReturn(
                Optional.of(endereco.getEvento())
        );
        Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(endereco);

        RetornoEnderecoDTO resultado = enderecoService.cadastrar(cadastroEndereco);

        assertNotNull(resultado);
        assertEquals(endereco.getId(), resultado.getId());
        assertEquals(endereco.getNumero(), resultado.getNumero());
        assertEquals(endereco.getCep(), resultado.getCep());
        assertEquals(endereco.getCidade(), resultado.getCidade());
        assertEquals(endereco.getBairro(), resultado.getBairro());
        assertEquals(endereco.getComplemento(), resultado.getComplemento());
        assertEquals(endereco.getEstado(), resultado.getEstado());
        assertNotNull(endereco.getEvento());

        Mockito.verify(eventoRepository, Mockito.times(1)).findById(Mockito.eq(endereco.getId()));
        Mockito.verify(enderecoRepository, Mockito.times(1)).save(Mockito.any(Endereco.class));
    }

    @Test
    @DisplayName("Deve retornar excecao quando cadastrar endereço e idEvento invalido")
    void deveRetornarExcecaoQuandoCadastrarEnderecoEIdEventoInvalido(){
        Endereco endereco = EnderecoBuilder.criarEndereco();
        CadastroEnderecoDTO cadastroEndereco = EnderecoBuilder.criarCadastroEndereco();

        Mockito.when(eventoRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            enderecoService.cadastrar(cadastroEndereco);
        });

        assertEquals("Evento não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar tres itens quando tres itens cadastrados")
    void deveRetornarTresItensQuandoTresItensCadastrados(){
        int valorEsperado = 3;
        List<Endereco> enderecos = EnderecoBuilder.criarListarEndereco();

        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecos);

        List<RetornoEnderecoDTO> resultado = enderecoService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(valorEsperado, resultado.size());

        Mockito.verify(enderecoRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItemCadastrado(){
        int valorEsperado = 0;

        Mockito.when(enderecoRepository.findAll()).thenReturn(new ArrayList<>());

        List<RetornoEnderecoDTO> resultado = enderecoService.listar();

        assertTrue(resultado.isEmpty());
        assertEquals(valorEsperado, resultado.size());

        Mockito.verify(enderecoRepository, Mockito.times(1)).findAll();
    }

}