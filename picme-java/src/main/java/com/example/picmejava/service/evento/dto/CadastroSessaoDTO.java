package com.example.picmejava.service.evento.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CadastroSessaoDTO {

    private LocalDateTime dataRealizacao;
    private String statusSessao;
    private Long idFotografo;
    private Long idCliente;
    private Long idEndereco;
    private Long idTema;
}
