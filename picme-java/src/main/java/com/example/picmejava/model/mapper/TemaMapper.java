package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilTemaDTO;

public class TemaMapper {

    public static PerfilTemaDTO toPerfilTemaDTO(Tema tema){
        PerfilTemaDTO dto = new PerfilTemaDTO();

        dto.setId(tema.getId());
        dto.setNome(tema.getNome());
        dto.setUsuarios(tema.getUsuarios().stream().map(usuario -> UsuarioMapper.toPerfilUsuarioDTO(usuario)).toList());

        return dto;
    }
}
