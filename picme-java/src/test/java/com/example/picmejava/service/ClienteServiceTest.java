package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.*;
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

    void cadastrar_retornarTresItensQuandoTresItensCadastrados() {
        //get
        Cliente cliente = ClienteBuilder.criarCliente();
        CadastroUsuarioDTO cadastroDto = ClienteBuilder.criarCadastroUsuarioDto();
        Lista<Cliente> resultadoLista = clienteService.listar();
        List<Cliente> resultado = resultadoLista.toList();
        // Crie uma lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        listaClientes.add(cliente);
        listaClientes.add(cliente);

        //when
        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);

        //assert
        assertFalse(resultado.isEmpty());
        assertEquals(3, resultado.size());

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
        // Crie um cliente autenticado
        Cliente clienteAutenticado = ClienteBuilder.criarCliente();
        clienteAutenticado.setAutenticado(true);

        // Crie os dados de login
        LoginUsuarioDTO dadosLogin = new LoginUsuarioDTO();
        dadosLogin.setEmail("email@example.com");
        dadosLogin.setSenha("senha123");

        // Mock do clienteRepository
        Mockito.when(clienteRepository.findByEmailAndSenha("email@example.com", "senha123")).thenReturn(Optional.of(clienteAutenticado));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteAutenticado);

        // Executar o método logoff()
        Cliente resultado = clienteService.logoff(dadosLogin);

        // Verificar se o cliente foi desautenticado corretamente
        assertFalse(resultado.getAutenticado());
    }




}
