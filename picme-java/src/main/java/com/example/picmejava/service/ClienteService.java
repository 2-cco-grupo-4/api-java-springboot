package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrar(Cliente novoCliente){
        novoCliente.setAutenticado(false);
        return clienteRepository.save(novoCliente);
    }

    public Cliente alterarSenha(Integer idCliente, String novaSenha) throws Exception{
        Optional<Cliente> clienteAlterado = clienteRepository.findById(idCliente);
        Cliente cliente = clienteAlterado.orElseThrow(() -> new Exception("Cliente não encontrado"));
        cliente.setSenha(novaSenha);
        return clienteRepository.save(cliente);
    }

    public Cliente login(Cliente buscarCliente) throws Exception{
        Optional<Cliente> clienteEncontrado = clienteRepository.findByEmail(buscarCliente.getEmail());
        Cliente cliente = clienteEncontrado.orElseThrow(() -> new Exception("Cliente não encontrado"));
        cliente.setAutenticado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente logoff(Cliente buscarCliente) throws Exception{
        Optional<Cliente> clienteEncontrado = clienteRepository.findByEmail(buscarCliente.getEmail());
        Cliente cliente = clienteEncontrado.orElseThrow(() -> new Exception("Cliente não encontrado"));
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }
}
