package com.example.picmejava.model.dto;

import com.example.picmejava.model.Evento;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoEnderecoDTO {

    private Integer id;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private Integer numero;
    private String complemento;
    private RetornoEventoDTO evento;
}
