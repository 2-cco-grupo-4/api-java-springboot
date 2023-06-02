package com.example.picmejava.service.temaUsuario.dto;

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
    private Long idFotografo;
}
