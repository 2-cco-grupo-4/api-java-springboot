package com.example.picmejava.instagram;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
public class PagingInstagram {

    private CursorsInstagram cursors;

    public CursorsInstagram getCursors() {
        return cursors;
    }

    public void setCursors(CursorsInstagram cursors) {
        this.cursors = cursors;
    }
}
