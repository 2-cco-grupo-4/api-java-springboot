package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.CadastroTemaDto;
import com.example.picmejava.model.dto.PerfilTemaDTO;

public class TemaMapper {

    public static PerfilTemaDTO toPerfilTemaDTO(Tema tema){
        PerfilTemaDTO dto = new PerfilTemaDTO();

        dto.setId(tema.getId());
        dto.setNome(tema.getNome());

        return dto;
    }

    public static Tema toTema(CadastroTemaDto novoTema) {
        Tema tema = new Tema();
        tema.setNome(novoTema.getNome());

        return tema;
    }
}
