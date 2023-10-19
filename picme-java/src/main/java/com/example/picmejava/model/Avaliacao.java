package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_avaliacao")
@Getter
@Setter
public class Avaliacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Long id;

    @Schema(
            description = "Descrição da avaliação, texto que o usuário insere",
            example = "Gostei muito da sua fotografia! Parabéns!"
    )
    private String descricao;

    @Schema(
            description = "Nota da avaliação, de 0 a 5",
            example = "4.5"
    )
    @Max(5)
    private Double nota;

    @OneToOne
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;
}