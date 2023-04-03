package com.example.picmejava.model;

import javax.persistence.Entity;

@Entity
public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", this.tokenSolicitacao);
    }

    public Integer getTokenSolicitacao() {
        return tokenSolicitacao;
    }

    public void setTokenSolicitacao(Integer tokenSolicitacao) {
        this.tokenSolicitacao = tokenSolicitacao;
    }
}
