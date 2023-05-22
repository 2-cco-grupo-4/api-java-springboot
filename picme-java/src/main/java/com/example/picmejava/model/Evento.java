package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany
    @JoinColumn(name = "id")
    private List<Fotografo> fotografos;
    @ManyToOne
    private Tema tema;
    @ManyToOne
    private Cliente cliente;
    @OneToOne
    private Endereco endereco;
}
