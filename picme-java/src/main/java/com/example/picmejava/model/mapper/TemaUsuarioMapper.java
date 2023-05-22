package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilTemaDTO;
import com.example.picmejava.model.dto.RetornoTemaUsuarioDTO;

import java.util.List;

public class TemaUsuarioMapper {

    public RetornoTemaUsuarioDTO toTemaUsuarioDTO(List<Tema> temas, Fotografo fotografo){
        RetornoTemaUsuarioDTO dto = new RetornoTemaUsuarioDTO();

        dto.setTemas(temas.stream().map(tema -> TemaMapper.toPerfilTemaDTO(tema)).toList());
        dto.setUsuario(UsuarioMapper.toPerfilUsuarioDTO(fotografo));

        return dto;
    }

    public RetornoTemaUsuarioDTO toTemaUsuarioDTO(List<Tema> temas, Cliente cliente){
        RetornoTemaUsuarioDTO dto = new RetornoTemaUsuarioDTO();

        dto.setTemas(temas.stream().map(tema -> TemaMapper.toPerfilTemaDTO(tema)).toList());
        dto.setUsuario(UsuarioMapper.toPerfilUsuarioDTO(cliente));

        return dto;
    }
}
