package com.example.picmejava.controller;

import com.example.picmejava.model.Imagem;
import com.example.picmejava.service.imagem.ImagemService;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImagemControllerTest {



    @Mock
    private ImagemService imagemService;
    @InjectMocks
    private ImagemController imagemController;
    @Test
    @DisplayName("Deve cadastrar uma nova imagem")
    void cadastrarImagem() {
        Long idAlbum = 1L;
        Imagem novaImagem = new Imagem();
        RetornoImagemDTO retornoImagem = new RetornoImagemDTO();

        when(imagemService.cadastrar(idAlbum, novaImagem)).thenReturn(retornoImagem);

        ResponseEntity<RetornoImagemDTO> response = imagemController.cadastrar(idAlbum, novaImagem);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(retornoImagem, response.getBody());
    }

    @Test
    @DisplayName("Deve listar todas as imagens")
    void listarImagens() {
        List<RetornoImagemDTO> imagens = new ArrayList<>();
        imagens.add(new RetornoImagemDTO());

        when(imagemService.listar()).thenReturn(imagens);

        ResponseEntity<List<RetornoImagemDTO>> response = imagemController.listar();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(imagens, response.getBody());
    }

    @Test
    @DisplayName("Deve deletar uma imagem")
    void deletarImagem() {
        Long idImagem = 1L;

        ResponseEntity<Void> response = imagemController.deletar(idImagem);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(imagemService, times(1)).deletar(idImagem);
    }
}
