package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Data de realização do evento", example = "2023-06-15")
    private LocalDate dataRealizacao;

    @Schema(description = "Status do evento", example = "Confirmado")
    private String statusEvento;

    @Schema(description = "Valor do evento", example = "1500.0")
    private Double valor;

    @Schema(description = "Avaliação do evento", example = "4")
    private Integer avaliacao;

    @ManyToOne
    @JoinColumn(name = "FK_FOTOGRAFO")
    private Fotografo fotografo;

    @ManyToOne
    @JoinColumn(name = "FK_TEMA")
    private Tema tema;

    @ManyToOne
    @JoinColumn(name = "FK_CLIENTE")
    private Cliente cliente;

    @OneToOne(mappedBy = "evento")
    private Endereco endereco;
}
