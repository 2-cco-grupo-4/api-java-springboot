package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pagamento")
public class Pagamento {

    @Schema(
            description = "Identificador do pagamento",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long id;

    @Schema(
            description = "Forma de pagamento escolhida para a sessão",
            example = "Cartão de Crédito"
    )
    private String forma;

    @Schema(
            description = "Valor inteiro da sessão",
            example = "500,00"
    )
    private Double valor;

    @Schema(
            description = "Quantidade de parcelas escolhida para o pagamento da sessão",
            example = "5"
    )
    private Integer parcelas;

    @OneToOne
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;

}
