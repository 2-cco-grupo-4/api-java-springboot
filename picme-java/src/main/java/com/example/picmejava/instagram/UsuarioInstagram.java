package com.example.picmejava.instagram;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioInstagram {

    private String access_token;
    private String user_id;

}
