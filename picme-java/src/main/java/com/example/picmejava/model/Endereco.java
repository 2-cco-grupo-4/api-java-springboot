package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Entity
@Table(name = "tb_endereco")
@Schema(description = "Representa um endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
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
    private String numero;

    @Schema(description = "Complemento do endereço")
    private String complemento;

    @OneToOne
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;
}
