package com.example.picmejava.service.usuario;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.utils.ListaObj;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.service.usuario.builder.ClienteBuilder;
import com.example.picmejava.service.usuario.builder.FotografoBuilder;
import com.example.picmejava.service.usuario.builder.UsuarioBuilder;
import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteService clienteService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Deve retornar exception quando loguin cliente e cliente não existir")
    void login_clienteQuandoClienteNaoExistir() {
        Cliente cliente = ClienteBuilder.criarCliente();
        LoginUsuarioDTO loginDto = UsuarioBuilder.criarLoginUsuarioDto();

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            clienteService.login(loginDto);
        });

        assertEquals("Cliente não encontrado",exception.getMessage());
    }



    @Test
    @DisplayName("Deve cadastrar o cliente quando dados corretos")
    void cadastrar_clienteQuandoDadosCorretos(){
        Cliente cliente = ClienteBuilder.criarCliente();
        String encode = passwordEncoder.encode(cliente.getSenha());
        CadastroUsuarioDTO cadastroDto = ClienteBuilder.criarCadastroUsuarioDto();

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
        Mockito.when(passwordEncoder.encode(cliente.getSenha())).thenReturn(encode);

        Cliente resultado = clienteService.cadastrar(cadastroDto);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
    }

    @Test
    @DisplayName("Deve retornar três itens quando três itens cadastrados")
    void cadastrar_retornarTresItensQuandoTresItensCadastrados() {
        int tamanhoEsperado = 3;
        List<Cliente> clientes = ClienteBuilder.criarListaCliente();

        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);

        ListaObj<Cliente> resultado = clienteService.listar();

        assertFalse(clientes.isEmpty());
        assertEquals(3, resultado.size());
    }



    @Test
    @DisplayName("Deve lançar exceção quando o cliente não for encontrado")
    void atualizar_clienteQuandoClienteNaoEncontrado() {

        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Novo Nome");
        dadosAtualizados.setNumCelular("NovoNumero");

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizar(1L, dadosAtualizados));

    }

    @Test
    @DisplayName("Deve atualizar o cliente quando os dados são válidos")
    void atualizar_ClienteQuandoDadosValidos() {

        Cliente clienteExistente = ClienteBuilder.criarCliente();
        clienteExistente.setId(1L);


        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Novo Nome");
        dadosAtualizados.setNumCelular("NovoNumero");

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteExistente));
        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(clienteExistente);


        Cliente resultado = clienteService.atualizar(1L, dadosAtualizados);


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
        cliente.setId(1L);
        cliente.setNome("Nome");
        cliente.setNumCelular("123456789");
        cliente.setEmail("email@email.com");
        cliente.setSenha("rafael");
        cliente.setAutenticado(false);

        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        listaClientes.add(cliente);

        Mockito.when(clienteRepository.findAll()).thenReturn(listaClientes);

        ListaObj<Cliente> resultado = clienteService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(2, resultado.size());
    }


    @Test
    @DisplayName("Deve gerar exceção quando atualizarClienteQuandoDadosInvalidos")
    void deveGerarExcecaoQuandoAtualizarClienteQuandoDadosInvalidos() {
        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Nome");
        dadosAtualizados.setNumCelular("123456789");
        dadosAtualizados.setSenha("rafael");

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(new Cliente()));

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizar(1L, dadosAtualizados));
    }



    @Test
    @DisplayName("Deve gerar exceção quando o cliente não for encontrado")
    void atualizarClienteQuandoClienteNaoEncontrado() {
        AtualizarUsuarioDTO dadosAtualizados = new AtualizarUsuarioDTO();
        dadosAtualizados.setNome("Nome");
        dadosAtualizados.setNumCelular("123456789");
        dadosAtualizados.setSenha("rafael");

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.empty());


    }
}