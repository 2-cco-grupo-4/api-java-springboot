package com.example.picmejava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_sessao")
@Schema(description = "Representa um evento")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sessao")
    private Long id;

    @Schema(description = "Data de realização da sessão")
    private LocalDate dataRealizacao;

    @Schema(description = "Status da sessão")
    private String statusSessao;

    @Schema(description = "Data de criação da sessão")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_fotografo")
    private Fotografo fotografo;

    @ManyToOne
    @JoinColumn(name = "fk_tema")
    private Tema tema;


    @JoinColumn(name = "fk_cliente")
    @ManyToOne(
            cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "sessao")
    private Endereco endereco;

    @OneToOne(mappedBy = "sessao")
    private Pagamento pagamento;

    @OneToOne(mappedBy = "sessao")
    private Avaliacao avaliacao;

    @OneToMany(mappedBy = "sessao")
    private List<LogSessao> logsSessao;

}
