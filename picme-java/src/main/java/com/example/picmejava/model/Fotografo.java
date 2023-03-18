package com.example.picmejava.model;

public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    public Fotografo(String nome,
                     String email,
                     String senha,
                     String cpf,
                     String dataNasc,
                     String numCelular,
                     Integer tokenSolicitacao) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.tokenSolicitacao = tokenSolicitacao;
    }

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
