package com.example.picmejava.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditaEnderecoDTO {
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String logradouro;
    private String numero;
    private String complemento;

}