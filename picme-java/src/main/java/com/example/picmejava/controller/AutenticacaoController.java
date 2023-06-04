package com.example.picmejava.controller;

import com.example.picmejava.infra.security.DadosTokenJWT;
import com.example.picmejava.infra.security.TokenService;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
        public ResponseEntity efetuarLogin(@RequestBody @Valid LoginUsuarioDTO login){
        System.out.println(login.getEmail());
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        System.out.println(authenticationToken);
        var authentication = manager.authenticate(authenticationToken);
        System.out.println(authentication);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
