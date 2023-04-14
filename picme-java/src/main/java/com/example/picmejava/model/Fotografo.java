package com.example.picmejava.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@DiscriminatorValue("fotografo")
public class Fotografo extends Usuario{

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo");
    }
}
