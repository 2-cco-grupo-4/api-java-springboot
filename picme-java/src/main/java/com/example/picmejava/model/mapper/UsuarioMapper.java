package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.PerfilClienteDTO;
import com.example.picmejava.model.dto.PerfilFotografoDTO;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;

public class UsuarioMapper {

    public static PerfilFotografoDTO toPerfilFotografoDTO(Fotografo dados){
        PerfilFotografoDTO dto = new PerfilFotografoDTO();

        dto.setId(dados.getId());
        dto.setTipoUsuario(dados.getTipoUsuario());
        dto.setNome(dados.getNome());
        dto.setAutenticado(dados.getAutenticado());

        return dto;
    }

    public static PerfilClienteDTO toPerfilClienteDTO (Cliente dados){
        PerfilClienteDTO dto = new PerfilClienteDTO();

        dto.setId(dados.getId());
        dto.setTipoUsuario(dados.getTipoUsuario());
        dto.setNome(dados.getNome());
        dto.setAutenticado(dados.getAutenticado());

        return dto;
    }

    public static PerfilUsuarioDTO toPerfilUsuarioDTO(Usuario usuario) {
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(usuario.getId());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setNome(usuario.getNome());
        dto.setAutenticado(usuario.getAutenticado());

        return dto;
    }
}
