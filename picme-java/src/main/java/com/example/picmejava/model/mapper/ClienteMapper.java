package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.dto.PerfilClienteDTO;

public class ClienteMapper {
    public PerfilClienteDTO toPerfilClienteDTO(Cliente cliente){
        PerfilClienteDTO dto = new PerfilClienteDTO();

        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setAutenticado(cliente.getAutenticado());
        dto.setTipoUsuario(cliente.getTipoUsuario());
        dto.setPreferencias(cliente.getPreferencias());

        return dto;
    }
}
