package com.example.picmejava.service.usuario;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.model.utils.ListaObj;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.infra.exception.ConflitoNoCadastroException;
import com.example.picmejava.service.usuario.dto.*;
import com.example.picmejava.service.utils.TabelaHash;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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

        boolean isValido = validarCadastro(novoCliente.getCpf(), novoCliente.getEmail());
        if (!isValido){
            throw new ConflitoNoCadastroException("Usuário já cadastrado");
        }

        return clienteRepository.save(clienteMapper.toCliente(novoCliente));
    }

    @Operation(summary = "Cadastrar cliente externo")
    public PerfilClienteDTO cadastrarExterno(CadastroClienteExternoDTO novoCliente) {
        return clienteMapper.toPerfilClienteDTO(clienteRepository.save(clienteMapper.clientExternoToCliente(novoCliente)));
    }

    @Operation(summary = "Validar um cliente")
    public Cliente validarCliente(String email, String senha) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmailAndSenha(email, senha);
        clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteOptional.get();
    }

    @Operation(summary = "Listar todos os clientes")
    public ListaObj<Cliente> listar() {
        ListaObj<Cliente> clientes = new ListaObj<>();
        listarClientesRecursivamente(clientes, clienteRepository.findAll().iterator());
        return clientes;
    }

    private void listarClientesRecursivamente(ListaObj<Cliente> clientes, Iterator<Cliente> iterator) {
        if (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            clientes.add(cliente);
            listarClientesRecursivamente(clientes, iterator);
        }
    }

    @Operation(summary = "Atualizar dados de um cliente")
    public Cliente atualizar(Long idCliente, AtualizarUsuarioDTO dadosAtualizados){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteRepository.save(clienteMapper.toClienteAtualizado(cliente, dadosAtualizados));
    }

    @Operation(summary = "Autenticar (login) ou desconectar (logoff) um cliente")
    public Cliente autenticarCliente(LoginUsuarioDTO usuario, boolean autenticar) {
        Cliente cliente = validarCliente(usuario.getEmail(), usuario.getSenha());

        if (autenticar) {
            cliente.setAutenticado(true);
        } else {
            cliente.setAutenticado(false);
        }

        return clienteRepository.save(cliente);
    }

    @Operation(summary = "Validar cadastro")
    public boolean validarCadastro(String cpf, String email) {
        Optional<Cliente> usuarioPorCpf = clienteRepository.findByCpf(cpf);
        Optional<Cliente> usuarioPorEmail = clienteRepository.findByEmail(email);

        if (usuarioPorCpf.isPresent() || usuarioPorEmail.isPresent()) {
            return false;
        }

        return true;
    }

}
