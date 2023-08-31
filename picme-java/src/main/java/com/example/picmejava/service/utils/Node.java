package com.example.picmejava.service.utils;

import com.example.picmejava.service.tag.Tag;

public class Node {
    Tag header;
    Node next;

    public Node(Tag header) {
        this.header = header;
        this.next = null;
    }

    public Tag getHeader() {
        return header;
    }

    public void setHeader(Tag header) {
        this.header = header;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }



}
