package com.example.picmejava.service.dashboard.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwEstadosMaisSessoes {

    private String estado;
    private Long total;
    private String mes;
    private int ano;

}
