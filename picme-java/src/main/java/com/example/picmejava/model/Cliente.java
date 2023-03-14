package com.example.picmejava.model;

import java.util.List;

public class Cliente extends Usuario{

    public Cliente(String nome,
                   String email,
                   String senha,
                   String cpf,
                   String dataNasc,
                   String numCelular) {
        super(nome, email, senha, cpf, dataNasc, numCelular);
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
