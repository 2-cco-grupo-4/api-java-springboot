package com.example.picmejava.service.arquivo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwExportCsvSessoesFotografo {

    private Long idFotografo;
    private String nomeCliente;
    private String tema;
    private String status;
    private LocalDateTime dataSessao;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String formaPagamento;
    private BigDecimal valor;
    private int parcelas;

}
