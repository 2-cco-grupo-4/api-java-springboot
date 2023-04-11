package com.example.picmejava.model;

import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Fotografo extends Usuario{
    private Integer tokenSolicitacao;

    public Fotografo(CadastroUsuarioDTO novoFotografo) {
        this.setNome(novoFotografo.getNome());
        this.setCpf(novoFotografo.getCpf());
        this.setDataNasc(novoFotografo.getDataNasc());
        this.setEmail(novoFotografo.getEmail());
        this.setSenha(novoFotografo.getSenha());
        this.setNumCelular(novoFotografo.getNumCelular());
    }

    @Override
    public String getTipoUsuario() {
        return String.format("Fotografo, token: %s", this.tokenSolicitacao);
    }

    @Override
    public void atualizarInformacoes(AtualizarUsuarioDTO dados) {
        super.atualizarInformacoes(dados);
    }
}
