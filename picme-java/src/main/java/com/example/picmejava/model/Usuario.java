package com.example.picmejava.model;

import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNasc;
    private String numCelular;
    private Boolean autenticado;
    private String tipoUsuario;

    public abstract String getTipoUsuario();

    public void atualizarInformacoes(AtualizarUsuarioDTO dados) {
        if (dados.getNome() != null){
            this.setNome(dados.getNome());
        }
        if (dados.getSenha() != null){
            this.setSenha(dados.getSenha());
        }
        if (dados.getNumCelular() != null){
            this.setNumCelular(dados.getNumCelular());
        }
        if (dados.getDataNasc() != null){
            this.setDataNasc(dados.getDataNasc());
        }
    }

}
