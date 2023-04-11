package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;

public class FotografoMapper {
    public PerfilUsuarioDTO toPerfilFotogradoDTO(Fotografo fotografo){
        PerfilUsuarioDTO dto = new PerfilUsuarioDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());

        return dto;
    }
}
