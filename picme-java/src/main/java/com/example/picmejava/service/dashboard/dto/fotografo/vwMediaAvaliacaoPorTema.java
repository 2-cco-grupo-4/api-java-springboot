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
public class vwMediaAvaliacaoPorTema {

    private String tema;
    private Double media;
    private Long user;

}
