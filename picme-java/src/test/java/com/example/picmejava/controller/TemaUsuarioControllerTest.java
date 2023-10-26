//package com.example.picmejava.controller;
//
//import com.example.picmejava.service.temaUsuario.TemaUsuarioService;
//import com.example.picmejava.service.temaUsuario.dto.CadastroTemaClienteDTO;
//import com.example.picmejava.service.temaUsuario.dto.CadastroTemaFotografoDTO;
//import com.example.picmejava.service.temaUsuario.dto.RetornoTemaClienteDTO;
//import com.example.picmejava.service.temaUsuario.dto.RetornoTemaFotografoDTO;
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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class TemaUsuarioControllerTest {
//
//
//
//    @Mock
//    private TemaUsuarioService temaUsuarioService;
//    @InjectMocks
//    private TemaUsuarioController temaUsuarioController;
//    @Test
//    @DisplayName("Deve cadastrar um novo tema de fotógrafo")
//    void cadastrarTemaFotografo() {
//        CadastroTemaFotografoDTO novoTemaFotografo = new CadastroTemaFotografoDTO();
//        RetornoTemaFotografoDTO retornoTemaFotografo = new RetornoTemaFotografoDTO();
//
//        when(temaUsuarioService.cadastrarTemaFotografo(novoTemaFotografo)).thenReturn(retornoTemaFotografo);
//
//        ResponseEntity<RetornoTemaFotografoDTO> response = temaUsuarioController.cadastrar(novoTemaFotografo);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(retornoTemaFotografo, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar um novo tema de cliente")
//    void cadastrarTemaCliente() {
//        CadastroTemaClienteDTO novoTemaCliente = new CadastroTemaClienteDTO();
//        RetornoTemaClienteDTO retornoTemaCliente = new RetornoTemaClienteDTO();
//
//        when(temaUsuarioService.cadastrarTemaCliente(novoTemaCliente)).thenReturn(retornoTemaCliente);
//
//        ResponseEntity<RetornoTemaClienteDTO> response = temaUsuarioController.cadastrar(novoTemaCliente);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(retornoTemaCliente, response.getBody());
//    }
//}
