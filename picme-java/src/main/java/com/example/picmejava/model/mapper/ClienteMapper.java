package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;

public class ClienteMapper {
    public PerfilUsuarioDTO toPerfilClienteDTO(Cliente cliente){
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setAutenticado(cliente.getAutenticado());
        dto.setTipoUsuario(cliente.getTipoUsuario());

        return dto;
    }
}
