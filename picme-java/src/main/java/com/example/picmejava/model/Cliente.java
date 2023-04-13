package com.example.picmejava.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("cliente")
public class Cliente extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
