package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Administrador extends Usuario {

    @Override
    public int getTipoUsuario() {
        return 0;
    }
}
