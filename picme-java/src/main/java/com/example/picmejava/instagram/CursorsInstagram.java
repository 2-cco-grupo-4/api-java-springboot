    package com.example.picmejava.instagram;

    import com.fasterxml.jackson.annotation.JsonAutoDetect;
    import lombok.NoArgsConstructor;

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @NoArgsConstructor
    public class CursorsInstagram {

        private String before;
        private String after;

    }
