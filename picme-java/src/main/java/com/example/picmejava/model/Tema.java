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
public class Tema implements Identificavel {

    @Schema(
            description = "Identificador do tema",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(
            description = "Nome do tema",
            example = "Casamento"
    )
    @Enumerated(EnumType.STRING)
    private TemaEnum tema;

    public TemaEnum getTema() {
        return tema;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "id=" + id +
                ", tema=" + tema + '\'' +
                '}';
    }
}
