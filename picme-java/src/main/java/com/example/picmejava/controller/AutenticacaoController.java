package com.example.picmejava.controller;

import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid LoginUsuarioDTO login){
        var token = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha());
        var authenticate = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
