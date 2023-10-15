package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.INTEGER)
@Schema(description = "Representa um usuário")
public abstract class Usuario implements UserDetails, Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Schema(description = "Nome do usuário")
    private String nome;

    @Schema(description = "E-mail do usuário")
    private String email;

    @Schema(description = "Senha do usuário")
    private String senha;

    @Schema(description = "CPF do usuário")
    private String cpf;

    @Column(name = "data_nascimento")
    @Schema(description = "Data de nascimento do usuário")
    private LocalDate dataNasc;

    @Schema(description = "Data de cadastro do usuário")
    private LocalDateTime dataCadastro;

    @Column(name = "celular")
    @Schema(description = "Número de celular do usuário")
    private String numCelular;

    @Schema(description = "Indica se o usuário está autenticado")
    private Boolean autenticado;

    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private int tipoUsuario;

    @Schema(description = "Token de solicitação do usuário")
    private String tokenSolicitacao;

    @Schema(description = "Cidade de escolha do fotógrafo para receber propostas")
    private String cidadePreferencia;

    @Schema(description = "Estado de escolha do fotógrafo para receber propostas")
    private String estadoPreferencia;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_usuario_tema",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tema")
    )
    private List<Tema> temas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_usuario_tag",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_log_acessos")
    private List<LogAcessos> logAcessos;

    public abstract int getTipoUsuario();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (tipoUsuario) {
            case 0:
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ROLE_FOTOGRAFO"));
                break;
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
