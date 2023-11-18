package com.example.picmejava.service.utils;

import com.example.picmejava.service.album.dto.RetornoAlbumDTO;

public class BinaryTree {
    Node root;

    BinaryTree(int value) {
        root = new Node(value);
    }

    public BinaryTree() {
        root = null;
    }

    public void insert(RetornoAlbumDTO album) {
        if (root == null) {
            root = new Node(Math.toIntExact(album.getId()));
        } else {
            root.insert(album.getId());
        }
    }
}