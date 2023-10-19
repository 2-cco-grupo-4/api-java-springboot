package com.example.picmejava.service.dashboard.dto.fotografo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwKpiPropostasRecebidasMesAtual {

    private Long fotografo;
    private String mesAtual;
    private Long mesAtualTotal;
    private String mesAnterior;
    private Long mesAnteriorTotal;

}
