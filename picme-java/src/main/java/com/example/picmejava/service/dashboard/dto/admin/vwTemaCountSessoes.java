package com.example.picmejava.service.dashboard.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwTemaCountSessoes {

    private String tema;
    private Long sessoes;
    private BigDecimal valor;
}
