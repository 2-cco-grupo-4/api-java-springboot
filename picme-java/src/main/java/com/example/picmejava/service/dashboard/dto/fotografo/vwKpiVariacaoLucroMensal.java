package com.example.picmejava.service.dashboard.dto.fotografo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwKpiVariacaoLucroMensal {

    private Long fotografo;
    private BigDecimal atual;
    private BigDecimal passado;

}
