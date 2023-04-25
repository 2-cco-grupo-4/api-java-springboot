package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.PerfilClienteDTO;
import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;

public class ClienteMapper {

    public static UsuarioTokenDTO of(Usuario usuario, String token) {
        UsuarioTokenDTO usuarioTokenDto = new UsuarioTokenDTO();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

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
