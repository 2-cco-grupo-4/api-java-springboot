package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario implements Identificavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tokenSolicitacao;


    public Integer getId() {
        return id;
    }

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo");
    }
}
