package com.example.picmejava.service.tag;

import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Tag;
import com.example.picmejava.model.mapper.TagMapper;
import com.example.picmejava.model.mapper.TemaMapper;
import com.example.picmejava.repository.TagRepository;
import com.example.picmejava.service.tag.dto.CadastroTagDTO;
import com.example.picmejava.service.tag.dto.PerfilTagDTO;
import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Operation(summary = "Cadastrar uma nova tag")
    public PerfilTagDTO cadastrar(CadastroTagDTO novaTag){
        Tag tag = TagMapper.toTag(novaTag);

        return TagMapper.toPerfilTagDTO(tagRepository.save(tag));
    }

}
