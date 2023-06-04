package com.example.picmejava.service.usuario.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilClienteDTO {

    private Long id;
    private String nome;
    private Boolean autenticado;
    private int tipoUsuario;
    private List<PerfilTemaDTO> temas;
}
