package com.example.picmejava.service.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioTokenDTO {

    private Integer id;
    private String nome;
    private String email;
    private String token;

}
