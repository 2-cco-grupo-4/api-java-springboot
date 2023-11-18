package com.example.picmejava.service.utils;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }


    boolean isLeaf() {
        return left == null && right == null;
    }


    boolean hasLeftChild() {
        return left != null;
    }

    boolean hasRightChild() {
        return right != null;
    }

    public void insert(long value) {
        if (value < this.value) {
            if (left == null) {
                left = new Node((int) value);
            } else {
                left.insert(value);
            }
        } else if (value > this.value) {
            if (right == null) {
                right = new Node((int) value);
            } else {
                right.insert(value);
            }
        }
    }

}

