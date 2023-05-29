package com.example.picmejava.celia;

import java.util.ArrayList;
import java.util.List;

public class Pilha<T> {
    private Object[] pilha;
    private int tamanho;
    private int topo;

    public Pilha(int capacidade) {
        pilha = new Object[capacidade];
        tamanho = 0;
        topo = -1;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }

    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException("A pilha está cheia");
        }
        pilha[++topo] = info;
        tamanho++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        T elemento = (T) pilha[topo];
        pilha[topo--] = null;
        tamanho--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return (T) pilha[topo];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }
    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        for (int i = topo; i >= 0; i--) {
            lista.add((T) pilha[i]);
        }
        return lista;
    }

}
