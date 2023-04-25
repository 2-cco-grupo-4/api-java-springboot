package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(
            description = "Identificador do usuário",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(
            description = "Nome do usuário",
            example = "Joaquim Pereira"
    )
    private String nome;

    @Schema(
            description = "E-Mail do usuário",
            example = "joaquim.pereira@gmail.com"
    )
    private String email;

    @Schema(
            description = "Senha do usuário",
            example = "!@@283478324"
    )
    private String senha;

    @Schema(
            description = "CPF do usuário",
            example = "142.228.775-98"
    )
    private String cpf;

    @Schema(
            description = "Data de nascimento do usuário",
            example = "1997-11-22"
    )
    private LocalDate dataNasc;

    @Schema(
            description = "Número de celular do usuário",
            example = "(11) 95423-8642"
    )
    private String numCelular;

    @Schema(
            description = "Boolean que identifica se o usuário está autenticado",
            example = "True"
    )
    private Boolean autenticado;

    @Schema(
            description = "Tipo de usuário",
            example = "1"
    )
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private String tipoUsuario;

    @Schema(
            description = "Token de autenticação do instagram",
            example = "IGQVJ..."
    )
    private String tokenSolicitacao;

    public abstract String getTipoUsuario();

    public Integer getId() {
        return id;
    }
}
