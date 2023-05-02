package com.example.picmejava.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsuarioInstagram {

    private String access_token;
    private String user_id;

}
