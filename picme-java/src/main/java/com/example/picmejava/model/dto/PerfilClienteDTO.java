package com.example.picmejava.model.dto;

import com.example.picmejava.model.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilClienteDTO {
    private Integer id;
    private String nome;
    private Boolean autenticado;
    private String tipoUsuario;
}
