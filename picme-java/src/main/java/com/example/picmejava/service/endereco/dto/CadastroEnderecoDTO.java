package com.example.picmejava.service.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroEnderecoDTO {
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private Long idEvento;

}
