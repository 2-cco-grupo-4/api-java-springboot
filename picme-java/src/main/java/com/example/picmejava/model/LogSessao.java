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
@Table(name = "tb_log_sessao")
public class LogSessao {

    @Schema(
            description = "Identificador do log",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log_sessao")
    private Long id;

    @Schema(
            description = "Data de modificação do status da sessão",
            example = "2023-10-13 13:36:35.954"
    )
    private LocalDate dataModificacao;

    @Schema(
            description = "Status da sessão",
            example = "Agendada"
    )
    private String statusSessao;

    @Schema(
            description = "Data de realização da sessão",
            example = "2023-12-10 19:30:00.000"
    )
    private LocalDate dataRealizacao;

    @ManyToOne
    @JoinColumn(name = "fk_sessao")
    private Sessao sessao;

}
