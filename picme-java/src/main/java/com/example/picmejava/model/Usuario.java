package com.example.picmejava.model;

public abstract class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String dataNasc;
    private String numCelular;
    private Boolean autenticado;
    private String tipoUsuario;

    public Usuario(String nome,
                   String email,
                   String senha,
                   String cpf,
                   String dataNasc,
                   String numCelular) {
        this.id = (int) (Math.random() * 10000);
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.numCelular = numCelular;
        this.autenticado = false;
        this.tipoUsuario = getTipoUsuario();
    }

    public Usuario() {
    }

    public abstract String getTipoUsuario();

    public Boolean verificarUsuario(Usuario usuario, Usuario buscarUsuario){
        if (usuario.getEmail().equals(buscarUsuario.getEmail()) && usuario.getSenha().equals(buscarUsuario.getSenha())){
            return true;
        }
        return false;
    }

    public Integer getId() {
        return id;
    }

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

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
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
