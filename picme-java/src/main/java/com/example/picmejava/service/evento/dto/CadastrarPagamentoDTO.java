package com.example.picmejava.service.evento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarPagamentoDTO {

    private String forma;
    private Double valor;
    private int parcelas;
    private Long idSessao;

}