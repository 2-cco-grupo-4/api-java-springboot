package com.example.picmejava.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{

    private List<Caracteristica> preferencias;

    public Cliente(String nome,
                   String email,
                   String senha,
                   String cpf,
                   String dataNasc,
                   String numCelular,
                   List<Caracteristica> preferencias) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
        this.preferencias = preferencias;
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    public List<Caracteristica> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Caracteristica> preferencias) {
        this.preferencias = preferencias;
    }
}
