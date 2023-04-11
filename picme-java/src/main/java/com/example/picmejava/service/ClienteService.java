package com.example.picmejava.service;

import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrar(CadastroUsuarioDTO novoCliente){
        Cliente cliente = new Cliente(novoCliente);
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }

    public Cliente alterarSenha(Integer idCliente, String novaSenha){
        Optional<Cliente> clienteAlterado = clienteRepository.findById(idCliente);
        Cliente cliente = clienteAlterado.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
        cliente.setSenha(novaSenha);
        return clienteRepository.save(cliente);
    }

    public Cliente login(Cliente buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente logoff(Cliente buscarCliente){
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
