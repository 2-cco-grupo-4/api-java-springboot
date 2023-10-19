package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_tag")
@NoArgsConstructor
@AllArgsConstructor

public class Tag {

    @Schema(
            description = "Identificador da tag",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tag")
    private Long id;

    @Schema(
            description = "Nome da tag",
            example = "Noite"
    )
    private String nome;

    @ManyToMany(mappedBy = "tags")
    List<Usuario> usuarios;

    @ManyToMany(mappedBy = "tags")
    List<Imagem> imagens;

}
