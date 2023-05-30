package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements Identificavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;

    @Schema(description = "Email do usuário", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "********")
    private String senha;

    @Schema(description = "CPF do usuário", example = "123.456.789-00")
    private String cpf;

    @Column(name = "data_nascimento")
    @Schema(description = "Data de nascimento do usuário", example = "1990-01-01")
    private LocalDate dataNasc;

    @Schema(description = "Data de cadastro do usuário", example = "2023-05-30")
    private LocalDate dataCadastro;

    @Column(name = "celular")
    @Schema(description = "Número de celular do usuário", example = "+55 11 98765-4321")
    private String numCelular;

    @Schema(description = "Indicador se o usuário está autenticado", example = "true")
    private Boolean autenticado;
    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private String tipoUsuario;
    private String tokenSolicitacao;
    @ManyToMany
    @JoinColumn(name = "tema_usuarios")
    private List<Tema> temas;
    public abstract String getTipoUsuario();

    public abstract void adicionar(Tema tema);
}
