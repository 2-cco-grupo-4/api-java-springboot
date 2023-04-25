package com.example.picmejava.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tema implements Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TemaEnum tema;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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
                ", tema=" + tema +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
