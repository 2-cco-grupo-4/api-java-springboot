package com.example.picmejava.service.evento.dto;

import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.endereco.dto.PerfilEnderecoDTO;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RetornoEventoDTO {
    private Long id;
    private LocalDate dataRealizacao;
    private String statusEvento;
    private Double valor;
    private PerfilFotografoDTO fotografo;
    private PerfilTemaDTO tema;
    private PerfilClienteDTO cliente;
    private PerfilEnderecoDTO endereco;
}
