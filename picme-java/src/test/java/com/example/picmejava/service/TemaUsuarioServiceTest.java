package com.example.picmejava.service;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.model.dto.PerfilTemaDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import com.example.picmejava.service.builder.FotografoBuilder;
import com.example.picmejava.service.builder.TemaBuilder;
import com.example.picmejava.service.builder.TemaUsuarioBuilder;
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
class TemaUsuarioServiceTest {

    @Mock
    private TemaRepository temaRepository;

    @Mock
    private FotografoRepository fotografoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private TemaUsuarioService temaUsuarioService;

    @Test
    @DisplayName("Deve retornar exception quando cadastrar TemaFotografo e idTema invalido")
    void deveRetornarExceptionQuandoCadastrarTemaFotografoEIdTemaInvalido(){
        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            temaUsuarioService.cadastrarTemaFotografo(cadastro);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar exception quando cadastrar TemaFotografo e idFotografo invalido")
    void deveRetornarExceptionQuandoCadastrarTemaFotografoEIdFotografoInvalido(){
        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
        Tema tema = TemaBuilder.criarTema();

        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            temaUsuarioService.cadastrarTemaFotografo(cadastro);
        });

        assertEquals("Fotografo não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar TemaFotografo quando cadastrar TemaFotografo e dados validos")
    void deveRetornarTemaFotografoQuandoCadastrarTemaFotografoEDadosValidos(){
        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
        Tema tema = TemaBuilder.criarTema();
        Fotografo fotografo = FotografoBuilder.criarFotografo();

        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
        Mockito.when(fotografoRepository.findById(Mockito.eq(fotografo.getId()))).thenReturn(Optional.of(fotografo));

        RetornoTemaFotografoDTO resultado = temaUsuarioService.cadastrarTemaFotografo(cadastro);

        assertNotNull(resultado);
    }

}