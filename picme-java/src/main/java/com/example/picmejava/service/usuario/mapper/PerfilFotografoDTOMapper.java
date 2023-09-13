package com.example.picmejava.service.usuario.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;

public class PerfilFotografoDTOMapper {

    public static PerfilFotografoDTO mapFotografo(Fotografo fotografo) {
        PerfilFotografoDTO perfilFotografoDTO = new PerfilFotografoDTO();
        perfilFotografoDTO.setId(fotografo.getId());
        perfilFotografoDTO.setNome(fotografo.getNome());
        return perfilFotografoDTO;
    }

}