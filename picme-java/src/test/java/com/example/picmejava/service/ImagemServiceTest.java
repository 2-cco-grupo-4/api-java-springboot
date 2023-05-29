package com.example.picmejava.service;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.model.dto.RetornoImagemDTO;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(MockitoExtension.class)
public class ImagemServiceTest {

    @Mock
    private ImagemRepository imagemRepository;

    @InjectMocks
    private ImagemService imagemService;

//    @Test
//    @DisplayName("Deve cadastrar a imagem em um álbum existente")
//    void cadastrarImagemEmAlbumExistente() {
//
//        Album albumExistente = new Album();
//        albumExistente.setId(1);
//
//
//        Imagem novaImagem = new Imagem();
//
//        Mockito.when(albumRepository.findById(1)).thenReturn(Optional.of(albumExistente));
//
//
//        Mockito.when(imagemRepository.save(Mockito.any(Imagem.class))).thenReturn(novaImagem);
//
//
//        RetornoImagemDTO resultado = imagemService.cadastrar(1, novaImagem);
//
//        assertNotNull(resultado);
//        assertEquals(novaImagem, resultado.getImagem());
//        assertEquals(albumExistente, resultado.getAlbum());
//    }

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
        imagensExistentes.add(new Imagem());
        imagensExistentes.add(new Imagem());
        imagensExistentes.add(new Imagem());
        Mockito.when(imagemRepository.findAll()).thenReturn(imagensExistentes);


        List<RetornoImagemDTO> resultado = imagemService.listar();


        assertNotNull(resultado);
        assertEquals(imagensExistentes.size(), resultado.size());
    }



}
