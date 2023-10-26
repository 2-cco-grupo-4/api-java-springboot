//package com.example.picmejava.controller;
//
//import com.example.picmejava.model.Album;
//import com.example.picmejava.service.album.AlbumService;
//import com.example.picmejava.service.album.dto.AtualizarAlbumDTO;
//import com.example.picmejava.service.album.dto.CadastroAlbumDTO;
//import com.example.picmejava.service.album.dto.RetornoAlbumDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class AlbumControllerTest {
//
//    @Mock
//    private AlbumService albumService;
//
//    @InjectMocks
//    private AlbumController albumController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar um novo album")
//    void deveCadastrarNovoAlbum() {
//        CadastroAlbumDTO novoAlbum = new CadastroAlbumDTO();
//        RetornoAlbumDTO albumCadastrado = new RetornoAlbumDTO();
//
//        when(albumService.cadastrar(novoAlbum)).thenReturn(albumCadastrado);
//
//        ResponseEntity<RetornoAlbumDTO> resultado = albumController.cadastrar(novoAlbum);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar um album")
//    void deveAtualizarAlbum() {
//        Long idAlbum = 1L;
//        AtualizarAlbumDTO albumAtualizado = new AtualizarAlbumDTO();
//        RetornoAlbumDTO retornoAlbum = new RetornoAlbumDTO();
//
//        when(albumService.atualizar(idAlbum, albumAtualizado)).thenReturn(retornoAlbum);
//
//        ResponseEntity<RetornoAlbumDTO> resultado = albumController.atualizar(idAlbum, albumAtualizado);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve deletar um album")
//    void deveDeletarAlbum() {
//        Long idAlbum = 1L;
//
//        ResponseEntity<Album> resultado = albumController.deletar(idAlbum);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve listar todos os albuns")
//    void deveListarAlbuns() {
//        List<RetornoAlbumDTO> albuns = new ArrayList<>();
//        albuns.add(new RetornoAlbumDTO());
//        albuns.add(new RetornoAlbumDTO());
//
//        when(albumService.listar()).thenReturn(albuns);
//
//        ResponseEntity<List<RetornoAlbumDTO>> resultado = albumController.listar();
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//        assertEquals(albuns.size(), resultado.getBody().size());
//    }
//}