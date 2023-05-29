package com.example.picmejava.service;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;
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

//    @Test
//    @DisplayName("Deve retornar temaUsuario quando cadastrar temaFotografo com dados validos")
//    void deveRetornarTemaUsuarioQuandoCadastrarTemaFotografoComDadosValidos(){
//        int idFotografo = 1;
//        Tema tema = TemaBuilder.criarTema();
//        Fotografo fotografo = FotografoBuilder.criarFotografo();
//        CadastroTemaFotografoDTO cadastroTemaFotografo = TemaUsuarioBuilder.criarCadastroTemaFotografo();
//        RetornoTemaFotografoDTO retornoTemaFotografoDTO = TemaUsuarioBuilder.criarRetornoTemaFotografo();
//
//        Mockito.when(fotografoRepository.findById(idFotografo))
//                .thenReturn(Optional.of(cadastroTemaFotografo.getFotografo()));
//        Mockito.when(temaRepository.findById(tema.getId()))
//                .thenReturn(Optional.of(tema));
//        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);
//        Mockito.when(temaRepository.save(Mockito.any(Tema.class))).thenReturn(tema);
//
//        RetornoTemaFotografoDTO resultado = temaUsuarioService.cadastrarTemaFotografo(cadastroTemaFotografo);
//
//        assertNotNull(resultado);
//        assertEquals(retornoTemaFotografoDTO, resultado);
//    }

}