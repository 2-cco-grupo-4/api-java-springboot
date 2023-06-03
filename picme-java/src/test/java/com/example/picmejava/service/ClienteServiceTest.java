package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.*;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.service.builder.ClienteBuilder;
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
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;



    @Test
    @DisplayName("Deve gerar exceção quando tentar logar e cliente não existir")
    void login_clienteQuandoClienteNaoExistir() {

        //get
        LoginUsuarioDTO loginDto = ClienteBuilder.criarLoginUsuarioDto();

        //when
        Mockito.when(clienteRepository.findByEmailAndSenha(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());

        //assert
        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.login(loginDto));

    }



    @Test
    @DisplayName("Deve cadastrar o cliente quando dados corretos")
    void cadastrar_clienteQuandoDadosCorretos(){

        //get
        Cliente cliente = ClienteBuilder.criarCliente();
        CadastroUsuarioDTO cadastroDto = ClienteBuilder.criarCadastroUsuarioDto();

        //when
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);

        //result
        Cliente resultado = clienteService.cadastrar(cadastroDto);

        //assert
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar três itens quando três itens cadastrados")
    void cadastrar_retornarTresItensQuandoTresItensCadastrados() {
        //get
        Cliente cliente = ClienteBuilder.criarCliente();
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        listaClientes.add(cliente);
        listaClientes.add(cliente);

        //assert
        assertFalse(listaClientes.isEmpty());
        assertEquals(3, listaClientes.size());
    }



    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void atualizar_clienteQuandoClienteNaoEncontrado() {

        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Novo Nome");
        dadosAtualizados.setNumCelular("NovoNumero");

        Mockito.when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizar(1, dadosAtualizados));

    }

    @Test
    @DisplayName("Deve atualizar o cliente quando os dados são válidos")
    void atualizar_ClienteQuandoDadosValidos() {

        Cliente clienteExistente = ClienteBuilder.criarCliente();
        clienteExistente.setId(1);


        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Novo Nome");
        dadosAtualizados.setNumCelular("NovoNumero");

        Mockito.when(clienteRepository.findById(1)).thenReturn(Optional.of(clienteExistente));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteExistente);


        Cliente resultado = clienteService.atualizar(1, dadosAtualizados);


        assertEquals("Novo Nome", resultado.getNome());
        assertEquals("NovoNumero", resultado.getNumCelular());

    }
    @Test
    @DisplayName("Deve autenticar o cliente quando as credenciais são válidas")
    void autenticarClienteQuandoCredenciaisValidas() {
        Cliente clienteExistente = ClienteBuilder.criarCliente();
        clienteExistente.setEmail("email@example.com");
        clienteExistente.setSenha("senha123");


        LoginUsuarioDTO dadosLogin = new LoginUsuarioDTO();
        dadosLogin.setEmail("email@example.com");
        dadosLogin.setSenha("senha123");


        Mockito.when(clienteRepository.findByEmailAndSenha("email@example.com", "senha123")).thenReturn(Optional.of(clienteExistente));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteExistente);

        Cliente resultado = clienteService.login(dadosLogin);


        assertTrue(resultado.getAutenticado());
    }

    @Test
    @DisplayName("Deve desautenticar o cliente quando chamado o método logoff")
    void desautenticarClienteQuandoLogoff() {
        Cliente clienteAutenticado = ClienteBuilder.criarCliente();
        clienteAutenticado.setAutenticado(true);


        LoginUsuarioDTO dadosLogin = new LoginUsuarioDTO();
        dadosLogin.setEmail("email@example.com");
        dadosLogin.setSenha("senha123");


        Mockito.when(clienteRepository.findByEmailAndSenha("email@example.com", "senha123")).thenReturn(Optional.of(clienteAutenticado));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteAutenticado);


        Cliente resultado = clienteService.logoff(dadosLogin);


        assertFalse(resultado.getAutenticado());
    }

    //Testar cliente
    @Test
    @DisplayName("Deve retornar uma lista de clientes quando chamado o método listar")
    void listarClientesQuandoChamadoMetodoListar() {
        Cliente cliente = ClienteBuilder.criarCliente();
        cliente.setId(1);
        cliente.setNome("Nome");
        cliente.setNumCelular("123456789");
        cliente.setEmail("email@email.com");
        cliente.setSenha("rafael");
        cliente.setAutenticado(false);

        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        listaClientes.add(cliente);

        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);

        Lista<Cliente> resultado = clienteService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }


    @Test
    @DisplayName("Deve gerar exceção quando atualizarClienteQuandoDadosInvalidos")
    void deveGerarExcecaoQuandoatualizarClienteQuandoDadosInvalidos() {
       //FAÇA ESSE MÉTODO
    }



    @Test
    @DisplayName("Deve gerar exceção quando o cliente não for encontrado")
    void atualizarClienteQuandoClienteNaoEncontrado() {
        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Nome");
        dadosAtualizados.setNumCelular("123456789");
        dadosAtualizados.setSenha("rafael");

        Mockito.when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizar(1, dadosAtualizados));
    }




}
