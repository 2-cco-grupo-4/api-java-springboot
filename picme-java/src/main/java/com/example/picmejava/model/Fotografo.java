package com.example.picmejava.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario{

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", super.getTokenSolicitacao());
    }
}
