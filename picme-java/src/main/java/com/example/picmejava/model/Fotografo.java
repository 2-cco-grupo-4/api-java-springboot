package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario implements Identificavel{
    private String tokenSolicitacao;

    @OneToMany
    @JoinColumn(name = "id") // Nome da coluna que representa a chave estrangeira
    private List<Album> albums;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo");
    }
}
