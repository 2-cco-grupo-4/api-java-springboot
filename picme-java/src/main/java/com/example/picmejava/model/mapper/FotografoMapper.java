package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.PerfilFotogradoDTO;

public class FotografoMapper {
    public PerfilFotogradoDTO toPerfilFotogradoDTO(Fotografo fotografo){
        PerfilFotogradoDTO dto = new PerfilFotogradoDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());

        return dto;
    }
}
