//package com.example.picmejava.controller;
//
//import com.example.picmejava.service.endereco.EnderecoService;
//import com.example.picmejava.service.endereco.dto.CadastroEnderecoDTO;
//import com.example.picmejava.service.endereco.dto.RetornoEnderecoDTO;
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
//class EnderecoControllerTest {
//
//    @Mock
//    private EnderecoService enderecoService;
//
//    @InjectMocks
//    private EnderecoController enderecoController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar um novo endereço e retornar o endereço cadastrado")
//    void deveCadastrarEndereco() {
//        CadastroEnderecoDTO novoEndereco = new CadastroEnderecoDTO();
//        novoEndereco.setNumero(123);
//        novoEndereco.setCidade("Cidade X");
//
//        RetornoEnderecoDTO enderecoCadastrado = new RetornoEnderecoDTO();
//        enderecoCadastrado.setId(1L);
//        enderecoCadastrado.setRua("Rua A");
//        enderecoCadastrado.setNumero(123);
//        enderecoCadastrado.setCidade("Cidade X");
//
//        when(enderecoService.cadastrar(novoEndereco)).thenReturn(enderecoCadastrado);
//
//        ResponseEntity<RetornoEnderecoDTO> resultado = enderecoController.cadastrar(novoEndereco);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
//        assertNotNull(resultado.getBody());
//        assertEquals(enderecoCadastrado, resultado.getBody());
//    }
//
//    @Test
//    @DisplayName("Deve retornar a lista de endereços")
//    void deveListarEnderecos() {
//        List<RetornoEnderecoDTO> enderecos = new ArrayList<>();
//
//        when(enderecoService.listar()).thenReturn(enderecos);
//
//        ResponseEntity<List<RetornoEnderecoDTO>> resultado = enderecoController.listar();
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//        assertNotNull(resultado.getBody());
//        assertEquals(enderecos, resultado.getBody());
//    }
//}