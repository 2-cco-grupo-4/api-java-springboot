package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrar(Cliente novoUsuario){
        clientes.add(novoUsuario);
        return novoUsuario;
    }

    public Cliente alterarSenha(Integer id, String novaSenha) throws Exception{
        for (Cliente usuario : clientes){
            if (usuario.getId() == id){
                usuario.setSenha(novaSenha);
                return usuario;
            }
        }
        throw new Exception("Usuário não encontrado!");
    }

    public Cliente login(Cliente buscarUsuario) throws Exception{
        for (Cliente usuario : clientes){
            if (usuario.verificarUsuario(usuario, buscarUsuario)){
                if (usuario.getAutenticado().equals(true)){
                    throw new Exception(String.format("Usuário %s já está ativo", usuario.getNome()));
                }else {
                    usuario.setAutenticado(true);
                    return usuario;
                }
            }
        }
        throw new Exception(String.format("Usuário não encontrado!"));
    }

    public String logoff(Cliente buscarUsuario) throws Exception{
        for (Cliente usuario : clientes){
            if (usuario.verificarUsuario(usuario, buscarUsuario)){
                if (usuario.getAutenticado().equals(false)){
                    throw new Exception(String.format("Usuário %s não está ativo", usuario.getNome()));
                }else {
                    usuario.setAutenticado(false);
                    return String.format("Usuario %s fez logoff com sucesso!", usuario.getNome());
                }
            }
        }

        throw new Exception(String.format("Usuário não encontrado!"));
    }
}
