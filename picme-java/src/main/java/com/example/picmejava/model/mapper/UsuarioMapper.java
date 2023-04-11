package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;

public class UsuarioMapper {
    public PerfilUsuarioDTO toPerfilClienteDTO(Cliente cliente){
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setAutenticado(cliente.getAutenticado());
        dto.setTipoUsuario(cliente.getTipoUsuario());

        return dto;
    }

    public PerfilUsuarioDTO toPerfilFotogradoDTO(Fotografo fotografo){
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());

        return dto;
    }

    public Cliente atualizarInformacoes(Cliente cliente, AtualizarUsuarioDTO dadosAtualizados){
        if (dadosAtualizados.getNome() != null){
            cliente.setNome(dadosAtualizados.getNome());
        }
        if (dadosAtualizados.getSenha() != null){
            cliente.setSenha(dadosAtualizados.getSenha());
        }
        if (dadosAtualizados.getNumCelular() != null){
            cliente.setNumCelular(dadosAtualizados.getNumCelular());
        }
        if (dadosAtualizados.getDataNasc() != null){
            cliente.setDataNasc(dadosAtualizados.getDataNasc());
        }
        return cliente;
    }

    public Fotografo atualizarInformacoes(Fotografo fotografo, AtualizarUsuarioDTO dadosAtualizados){
        if (dadosAtualizados.getNome() != null){
            fotografo.setNome(dadosAtualizados.getNome());
        }
        if (dadosAtualizados.getSenha() != null){
            fotografo.setSenha(dadosAtualizados.getSenha());
        }
        if (dadosAtualizados.getNumCelular() != null){
            fotografo.setNumCelular(dadosAtualizados.getNumCelular());
        }
        if (dadosAtualizados.getDataNasc() != null){
            fotografo.setDataNasc(dadosAtualizados.getDataNasc());
        }
        return fotografo;
    }
}
