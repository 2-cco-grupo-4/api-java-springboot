package com.example.picmejava;

import java.util.Date;

public class Cliente extends Usuario{

    private String preferencias;

    public Cliente(String nome, String email, String senha, Date dataNasc, String numCelular, Boolean autenticado, String preferencias) {
        super(nome, email, senha, dataNasc, numCelular, autenticado);
        this.preferencias = preferencias;
    }

    @Override
    public String editarPerfil(Usuario usuario) {
        return null;
    }

    public String conectar(Fotografo fotografo) {
        return "Conectado com " + fotografo.getNome();
    }
}
