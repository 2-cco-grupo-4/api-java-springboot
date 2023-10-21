package com.example.picmejava.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "tb_imagem")
public class Imagem implements Identificavel {

    @Schema(
            description = "Identificador da imagem",
            example = "1"
    )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem")
    private Long id;

    @Schema(
            description = "Caminho para a imagem",
            example = "https://scontent-iad3-2.cdninstagram.com/v/t51.29350-15/342519547_715840500544965_8565762847588876392_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=89Vs1o0LN_sAX92eU9A&_nc_ht=scontent-iad3-2.cdninstagram.com&edm=ANQ71j8EAAAA&oh=00_AfCjl80cpqI5AxCFJgTGWsLoKOzwyBtmnomfpH3mrBBf5A&oe=6449E5FB"
    )
    private String mediaUrl;

    @Schema(
            description = "Link para a publicação no instagram",
            example = "https://www.instagram.com/p/CrZRF7zrkuK/"
    )
    private String permalink;

    @Schema(
            description = "Legenda da publicação no instagram",
            example = "Foto demo picme 01"
    )
    private String caption;

    @Schema(
            description = "Tipo de arquivo retornado pelo Instagram, devemos guardar apenas IMAGE",
            example = "IMAGE"
    )
    private String mediaType;

    @Schema(
            description = "Identifica se a imagem veio do instagram ou do S3",
            example = "Insta"
    )
    private String origemImagem;

    @Schema(
            description = "Data da última atualização da imagem",
            example = "2023-10-13 12:59:21.540"
    )
    private LocalDateTime updatedAt;

    @Schema(
            description = "ID do albúm que a imagem pertence",
            example = "1"
    )
    @ManyToOne
    @JoinColumn(name = "FK_ALBUM", referencedColumnName = "ID_ALBUM")
    private Album idAlbum;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_tag_imagem",
            joinColumns = @JoinColumn(name = "id_imagem"),
            inverseJoinColumns = @JoinColumn(name = "id_tag")
    )
    private List<Tag> tags;

}
