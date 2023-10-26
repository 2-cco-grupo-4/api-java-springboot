//package com.example.picmejava.service.usuario;
//
//import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
//import com.example.picmejava.model.Cliente;
//import com.example.picmejava.model.utils.ListaObj;
//import com.example.picmejava.repository.ClienteRepository;
//import com.example.picmejava.repository.UsuarioRepository;
//import com.example.picmejava.service.usuario.builder.ClienteBuilder;
//import com.example.picmejava.service.usuario.builder.UsuarioBuilder;
//import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class ClienteServiceTest {
//    @Mock
//    private ClienteRepository clienteRepository;
//    @Mock
//    private UsuarioRepository usuarioRepository;
//    @InjectMocks
//    private ClienteService clienteService;
//    @InjectMocks
//    private AutenticacaoService autenticacaoService;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    @DisplayName("Deve cadastrar o cliente quando cadastro cliente e dados corretos")
//    void deveCadastrarClienteQuandoCadastroClienteEDadosValidos(){
//        Cliente cliente = ClienteBuilder.criarCliente();
//        String encode = passwordEncoder.encode(cliente.getSenha());
//        CadastroUsuarioDTO cadastroDto = ClienteBuilder.criarCadastroUsuarioDto();
//
//        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
//        Mockito.when(passwordEncoder.encode(cliente.getSenha())).thenReturn(encode);
//
//        Cliente resultado = clienteService.cadastrar(cadastroDto);
//
//        assertNotNull(resultado);
//        assertEquals(cliente, resultado);
//    }
//
//    @Test
//    @DisplayName("Deve retornar três itens quando listar com três itens cadastrados")
//    void deveRetornarTresItensQuandoListarETresItensCadastrados() {
//        int tamanhoEsperado = 3;
//        List<Cliente> clientes = ClienteBuilder.criarListaCliente();
//
//        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
//
//        ListaObj<Cliente> resultado = clienteService.listar();
//
//        assertFalse(clientes.isEmpty());
//        assertEquals(3, resultado.size());
//    }
//
//    @Test
//    @DisplayName("Deve retornar lista vazia quando listar cliente e nenhum cliente cadastrado")
//    void deveRetornarListaVaziaQuandoListarClienteENenumClienteCadastrado(){
//        int tamanhoEsperado = 0;
//
//        Mockito.when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
//
//        ListaObj<Cliente> resultado = clienteService.listar();
//
//        assertTrue(resultado.isEmpty());
//        assertEquals(0, resultado.size());
//    }
//
//    @DisplayName("Deve atualizar o cliente quando atualizar cliente e dados validos")
//    void deveAtualizarClienteQuandoAtualizarClienteEDadosValidos() {
//        Cliente cliente = ClienteBuilder.criarCliente();
//        AtualizarUsuarioDTO atualizar = UsuarioBuilder.criarAtualizazrUsuarioDTO();
//
//        Mockito.when(clienteRepository.findById(Mockito.eq(cliente.getId()))).thenReturn(Optional.of(cliente));
//        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
//
//        Cliente resultado = clienteService.atualizar(cliente.getId(), atualizar);
//
//        assertNotNull(resultado);
//        assertEquals(cliente, resultado);
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção quando atualizar cliente e idCliente não encontrado")
//    void deveLancarExcecaoQuandoAtualizarClienteEIdClienteNaoEncontrado() {
//        Cliente cliente = ClienteBuilder.criarCliente();
//        AtualizarUsuarioDTO atualizar = UsuarioBuilder.criarAtualizazrUsuarioDTO();
//
//        Mockito.when(clienteRepository.findById(Mockito.eq(cliente.getId()))).thenReturn(Optional.empty());
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            clienteService.atualizar(cliente.getId(), atualizar);
//        });
//
//        assertEquals("Cliente não encontrado", exception.getMessage());
//
//    }
//
//    @Test
//    @DisplayName("Deve desautenticar o cliente quando logoff cliente e dados validos")
//    void desautenticarClienteQuandoLogoff() {
//        Cliente cliente = ClienteBuilder.criarCliente();
//        LoginUsuarioDTO login = UsuarioBuilder.criarLoginUsuarioDto();
//
//        Mockito.when(clienteRepository.findByEmailAndSenha(login.getEmail(), login.getSenha())).thenReturn(Optional.of(cliente));
//        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
//
//        Cliente resultado = clienteService.logoff(login);
//
//        assertNotNull(resultado);
//        assertEquals(cliente, resultado);
//    }
//
//    @Test
//    @DisplayName("Deve lançar excecao quando logoff cliente e dados inválidos")
//    void deveLancarExcecaoQuandoLogoffEDadosInvalidos() {
//        Cliente cliente = ClienteBuilder.criarCliente();
//        LoginUsuarioDTO login = UsuarioBuilder.criarLoginUsuarioDto();
//
//        Mockito.when(clienteRepository.findByEmailAndSenha(login.getEmail(), login.getSenha())).thenReturn(Optional.empty());
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            clienteService.logoff(login);
//        });
//
//        assertEquals("Cliente não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar cliente quando validar fotografo")
//    void deveRetornarFotografoQuandoValidarCliente(){
//        Cliente cliente = ClienteBuilder.criarCliente();
//        LoginUsuarioDTO loguin = UsuarioBuilder.criarLoginUsuarioDto();
//
//        Mockito.when(clienteRepository.findByEmailAndSenha(loguin.getEmail(), loguin.getSenha())).thenReturn(Optional.of(cliente));
//
//        Cliente resultado = clienteService.validarCliente(loguin.getEmail(), loguin.getSenha());
//
//        assertNotNull(resultado);
//        assertEquals(cliente, resultado);
//    }
//
//    @Test
//    @DisplayName("Deve retornar excecao quando validar cliente nao encontrar com email e senha")
//    void deveRetornarExcecaoQuandoValidarClienteENaoEncontrarComEmailESenha(){
//        LoginUsuarioDTO loguin = UsuarioBuilder.criarLoginUsuarioDto();
//
//        Mockito.when(clienteRepository.findByEmailAndSenha(loguin.getEmail(), loguin.getSenha()))
//                .thenReturn(Optional.empty());
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            clienteService.validarCliente(loguin.getEmail(), loguin.getSenha());
//        });
//
//        assertEquals("Cliente não encontrado", exception.getMessage());
//    }
//}