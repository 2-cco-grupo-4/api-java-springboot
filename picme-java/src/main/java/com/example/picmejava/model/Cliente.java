package com.example.picmejava.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Usuario{

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Tema> preferencias;

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

    public List<Tema> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<Tema> preferencias) {
        this.preferencias = preferencias;
    }
}
