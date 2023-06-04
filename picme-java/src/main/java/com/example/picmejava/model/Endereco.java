package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Getter
@Setter
@Entity
@Schema(description = "Representa um endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Schema(description = "Estado do endereço")
    private String estado;

    @Schema(description = "Cidade do endereço")
    private String cidade;

    @Schema(description = "CEP do endereço")
    private String cep;

    @Schema(description = "Bairro do endereço")
    private String bairro;

    @Schema(description = "Logradouro do endereço")
    private String logradouro;

    @Schema(description = "Número do endereço")
    private Integer numero;

    @Schema(description = "Complemento do endereço")
    private String complemento;

    @OneToOne
    @JoinColumn(name = "FK_EVENTO")
    private Evento evento;
}
