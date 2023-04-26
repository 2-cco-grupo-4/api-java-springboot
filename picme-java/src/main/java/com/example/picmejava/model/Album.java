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
public class Album implements Identificavel{

    @Schema(
            description = "Identificador do albúm",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(
            description = "Nome dado ao albúm",
            example = "Album casamento Carlos e Maria"
    )
    private String titulo;

    @Schema(
            description = "Tema do albúm",
            example = "CASAMENTO"
    )
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tema_id", referencedColumnName = "id")
    private Tema tipo;

    @Schema(
            description = "Descrição do albúm",
            example = "Fotos de um casamento no estilo vintage realizado em 18/03/2022"
    )
    private String descricao;

    @Schema(
            description = "Identificador do Fotógrafo",
            example = "1"
    )
    private Integer idFotografo;

    @Override
    public Integer getId() {
        return id;
    }
}
