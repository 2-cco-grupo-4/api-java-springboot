package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_log_acessos")
public class LogAcessos {

    @Schema(
            description = "Identificador do log",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_acessos")
    private Long id;

    @Schema(
            description = "Data de Login do usuário",
            example = "2023-10-13 12:54:36.546"
    )
    private LocalDate dataLogin;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

}
