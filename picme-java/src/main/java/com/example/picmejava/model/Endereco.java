package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Integer id;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    @OneToOne
    @JoinColumn(name = "FK_EVENTO")
    private Evento evento;


}
