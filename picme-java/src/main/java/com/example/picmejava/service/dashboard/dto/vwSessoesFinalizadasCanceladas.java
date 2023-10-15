package com.example.picmejava.service.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwSessoesFinalizadasCanceladas {

    private String mes;
    private Long convertidas;
    private Long total;
    private Long interrompidas;

}
