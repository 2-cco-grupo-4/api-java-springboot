package com.example.picmejava.service;

import com.example.picmejava.model.Fotografo;

import java.util.ArrayList;
import java.util.List;

public class FotografoService {

    private List<Fotografo> fotografos = new ArrayList<>();

    public Fotografo cadastrar(Fotografo novoUsuario){
        fotografos.add(novoUsuario);
        return novoUsuario;
    }

    public Fotografo alterarSenha(Integer id, String novaSenha){
        for (Fotografo usuario : fotografos){
            if (usuario.getId() == id){
                usuario.setSenha(novaSenha);
                return usuario;
            }
        }
        return null;
    }

    public Fotografo login(Fotografo buscarUsuario) throws Exception{
        for (Fotografo usuario : fotografos){
            if (usuario.verificarUsuario(usuario, buscarUsuario)){
                if (usuario.getAutenticado().equals(true)){
                    throw new Exception(String.format("Usuário %s não já está ativo", usuario.getNome()));
                }else {
                    usuario.setAutenticado(true);
                    return usuario;
                }
            }
        }
        throw new Exception(String.format("Usuário não encontrado"));
    }

    public String logoff(Fotografo buscarUsuario) throws Exception{
        for (Fotografo usuario : fotografos){
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
