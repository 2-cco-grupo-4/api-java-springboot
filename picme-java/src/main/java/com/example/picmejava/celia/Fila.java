package com.example.picmejava.celia;

import java.util.ArrayList;
import java.util.List;

public class Fila<T> {
    private Object[] fila;
    private int tamanho;
    private int frente;
    private int tras;

    public Fila(int capacidade) {
        fila = new Object[capacidade];
        tamanho = 0;
        frente = 0;
        tras = -1;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void push(T item) {
        if (isFull()) {
            throw new IllegalStateException("A fila está cheia");
        }
        fila[++tras] = item;
        tamanho++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        T elemento = (T) fila[frente];
        fila[frente++] = null;
        frente = frente % fila.length;
        tamanho--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return (T) fila[frente];
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            int indice = frente;
            for (int i = 0; i < tamanho; i++) {
                System.out.println(fila[indice]);
                indice = (indice + 1) % fila.length;
            }
        }
    }

    public List<T> toList() {
        List<T> lista = new ArrayList<>();
        for (int i = frente; i <= tras; i++) {
            lista.add((T) fila[i]);
        }
        return lista;
    }


}
