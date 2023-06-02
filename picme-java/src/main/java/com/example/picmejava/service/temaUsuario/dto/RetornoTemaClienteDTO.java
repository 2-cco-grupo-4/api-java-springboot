package com.example.picmejava.service.temaUsuario.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RetornoTemaClienteDTO {

    private List<PerfilTemaDTO> temas;
    private PerfilClienteDTO cliente;
}
