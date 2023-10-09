package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
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
    @Column(name = "ID_USUARIO")
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
    private LocalDate dataCadastro;

    @Column(name = "celular")
    @Schema(description = "Número de celular do usuário")
    private String numCelular;

    @Schema(description = "Indica se o usuário está autenticado")
    private Boolean autenticado;

    @Column(name = "tipo_usuario", insertable = false, updatable = false)
    private int tipoUsuario;

    @Schema(description = "Token de solicitação do usuário")
    private String tokenSolicitacao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_tema",
            joinColumns = @JoinColumn(name = "ID_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_TEMA")
    )
    private List<Tema> temas;

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
