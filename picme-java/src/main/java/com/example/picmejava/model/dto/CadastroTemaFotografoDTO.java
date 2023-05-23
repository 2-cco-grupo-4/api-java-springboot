package com.example.picmejava.model.dto;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastroTemaFotografoDTO {

    private List<Tema> temas;
    private Fotografo fotografo;
}
