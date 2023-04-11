package com.example.picmejava.service;

import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.mapper.UsuarioMapper;
import com.example.picmejava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private UsuarioMapper usuarioMapper = new UsuarioMapper();

    public Cliente cadastrar(CadastroUsuarioDTO novoCliente){
        Cliente cliente = new Cliente(novoCliente);
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    public Cliente atualizar(Integer idCliente, AtualizarUsuarioDTO dadosAtualizados){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
        return clienteRepository.save(usuarioMapper.atualizarInformacoes(cliente, dadosAtualizados));
    }

    public Cliente login(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente logoff(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }

    public Cliente validarCliente(String email, String senha){
        Optional<Cliente> clienteOptional = clienteRepository.findByEmailAndSenha(email, senha);
        clienteOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
        return clienteOptional.get();
    }
}
