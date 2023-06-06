package com.example.picmejava.service.imagem;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
import com.example.picmejava.service.album.builder.AlbumBuilder;
import com.example.picmejava.service.evento.EventoService;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class ImagemServiceTest {

    @Mock
    private ImagemRepository imagemRepository;

    @Mock
    private AlbumRepository albumRepository;
    @InjectMocks
    private ImagemService imagemService;

    @InjectMocks
    private EventoService eventoService;


    @Test
    @DisplayName("Deve buscar a imagem pelo ID")
    void buscarImagemPorId() {
        Imagem imagemExistente = new Imagem();
        imagemExistente.setId(1L);
        Mockito.when(imagemRepository.findById(1L)).thenReturn(Optional.of(imagemExistente));
        Imagem resultado = imagemService.buscarPorId(1L);
        assertNotNull(resultado);
        assertEquals(imagemExistente, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar a imagem pelo ID quando não existir")
    void buscarImagemPorId_quandoImagemNaoExistir() {
        Mockito.when(imagemRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.buscarPorId(1L));
    }


    @Test
    @DisplayName("Deve deletar a imagem pelo ID")
    void deletarImagemPorId() {
        Imagem imagemExistente = new Imagem();
        imagemExistente.setId(1L);
        Mockito.when(imagemRepository.findById(1L)).thenReturn(Optional.of(imagemExistente));
        imagemService.deletar(1L);
        Mockito.verify(imagemRepository, Mockito.times(1)).deleteById(1L);
    }


    @Test
    @DisplayName("Deve listar todas as imagens")
    void listarTodasImagens() {

        List<Imagem> imagensExistentes = new ArrayList<>();
        Imagem imagem = new Imagem();
        imagem.setIdAlbum(AlbumBuilder.criarAlbum());
        imagensExistentes.add(imagem);
        Mockito.when(imagemRepository.findAll()).thenReturn(imagensExistentes);


        List<RetornoImagemDTO> resultado = imagemService.listar();


        assertNotNull(resultado);
        assertEquals(imagensExistentes.size(), resultado.size());
    }





    @Test
    @DisplayName("Deve gerar um erro caso não encontre a imagem")
    void buscarImagemPorIdQuandoImagemNaoExistir() {

        Mockito.when(imagemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.buscarPorId(1L));
    }

    @Test
    @DisplayName("Deve gerar um erro caso não encontre a imagem")
    void deletarImagemPorIdQuandoImagemNaoExistir() {

        Mockito.when(imagemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.deletar(1L));
    }



}