package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Imagem implements Identificavel {

    @Schema(
            description = "Identificador da imagem",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_IMAGEM")
    private Integer id;

    @Schema(
            description = "Caminho para a imagem",
            example = "https://scontent-iad3-2.cdninstagram.com/v/t51.29350-15/342519547_715840500544965_8565762847588876392_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=89Vs1o0LN_sAX92eU9A&_nc_ht=scontent-iad3-2.cdninstagram.com&edm=ANQ71j8EAAAA&oh=00_AfCjl80cpqI5AxCFJgTGWsLoKOzwyBtmnomfpH3mrBBf5A&oe=6449E5FB"
    )
    @Column(name = "CAMINHO")
    private String path;

    @Schema(
            description = "Tipo de arquivo, teoricamente deve ser imagem",
            example = "IMAGE"
    )
    private String tipo;

    @Schema(
            description = "Descrição retornada pela API do instagram, caso não tenha, será nula",
            example = "Foto demo picme 01"
    )
    private String descricao;

    @Schema(
            description = "ID do albúm que a imagem pertence",
            example = "1"
    )
    @ManyToOne
    @JoinColumn(name = "FK_ALBUM", referencedColumnName = "ID_ALBUM")
    private Album idAlbum;

}
