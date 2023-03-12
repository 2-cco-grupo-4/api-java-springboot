package com.example.picmejava;

import java.util.Date;

public abstract class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Date dataNasc;
    private String numCelular;
    private Boolean autenticado;

    public Usuario(String nome, String email, String senha, String cpf, Date dataNasc, String numCelular, Boolean autenticado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.numCelular = numCelular;
        this.autenticado = autenticado;
    }

    // METODOS
    // LOGIN
    public abstract String login(Usuario usuario);

    //LOGOFF
    public abstract String logoff(Usuario usuario);

    //GETTERS AND SETTERS

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }
}
