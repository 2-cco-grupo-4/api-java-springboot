package com.example.picmejava.model;


public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;
    private String caracteristica;

    public Fotografo(String nome,
                     String email,
                     String senha,
                     String cpf,
                     String dataNasc,
                     String numCelular,
                     Integer tokenSolicitacao,
                     String caracteristica) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.tokenSolicitacao = tokenSolicitacao;
        this.caracteristica = caracteristica;
    }

    @Override
    public String getTipoUsuario() {
        return "Fotografo";
    }

    public Integer getTokenSolicitacao() {
        return tokenSolicitacao;
    }

    public void setTokenSolicitacao(Integer tokenSolicitacao) {
        this.tokenSolicitacao = tokenSolicitacao;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }
}
