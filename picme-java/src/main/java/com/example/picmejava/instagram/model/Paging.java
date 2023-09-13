package com.example.picmejava.instagram.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
public class Paging {

    private Cursor cursors;

    public Cursor getCursors() {
        return cursors;
    }

    public void setCursors(Cursor cursors) {
        this.cursors = cursors;
    }
}
