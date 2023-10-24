package com.example.picmejava.infra.security;

public record DadosTokenJWT(String token, Long id, String nome, int tipoUsuario, String token_solicitacao) {
}
