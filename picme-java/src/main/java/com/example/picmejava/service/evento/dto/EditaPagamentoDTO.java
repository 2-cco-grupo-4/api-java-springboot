package com.example.picmejava.service.evento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditaPagamentoDTO {

    private String forma;
    private Double valor;
    private int parcelas;

}
