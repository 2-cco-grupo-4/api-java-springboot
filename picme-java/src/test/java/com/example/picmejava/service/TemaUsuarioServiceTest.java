package com.example.picmejava.service;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilTemaDTO;
import com.example.picmejava.repository.TemaRepository;
import com.example.picmejava.service.builder.TemaBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TemaUsuarioServiceTest {
    @Mock
    private TemaRepository temaRepository;
    @InjectMocks
    private TemaService temaService;
    @Test
    @DisplayName("Deve retornar Tema quando cadastrar com dados validos")

    void deveRetornarTemaQuandoCadastrarComDadosValidos(){
        Tema tema = TemaBuilder.criarTema();

        Mockito.when(temaRepository.save(Mockito.any(Tema.class))).thenReturn(tema);

        Tema resultado = temaService.cadastrar(tema);

        assertNotNull(resultado);
        assertEquals(tema, resultado);
    }

    @Test
    @DisplayName("Deve retornar tres itens quando tres itens cadastrados")
    void deveRetornarTresItensQuandoTresItensCadastrados(){
        int valorEsperado = 3;
        List<Tema> temas = TemaBuilder.criarTemas();

        Mockito.when(temaRepository.findAll()).thenReturn(temas);

        List<PerfilTemaDTO> resultado = temaService.listar();

        assertNotNull(resultado);
        assertEquals(valorEsperado, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista de três itens quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItemCadastrado(){
        int valorEsperado = 3;
        List<Tema> temas = TemaBuilder.criarTemas();

        Mockito.when(temaRepository.findAll()).thenReturn(temas);

        List<PerfilTemaDTO> resultado = temaService.listar();

        assertNotNull(resultado);
        assertEquals(valorEsperado, resultado.size());
    }


}