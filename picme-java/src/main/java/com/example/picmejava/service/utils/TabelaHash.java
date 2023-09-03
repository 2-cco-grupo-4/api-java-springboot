package com.example.picmejava.service.utils;

import com.example.picmejava.model.Cliente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaHash {
    private static final int TAMANHO_TABELA = 26 ;
    private List<LinkedList<String>> tabela;

    public TabelaHash() {
        tabela = new ArrayList<>(TAMANHO_TABELA);
        for (int i = 0; i < TAMANHO_TABELA; i++) {
            tabela.add(new LinkedList<>());
        }
    }

    public void adicionarUsuario(String usuario) {
        char primeiraLetra = Character.toLowerCase(usuario.charAt(0));
        int indice = calcularIndice(primeiraLetra);
        tabela.get(indice).add(usuario);
    }

    public LinkedList<String> pesquisarUsuariosPorNome(String nome) {
        char primeiraLetra = Character.toLowerCase(nome.charAt(0));
        int indice = primeiraLetra - 'a';
        return tabela.get(indice);
    }

    private int calcularIndice(char primeiraLetra) {
        return primeiraLetra % TAMANHO_TABELA;
    }


}

