package com.example.picmejava.model;

import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", this.tokenSolicitacao);
    }

    @Override
    public void atualizarInformacoes(AtualizarUsuarioDTO dados) {
        super.atualizarInformacoes(dados);
    }
}
