package com.example.picmejava.service.evento.dto;

import com.example.picmejava.model.Tema;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;
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
