package com.example.picmejava.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
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
    private String tokenSolicitacao;
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private String tipoUsuario;

    public abstract String getTipoUsuario();

}
