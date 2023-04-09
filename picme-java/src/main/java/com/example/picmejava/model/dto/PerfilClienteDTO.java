package com.example.picmejava.model.dto;

import com.example.picmejava.model.Tema;
import lombok.Data;

import java.util.List;

@Data
public class PerfilClienteDTO {
    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
}
