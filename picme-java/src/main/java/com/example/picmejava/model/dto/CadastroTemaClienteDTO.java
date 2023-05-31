package com.example.picmejava.model.dto;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastroTemaClienteDTO {

    private List<Tema> temas;
    private Integer idCliente;
}
