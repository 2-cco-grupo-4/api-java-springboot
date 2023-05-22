package com.example.picmejava.model.dto;

import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilClienteDTO {

    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
    private List<Tema> temas;
}
