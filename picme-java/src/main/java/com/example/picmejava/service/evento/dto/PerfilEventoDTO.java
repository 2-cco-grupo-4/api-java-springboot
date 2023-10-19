package com.example.picmejava.service.evento.dto;

import com.example.picmejava.model.Tema;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PerfilEventoDTO {

    private Long id;
    private LocalDateTime dataRealizacao;
    private String statusEvento;
    private LocalDateTime createdAt;
    private PerfilFotografoDTO fotografo;
    private PerfilClienteDTO cliente;
    private PerfilTemaDTO tema;
}
