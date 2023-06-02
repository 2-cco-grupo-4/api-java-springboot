package com.example.picmejava.model.dto;

import com.example.picmejava.model.Tema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PerfilEventoDTO {

    private Long id;
    private LocalDate dataRealizacao;
    private String statusEvento;
    private Double valor;
    private PerfilFotografoDTO fotografo;
    private Tema tema;
    private PerfilClienteDTO cliente;
}
