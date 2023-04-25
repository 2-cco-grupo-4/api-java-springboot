package com.example.picmejava.service.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Setter
@Getter
public class UsuarioLoginDTO {

    private String email;
    private String senha;

}
