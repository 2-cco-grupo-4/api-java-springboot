package com.example.picmejava.controller;

import com.example.picmejava.model.Administrador;
import com.example.picmejava.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void cadastrarAdmin(@RequestBody Administrador administrador){
        String senhaCriptografada = passwordEncoder.encode(administrador.getSenha());
        administrador.setSenha(senhaCriptografada);
        usuarioRepository.save(administrador);
    }
}
