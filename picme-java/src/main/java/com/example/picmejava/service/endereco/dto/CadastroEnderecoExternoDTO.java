package com.example.picmejava.service.endereco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroEnderecoExternoDTO {

    private String endereco;
    private String cidade;
    private String bairro;
    private String estado;
    private String complemento;
    private String cep;
    private Long idSessao;

}
