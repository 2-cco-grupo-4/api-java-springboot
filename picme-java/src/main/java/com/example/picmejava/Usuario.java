package com.example.picmejava;

import java.util.Date;

public abstract class Usuario {

    private String nome;
    private String email;
    private String senha;
    private Date dataNasc;
    private String numCelular;
    private Boolean autenticado;

    public Usuario(String nome, String email, String senha, Date dataNasc, String numCelular, Boolean autenticado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.numCelular = numCelular;
        this.autenticado = autenticado;
    }

    public abstract String editarPerfil(Usuario usuario);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }
}
