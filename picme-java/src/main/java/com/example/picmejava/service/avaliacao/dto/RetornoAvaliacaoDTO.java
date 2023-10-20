package com.example.picmejava.service.avaliacao.dto;

import com.example.picmejava.service.evento.dto.RetornoEventoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetornoAvaliacaoDTO {

    private Long id;
    private String descricao;
    private Double nota;
    private RetornoEventoDTO evento;

}
