package com.example.picmejava.service.utils;

import com.example.picmejava.service.tag.Tag;

public class TabelaHash {

    private Node[] tabela;
    private int tamanho;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
    }

    public int funcaoHash(Tag tag) {
        return tag.ordinal() % tamanho;
    }

    public void inserir(Tag tag) {
        int indice = funcaoHash(tag);
        Node node = new Node(tag);
        if (tabela[indice] == null) {
            tabela[indice] = node;
        } else {
            Node aux = tabela[indice];
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(node);
        }
    }

    public void remover(Tag tag) {
        int indice = funcaoHash(tag);
        if (tabela[indice] != null) {
            Node aux = tabela[indice];
            Node anterior = null;
            while (aux != null) {
                if (aux.getHeader().equals(tag)) {
                    if (anterior == null) {
                        tabela[indice] = aux.getNext();
                    } else {
                        anterior.setNext(aux.getNext());
                    }
                    break;
                }
                anterior = aux;
                aux = aux.getNext();
            }
        }
    }

    public boolean buscar(Tag tag) {
        int indice = funcaoHash(tag);
        if (tabela[indice] != null) {
            Node aux = tabela[indice];
            while (aux != null) {
                if (aux.getHeader().equals(tag)) {
                    return true;
                }
                aux = aux.getNext();
            }
        }
        return false;
    }

    public void imprimir() {
        for (int i = 0; i < tamanho; i++) {
            if (tabela[i] != null) {
                Node aux = tabela[i];
                while (aux != null) {
                    System.out.println(aux.getHeader());
                    aux = aux.getNext();
                }
            }
        }
    }

    public Node[] getTabela() {
        return tabela;
    }

    public void setTabela(Node[] tabela) {
        this.tabela = tabela;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
