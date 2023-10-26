//package com.example.picmejava.controller;
//
//import com.example.picmejava.model.Fotografo;
//import com.example.picmejava.model.mapper.FotografoMapper;
//import com.example.picmejava.service.usuario.FotografoService;
//import com.example.picmejava.service.usuario.dto.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
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
//@ExtendWith(MockitoExtension.class)
//class FotografoControllerTest {
//    @Mock
//    private FotografoService fotografoService;
//
//    @InjectMocks
//    private FotografoController fotografoController;
//
//    private FotografoMapper fotografoMapper = new FotografoMapper();
//
//
//
//    @Test
//    @DisplayName("Deve cadastrar um novo fotógrafo")
//    void cadastrarFotografo() {
//        CadastroUsuarioDTO novoCadastroFotografo = new CadastroUsuarioDTO();
//        PerfilFotografoDTO perfilFotografo = new PerfilFotografoDTO();
//
//        when(fotografoService.cadastrar(novoCadastroFotografo)).thenReturn(perfilFotografo);
//
//        ResponseEntity<PerfilFotografoDTO> response = fotografoController.cadastrar(novoCadastroFotografo);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(perfilFotografo, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve retornar a lista de fotógrafos")
//    void listarFotografos() {
//        List<RetornoFotografoDTO> listaFotografos = new ArrayList<>();
//
//        when(fotografoService.listar()).thenReturn(listaFotografos);
//
//        ResponseEntity<List<RetornoFotografoDTO>> response = fotografoController.listar();
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }
//
//    @Test
//    @DisplayName("Deve atualizar os dados do fotógrafo")
//    void atualizarFotografo() {
//        Long idFotografo = 1L;
//        AtualizarUsuarioDTO fotografoAtualizado = new AtualizarUsuarioDTO();
//        PerfilFotografoDTO perfilFotografo = new PerfilFotografoDTO();
//
//        when(fotografoService.atualizar(idFotografo, fotografoAtualizado)).thenReturn(new Fotografo());
//
//        ResponseEntity<PerfilFotografoDTO> response = fotografoController.atualizar(idFotografo, fotografoAtualizado);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(perfilFotografo.getId(), response.getBody().getId());
//    }
//
//    @Test
//    @DisplayName("Deve realizar o login do fotógrafo")
//    void loginFotografo() {
//        LoginUsuarioDTO usuarioLoginDTO = new LoginUsuarioDTO();
//        RetornoFotografoDTO retornoFotografo = new RetornoFotografoDTO();
//
//        when(fotografoService.login(usuarioLoginDTO)).thenReturn(new Fotografo());
//
//        ResponseEntity<RetornoFotografoDTO> response = fotografoController.login(usuarioLoginDTO);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(retornoFotografo.getId(), response.getBody().getId());
//    }
//
//    @Test
//    @DisplayName("Deve realizar o logoff do fotógrafo")
//    void logoffFotografo() {
//        LoginUsuarioDTO buscarFotografo = new LoginUsuarioDTO();
//        PerfilFotografoDTO perfilFotografo = new PerfilFotografoDTO();
//        ResponseEntity<PerfilFotografoDTO> responseEntity = ResponseEntity.ok(perfilFotografo);
//
//        when(fotografoService.logoff(buscarFotografo)).thenReturn(new Fotografo());
//
//        ResponseEntity<PerfilFotografoDTO> response = fotografoController.logoff(buscarFotografo);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(perfilFotografo.getId(), response.getBody().getId());
//    }
//
//
//
//
//}
