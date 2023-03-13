package com.example.picmejava.model;

import java.util.List;

public class Cliente extends Usuario{

    private List<Preferencia> preferencias;

    public Cliente(String nome,
                   String email,
                   String senha,
                   String cpf,
                   String dataNasc,
                   String numCelular,
                   List<Preferencia> preferencias) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.preferencias = preferencias;
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    public List<Preferencia> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Preferencia> preferencias) {
        this.preferencias = preferencias;
    }
}
