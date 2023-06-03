package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Album;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.dto.RetornoImagemDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
import com.example.picmejava.service.builder.AlbumBuilder;
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
public class ImagemServiceTest {

    @Mock
    private ImagemRepository imagemRepository;

    @Mock
    private AlbumRepository albumRepository;
    @InjectMocks
    private ImagemService imagemService;


    @Test
    @DisplayName("Deve buscar a imagem pelo ID")
    void buscarImagemPorId() {
        Imagem imagemExistente = new Imagem();
        imagemExistente.setId(1);
        Mockito.when(imagemRepository.findById(1)).thenReturn(Optional.of(imagemExistente));
        Imagem resultado = imagemService.buscarPorId(1);
        assertNotNull(resultado);
        assertEquals(imagemExistente, resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar a imagem pelo ID quando não existir")
    void buscarImagemPorId_quandoImagemNaoExistir() {
            Mockito.when(imagemRepository.findById(1)).thenReturn(Optional.empty());
            assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.buscarPorId(1));
    }


    @Test
    @DisplayName("Deve deletar a imagem pelo ID")
    void deletarImagemPorId() {
        Imagem imagemExistente = new Imagem();
        imagemExistente.setId(1);
        Mockito.when(imagemRepository.findById(1)).thenReturn(Optional.of(imagemExistente));
        imagemService.deletar(1);
        Mockito.verify(imagemRepository, Mockito.times(1)).deleteById(1);
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
    @DisplayName("Deve listar todas as imagens de um álbum")
    void listarTodasImagensDeUmAlbum() {
        List<Imagem> imagens = new ArrayList<>();
        Imagem imagem = new Imagem();
        imagens.add(imagem);
        Album album = AlbumBuilder.criarAlbum();
        album.setImagems(imagens);
        Mockito.when(albumRepository.findById(1)).thenReturn(Optional.of(album));

        List<RetornoImagemDTO> resultado = imagemService.listarPorAlbum(1);

        assertNotNull(resultado);
        assertEquals(imagens.size(), resultado.size());
    }



    @Test
    @DisplayName("Deve gerar um erro caso não encontre a imagem")
    void buscarImagemPorIdQuandoImagemNaoExistir() {

        Mockito.when(imagemRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.buscarPorId(1));
    }

    @Test
    @DisplayName("Deve gerar um erro caso não encontre a imagem")
    void deletarImagemPorIdQuandoImagemNaoExistir() {

        Mockito.when(imagemRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> imagemService.deletar(1));
    }



}
