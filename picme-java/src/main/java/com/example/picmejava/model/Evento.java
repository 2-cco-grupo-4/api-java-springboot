package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Schema(description = "Representa um evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id;

    @Schema(description = "Data de realização do evento")
    private LocalDate dataRealizacao;

    @Schema(description = "Status do evento")
    private String statusEvento;

    @Schema(description = "Valor do evento")
    private Double valor;

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
