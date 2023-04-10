package com.example.picmejava.model;

import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario{


    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

}
