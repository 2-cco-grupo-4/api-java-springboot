package com.example.picmejava.service.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class vwClientesImediatosMes {

    private String mes;
    private Long agendaram;
    private Long total;
    private Long naoAgendaram;

}
