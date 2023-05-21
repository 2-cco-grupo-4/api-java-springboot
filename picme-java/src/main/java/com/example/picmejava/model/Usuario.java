package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Identificavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNasc;
    private LocalDate dataCadastro;
    private String numCelular;
    private Boolean autenticado;
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private String tipoUsuario;
    private String tokenSolicitacao;

    public abstract String getTipoUsuario();
}
