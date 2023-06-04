package com.example.picmejava.service.usuario;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper = new ClienteMapper();

    public Cliente cadastrar(CadastroUsuarioDTO novoCliente){
        String senhaCriptografada = passwordEncoder.encode(novoCliente.getSenha());
        novoCliente.setSenha(senhaCriptografada);

        return clienteRepository.save(clienteMapper.toCliente(novoCliente));
    }

    public Lista<Cliente> listar() {

        Lista<Cliente> clientes = new Lista();
        for(Cliente i :  clienteRepository.findAll()){
            clientes.add(i);
        }
        return clientes;
    }

    public Cliente atualizar(Long idCliente, AtualizarUsuarioDTO dadosAtualizados){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteRepository.save(clienteMapper.toClienteAtualizado(cliente, dadosAtualizados));
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
        clienteOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
        return clienteOptional.get();
    }

}
