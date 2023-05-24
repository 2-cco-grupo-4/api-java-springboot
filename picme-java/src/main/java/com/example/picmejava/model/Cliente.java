package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@Entity

@DiscriminatorValue("cliente")
public class Cliente extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
