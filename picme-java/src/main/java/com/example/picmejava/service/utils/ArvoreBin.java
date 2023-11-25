package com.example.picmejava.service.utils;

import com.example.picmejava.model.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ArvoreBin {
    private Node root;

    public ArvoreBin() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void insert(Object data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (data.hashCode() < current.getData().hashCode()) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    public Node search(Object data) {
        Node current = root;
        while (current.getData() != data) {
            if (data.hashCode() < current.getData().hashCode()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    //transforma a árvore em uma lista
    public List<Album> toList() {
        List<Album> lista = new ArrayList<>();
        if (root == null) {
            return lista;
        }
        Stack<Node> pilha = new Stack<>();
        Node current = root;
        while (current != null || pilha.size() > 0) {
            while (current != null) {
                pilha.push(current);
                current = current.getLeft();
            }
            current = pilha.pop();
            lista.add((Album) current.getData());
            current = current.getRight();
        }
        return lista;
    }
}
