package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario implements Identificavel{
    private String tokenSolicitacao;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo");
    }
}
