package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.RetornoTemaClienteDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;

import java.util.List;

public class TemaUsuarioMapper {

    public RetornoTemaFotografoDTO toRetornoTemaUsuarioDTO(List<Tema> temas, Fotografo fotografo){
        RetornoTemaFotografoDTO dto = new RetornoTemaFotografoDTO();

        dto.setTemas(temas.stream().map(TemaMapper::toPerfilTemaDTO).toList());
        dto.setFotografo(UsuarioMapper.toPerfilFotografoDTO(fotografo));

        return dto;
    }

    public RetornoTemaClienteDTO toRetornoTemaUsuarioDTO(List<Tema> temas, Cliente cliente){
        RetornoTemaClienteDTO dto = new RetornoTemaClienteDTO();

        dto.setTemas(temas.stream().map(TemaMapper::toPerfilTemaDTO).toList());
        dto.setCliente(UsuarioMapper.toPerfilClienteDTO(cliente));

        return dto;
    }
}
