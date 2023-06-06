package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Entity
@Setter
@Getter
@DiscriminatorValue("1")
public class Cliente extends Usuario{

    @Override
    public int getTipoUsuario() {
        return 1;
    }
}
