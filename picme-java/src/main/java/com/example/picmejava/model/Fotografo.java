package com.example.picmejava.model;


import java.util.ArrayList;
import java.util.List;

public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    private List<Album> albuns;

    public Fotografo(String nome,
                     String email,
                     String senha,
                     String cpf,
                     String dataNasc,
                     String numCelular,
                     Integer tokenSolicitacao,
                     List<Album> albuns) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.tokenSolicitacao = tokenSolicitacao;
        this.albuns = albuns;
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

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
}
