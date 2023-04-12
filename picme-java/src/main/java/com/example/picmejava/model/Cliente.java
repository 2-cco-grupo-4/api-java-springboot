package com.example.picmejava.model;

import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Cliente extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
