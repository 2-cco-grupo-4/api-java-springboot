package com.example.picmejava;

import java.util.Date;

public class Fotografo extends Usuario{
    private String tokenSolicitacao;
    private String especificacao;

    public Fotografo(String nome, String email, String senha, Date dataNasc, String numCelular, Boolean autenticado, String tokenSolicitacao, String especificacao) {
        super(nome, email, senha, dataNasc, numCelular, autenticado);
        this.tokenSolicitacao = tokenSolicitacao;
        this.especificacao = especificacao;
    }

    @Override
    public String editarPerfil(Usuario usuario) {
        return null;
    }

    public String getTokenSolicitacao() {
        return tokenSolicitacao;
    }

    public void setTokenSolicitacao(String tokenSolicitacao) {
        this.tokenSolicitacao = tokenSolicitacao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }
}
