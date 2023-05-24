package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AVALIACAO")
@Getter
@Setter
public class Avaliacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Double valor ;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
