package com.example.picmejava.model;


public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;
    private String especificacao;

    public Fotografo(String nome,
                     String email,
                     String senha,
                     String cpf,
                     String dataNasc,
                     String numCelular,
                     Integer tokenSolicitacao,
                     String especificacao) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.tokenSolicitacao = tokenSolicitacao;
        this.especificacao = especificacao;
    }

    @Override
    public String getTipoUsuario() {
        return "Fotografo";
    }

    public String postarImagem(){
        return null;
    }

    public String criarAlbum(){
        return null;
    }

    public String editarAlbum(){
        return null;
    }

    public String excluirAlbum(){
        return null;
    }

    public String atualizarAlbum(){
        return null;
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
