package com.example.picmejava.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", this.tokenSolicitacao);
    }

}
