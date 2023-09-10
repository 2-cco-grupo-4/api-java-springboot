package com.example.picmejava.service.usuario;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.model.utils.ListaObj;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;

import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.service.usuario.mapper.PerfilClienteDtoMapper;
import com.example.picmejava.service.utils.TabelaHash;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Cliente Service", description = "APIs relacionadas a operações de clientes")
public class ClienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper = new ClienteMapper();

    private TabelaHash<String> tabelaHash = new TabelaHash();

    @Operation(summary = "Cadastrar um novo cliente")
    public Cliente cadastrar(CadastroUsuarioDTO novoCliente){
        String senhaCriptografada = passwordEncoder.encode(novoCliente.getSenha());
        novoCliente.setSenha(senhaCriptografada);

        return clienteRepository.save(clienteMapper.toCliente(novoCliente));
    }

    @Operation(summary = "Listar todos os clientes")
    public ListaObj<Cliente> listar() {

        ListaObj<Cliente> clientes = new ListaObj();
        for(Cliente i :  clienteRepository.findAll()){
            clientes.add(i);
        }
        return clientes;
    }
    @Operation(summary = "Atualizar dados de um cliente")
    public Cliente atualizar(Long idCliente, AtualizarUsuarioDTO dadosAtualizados){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteRepository.save(clienteMapper.toClienteAtualizado(cliente, dadosAtualizados));
    }

    @Operation(summary = "Realizar o login de um cliente")
    public Cliente login(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(true);
        return clienteRepository.save(cliente);
    }
    @Operation(summary = "Realizar o logoff de um cliente")
    public Cliente logoff(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }

    @Operation(summary = "Validar um cliente")
    public Cliente validarCliente(String email, String senha) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmailAndSenha(email, senha);
        clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteOptional.get();
    }


    private void adicionarClientesNaTabela() {
        List<Cliente> todosClientes = clienteRepository.findAll();


        for (Cliente cliente : todosClientes) {
            PerfilClienteDTO perfilClienteDTO = PerfilClienteDtoMapper.mapClienteToPerfilClienteDTO(cliente);
            tabelaHash.add(perfilClienteDTO);
        }
    }

    @Operation(summary = "Buscar um cliente pelo nome")
    public List<PerfilClienteDTO> buscarCliente(String nome) {

        adicionarClientesNaTabela();

        List<PerfilClienteDTO> clientes = tabelaHash.searchByFirstChar(nome.charAt(0));

        return clientes;
    }

    public Cliente buscarClientePorNome(String nome) {
        Optional<Cliente> clienteOptional = clienteRepository.findByNome(nome);
        return clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }


}
