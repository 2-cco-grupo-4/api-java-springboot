//package com.example.picmejava.controller;
//
//import com.example.picmejava.model.Cliente;
//import com.example.picmejava.model.utils.ListaObj;
//import com.example.picmejava.service.usuario.ClienteService;
//import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
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
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//class ClienteControllerTest {
//
//    @Mock
//    private ClienteService clienteService;
//
//    @InjectMocks
//    private ClienteController clienteController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar um novo cliente")
//    void deveCadastrarNovoCliente() {
//        CadastroUsuarioDTO cadastro = new CadastroUsuarioDTO();
//        Cliente cliente = new Cliente();
//
//        when(clienteService.cadastrar(cadastro)).thenReturn(cliente);
//
//        ResponseEntity<PerfilClienteDTO> resultado = clienteController.cadastrar(cadastro);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve listar todos os clientes cadastrados")
//    void deveListarClientesCadastrados() {
//        ListaObj<Cliente> clientesCadastrados = new ListaObj<>();
//        clientesCadastrados.add(new Cliente());
//        clientesCadastrados.add(new Cliente());
//
//        when(clienteService.listar()).thenReturn(clientesCadastrados);
//
//        ResponseEntity<List<PerfilClienteDTO>> resultado = clienteController.listar();
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//        assertEquals(clientesCadastrados.size(), resultado.getBody().size());
//    }
//
//    @Test
//    @DisplayName("Deve atualizar os dados de um cliente")
//    void deveAtualizarDadosCliente() {
//        Long idCliente = 1L;
//        AtualizarUsuarioDTO clienteAtualizado = new AtualizarUsuarioDTO();
//        Cliente cliente = new Cliente();
//
//        when(clienteService.atualizar(idCliente, clienteAtualizado)).thenReturn(cliente);
//
//        ResponseEntity<PerfilClienteDTO> resultado = clienteController.atualizar(idCliente, clienteAtualizado);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve realizar o login de um cliente")
//    void deveRealizarLoginCliente() {
//        LoginUsuarioDTO usuarioLoginDTO = new LoginUsuarioDTO();
//        Cliente clienteLogado = new Cliente();
//
//        when(clienteService.login(usuarioLoginDTO)).thenReturn(clienteLogado);
//
//        ResponseEntity<PerfilClienteDTO> resultado = clienteController.login(usuarioLoginDTO);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Deve realizar o logoff de um cliente")
//    void deveRealizarLogoffCliente() {
//        LoginUsuarioDTO buscarCliente = new LoginUsuarioDTO();
//        Cliente clienteLogoff = new Cliente();
//
//        when(clienteService.logoff(buscarCliente)).thenReturn(clienteLogoff);
//
//        ResponseEntity<PerfilClienteDTO> resultado = clienteController.logoff(buscarCliente);
//
//        assertNotNull(resultado);
//        assertEquals(HttpStatus.OK, resultado.getStatusCode());
//    }
//
//}