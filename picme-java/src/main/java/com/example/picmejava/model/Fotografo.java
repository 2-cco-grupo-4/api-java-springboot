package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@DiscriminatorValue("2")
public class Fotografo extends Usuario implements Identificavel{
//    private String tokenSolicitacao;

    @OneToMany
    @JoinColumn(name = "FK_FOTOGRAFO")
    private List<Album> albums;

    @Override
    public int getTipoUsuario() {
        return 2;
    }
}
