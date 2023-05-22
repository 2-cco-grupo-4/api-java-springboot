package com.example.picmejava.model;

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
    private LocalDate dataRealizacao;
    private String status;
    private Double valor;
    private Integer avaliacao;
    @ManyToOne
    private Fotografo fotografo;
    @ManyToOne
    private Tema tema;
    @ManyToOne
    private Cliente cliente;
    @OneToOne(mappedBy = "evento")
    private Endereco endereco;
}
