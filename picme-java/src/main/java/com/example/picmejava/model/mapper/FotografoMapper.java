package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.PerfilFotografoDTO;

public class FotografoMapper {

    public PerfilFotografoDTO toPerfilFotografoDTO(Fotografo fotografo){
        PerfilFotografoDTO dto = new PerfilFotografoDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());

        return dto;
    }
}
