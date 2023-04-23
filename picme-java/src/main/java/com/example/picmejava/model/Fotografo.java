package com.example.picmejava.model;

import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fotografo extends Usuario implements Identificavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tokenSolicitacao;


    public Integer getId() {
        return id;
    }

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", this.tokenSolicitacao);
    }
}
