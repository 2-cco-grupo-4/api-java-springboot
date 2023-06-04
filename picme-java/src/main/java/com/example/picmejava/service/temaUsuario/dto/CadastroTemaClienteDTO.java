package com.example.picmejava.service.temaUsuario.dto;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CadastroTemaClienteDTO {

    private List<Tema> temas;
    private Long idCliente;
}
