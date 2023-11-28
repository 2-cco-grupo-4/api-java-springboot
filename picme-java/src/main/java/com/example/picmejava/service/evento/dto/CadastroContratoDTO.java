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
public class CadastroContratoDTO {

    private LocalDate dataRealizacao;
    private String statusSessao;
    private Long idFotografo;
    private Long idCliente;
    private Long idTema;
    private LocalDateTime createdAt;
}
