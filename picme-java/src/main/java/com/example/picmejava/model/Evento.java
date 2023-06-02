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
    @Column(name = "ID_EVENTO")
    private Long id;
    private LocalDate dataRealizacao;
    private String statusEvento;
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
