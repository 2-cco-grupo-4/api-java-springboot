package com.example.picmejava.controller;


import com.example.picmejava.model.UsuarioInstagram;
import com.example.picmejava.service.InstagramService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Instagram controller",
        description = "Controller responsável por conectar a API REST do Instagram com a API do projeto picme"
)

@RestController
@RequestMapping("/instagram")
public class InstagramController {

    @Autowired
    private InstagramService instagramService;

    @PostMapping
    public ResponseEntity<UsuarioInstagram> acessTokenUsuario(@RequestParam String codigo){
        UsuarioInstagram usuarioInstagram = instagramService.postUsuarioInsta(codigo).block();
        return ResponseEntity.status(200).body(usuarioInstagram);
    }

}