package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Entity

@DiscriminatorValue("1")
public class Cliente extends Usuario{

    @Override
    public int getTipoUsuario() {
        return 1;
    }
}
