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
@Table(name = "tb_log_pesquisas")
public class LogPesquisas {

    @Schema(
            description = "Identificador do log",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_pesquisas")
    private Long id;

    @Schema(
            description = "Termo buscado pelo usuário",
            example = "Aniversario"
    )
    private String termo_busca;

    @Schema(
            description = "Data da pesquisa do usuário",
            example = "2023-10-13 12:59:21.540"
    )
    private LocalDate data_pesquisa;

    @ManyToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

}
