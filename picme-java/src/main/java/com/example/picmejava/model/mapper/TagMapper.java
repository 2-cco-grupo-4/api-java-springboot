package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Tag;
import com.example.picmejava.service.tag.dto.CadastroTagDTO;
import com.example.picmejava.service.tag.dto.PerfilTagDTO;

public class TagMapper {

    public static PerfilTagDTO toPerfilTagDTO(Tag tag){
        PerfilTagDTO dto = new PerfilTagDTO();

        dto.setId(tag.getId());
        dto.setNome(tag.getNome());

        return dto;
    }

    public static Tag toTag(CadastroTagDTO novaTag) {
        Tag tag = new Tag();
        tag.setNome(novaTag.getNome());

        return tag;
    }

}
