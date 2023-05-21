package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.PerfilClienteDTO;

import java.time.LocalDate;

public class ClienteMapper {

    public PerfilClienteDTO toPerfilClienteDTO(Cliente cliente){
        PerfilClienteDTO dto = new PerfilClienteDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setAutenticado(cliente.getAutenticado());
        dto.setTipoUsuario(cliente.getTipoUsuario());

        return dto;
    }

    public Cliente toCliente(CadastroUsuarioDTO dados){
        Cliente cliente = new Cliente();

        cliente.setNome(dados.getNome());
        cliente.setCpf(dados.getCpf());
        cliente.setDataNasc(dados.getDataNasc());
        cliente.setEmail(dados.getEmail());
        cliente.setSenha(dados.getSenha());
        cliente.setDataCadastro(LocalDate.now());
        cliente.setNumCelular(dados.getNumCelular());
        cliente.setAutenticado(false);

        return cliente;
    }

    public Cliente toClienteAtualizado(Cliente cliente, AtualizarUsuarioDTO dadosAtualizados){
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
}
