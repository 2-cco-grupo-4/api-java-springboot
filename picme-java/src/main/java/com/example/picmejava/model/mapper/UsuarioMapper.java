package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;

public class UsuarioMapper {

    public static PerfilUsuarioDTO toPerfilUsuarioDTO(Usuario usuario){
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(usuario.getId());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setNome(usuario.getNome());
        dto.setAutenticado(usuario.getAutenticado());

        return dto;
    }
}
