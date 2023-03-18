package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrar(Cliente novoCliente){
        clientes.add(novoCliente);
        return novoCliente;
    }

    public Cliente alterarSenha(Integer idCliente, String novaSenha) throws Exception{
        Cliente cliente = buscarClientePorId(idCliente);
        if (!cliente.equals(null)){
            cliente.setSenha(novaSenha);
            return cliente;
        }
        throw new Exception("Cliente não encontrado!");
    }

    public Cliente buscarClientePorId(Integer idCliente) throws Exception{
        for (Cliente cliente : clientes){
            if (cliente.getId().equals(idCliente)){
                return cliente;
            }
        }
        throw new Exception("Cliente não encontrado!");
    }

    public Cliente login(Cliente buscarCliente) throws Exception{
        for (Cliente cliente : clientes){
            if (cliente.verificarUsuario(cliente, buscarCliente)){
                if (cliente.getAutenticado().equals(true)){
                    throw new Exception(String.format("Cliente %s já está ativo", cliente.getNome()));
                }else {
                    cliente.setAutenticado(true);
                    return cliente;
                }
            }
        }
        throw new Exception(String.format("Cliente não encontrado!"));
    }

    public String logoff(Cliente buscarCliente) throws Exception{
        for (Cliente cliente : clientes){
            if (cliente.verificarUsuario(cliente, buscarCliente)){
                if (cliente.getAutenticado().equals(false)){
                    throw new Exception(String.format("Cliente %s não está ativo", cliente.getNome()));
                }else {
                    cliente.setAutenticado(false);
                    return String.format("Cliente %s fez logoff com sucesso!", cliente.getNome());
                }
            }
        }

        throw new Exception(String.format("Cliente não encontrado!"));
    }
}
