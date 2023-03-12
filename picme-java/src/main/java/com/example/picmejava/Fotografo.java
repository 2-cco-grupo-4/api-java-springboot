package com.example.picmejava;

import java.util.Date;

public class Fotografo extends Usuario{
    private String certificado;
    private Integer tokenSolicitacao;
    private String especificacao;

    public Fotografo(String nome, String email, String senha, String cpf, Date dataNasc, String numCelular, Boolean autenticado, String certificado, Integer tokenSolicitacao, String especificacao) {
        super(nome, email, senha, cpf, dataNasc, numCelular, autenticado);
        this.certificado = certificado;
        this.tokenSolicitacao = tokenSolicitacao;
        this.especificacao = especificacao;
    }

    //METODOS

    //LOGIN
    @Override
    public String login(Usuario usuario) {
        return null;
    }

    //LOGOFF
    @Override
    public String logoff(Usuario usuario) {
        return null;
    }

    //POSTAR IMAGEM
    public String postarImagem(){
        return null;
    }

    //EDITAR IMAGEM
    public String editarImagem(){
        return null;
    }

    //DELETAR IMAGEM
    public String deletarImagem(){
        return null;
    }

    //CRIAR ALBUM
    public String criarAlbum(){
        return null;
    }

    //EDITAR ALBUM
    public String editarAlbum(){
        return null;
    }

    //EXCLUIR ALBUM
    public String excluirAlbum(){
        return null;
    }

    //ATUALIZAR TOKEN
    public String atualizarAlbum(){
        return null;
    }

    // GETTERS AND SETTERS

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public Integer getTokenSolicitacao() {
        return tokenSolicitacao;
    }

    public void setTokenSolicitacao(Integer tokenSolicitacao) {
        this.tokenSolicitacao = tokenSolicitacao;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }
}
