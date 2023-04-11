package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
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
}
