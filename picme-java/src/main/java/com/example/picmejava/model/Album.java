package com.example.picmejava.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album implements Identificavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tema_id", referencedColumnName = "id")
    private Tema tipo;
    private String descricao;
    private Integer idFotografo;

    @Override
    public Integer getId() {
        return id;
    }
}
