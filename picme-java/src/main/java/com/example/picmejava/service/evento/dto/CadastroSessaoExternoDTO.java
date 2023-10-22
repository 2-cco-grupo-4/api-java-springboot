package com.example.picmejava.service.evento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CadastroSessaoExternoDTO {

    private String cliente;
    private String telefone;
    private LocalDateTime dataRealizacao;
    private String endereco;
    private String cidade;
    private String bairro;
    private String estado;
    private String complemento;
    private String statusSessao;
    private String cep;
    private Long idFotografo;

}
