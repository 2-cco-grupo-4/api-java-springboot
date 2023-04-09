package com.example.picmejava.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente extends Usuario{


    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }

}
