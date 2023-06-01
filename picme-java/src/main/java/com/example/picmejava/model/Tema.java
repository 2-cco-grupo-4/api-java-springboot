package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tema implements Identificavel {

    @Schema(
            description = "Identificador do tema",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TEMA")
    private Integer id;

    @Schema(
            description = "Nome do tema",
            example = "Casamento"
    )
    private String nome;

    @ManyToMany(mappedBy = "temas")
    List<Usuario> usuarios;
}

