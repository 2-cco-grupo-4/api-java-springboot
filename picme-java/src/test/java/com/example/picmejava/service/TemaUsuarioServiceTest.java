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

//    @Test
//    @DisplayName("Deve retornar temaUsuario quando cadastrar temaFotografo com dados validos")
//    void deveRetornarTemaUsuarioQuandoCadastrarTemaFotografoComDadosValidos(){
//        int idFotografo = 1;
//        Tema tema = new Tema();
//        tema.setId(1);
//
//        Fotografo fotografo = new Fotografo();
//        fotografo.setId(1);
//
//        CadastroTemaFotografoDTO cadastroTemaFotografoDTO = new CadastroTemaFotografoDTO();
//        cadastroTemaFotografoDTO.setFotografo(fotografo);
//        cadastroTemaFotografoDTO.setTemas(List.of(TemaBuilder.criarTema()));
//
//        RetornoTemaFotografoDTO retornoTemaFotografoDTO = new RetornoTemaFotografoDTO();
//        retornoTemaFotografoDTO.setFotografo(FotografoBuilder.criarPerfilFotografoDto());
//        retornoTemaFotografoDTO.setTemas(List.of(TemaBuilder.criarPerfilTemaDto()));
//
//
//        Mockito.when(fotografoRepository.findById(idFotografo))
//                .thenReturn(Optional.of(cadastroTemaFotografoDTO.getFotografo()));
//        Mockito.when(temaRepository.findById(Mockito.anyInt()))
//                .thenReturn(Optional.of(tema));
//        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);
//        Mockito.when(temaRepository.save(Mockito.any(Tema.class))).thenReturn(tema);
//
//        RetornoTemaFotografoDTO resultado = temaUsuarioService.cadastrarTemaFotografo(cadastroTemaFotografoDTO);
//
//        assertNotNull(resultado);
//        assertEquals(retornoTemaFotografoDTO.getTemas(), resultado.getTemas());
//    }

}