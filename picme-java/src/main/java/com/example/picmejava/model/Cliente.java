package com.example.picmejava.model;

import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Cliente extends Usuario{


    public Cliente(CadastroUsuarioDTO novoCliente) {
        this.setNome(novoCliente.getNome());
        this.setCpf(novoCliente.getCpf());
        this.setDataNasc(novoCliente.getDataNasc());
        this.setEmail(novoCliente.getEmail());
        this.setSenha(novoCliente.getSenha());
        this.setNumCelular(novoCliente.getNumCelular());
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
