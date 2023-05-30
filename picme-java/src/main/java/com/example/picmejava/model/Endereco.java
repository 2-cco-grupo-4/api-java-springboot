package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do endereço", example = "1")
    private Integer id;

    @Schema(description = "Estado do endereço", example = "São Paulo")
    private String estado;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @Schema(description = "Logradouro do endereço", example = "Rua Exemplo")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "123")
    private Integer numero;

    @Schema(description = "Complemento do endereço", example = "Apartamento 456")
    private String complemento;

    @OneToOne
    @JoinColumn(name = "FK_EVENTO")
    private Evento evento;
}
