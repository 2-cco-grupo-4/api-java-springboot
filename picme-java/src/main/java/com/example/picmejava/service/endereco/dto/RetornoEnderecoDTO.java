package com.example.picmejava.service.endereco.dto;

import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoEnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    private RetornoEventoDTO evento;
}
