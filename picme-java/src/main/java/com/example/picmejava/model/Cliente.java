package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Entity

@DiscriminatorValue("cliente")
public class Cliente extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    @Override
    public void adicionar(Tema tema) {
        if (getTemas() == null){
            setTemas(new ArrayList<>());
        }
        getTemas().add(tema);
    }
}
