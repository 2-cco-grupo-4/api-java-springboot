package com.example.picmejava.instagram.controller;


import com.example.picmejava.instagram.model.AccessToken;
import com.example.picmejava.instagram.model.LongAccessToken;
import com.example.picmejava.instagram.service.InstagramService;
import com.example.picmejava.instagram.model.ListData;
import com.example.picmejava.instagram.model.Media;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Instagram controller",
        description = "Controller responsável por conectar a API REST do Instagram com a API do projeto picme"
)

@RestController
@RequestMapping("/instagram")
public class InstagramController {

    @Autowired
    private InstagramService instagramService;

    @Operation(summary = "Obter access token do usuário", description = "Endpoint para acessar o token de usuário do Instagram")
    @PostMapping
    public ResponseEntity<AccessToken> acessTokenUsuario(@RequestParam String codigo){
        // Melhorar tratativa de retorno
        AccessToken accessToken = instagramService.postUsuarioInsta(codigo).block();
        return ResponseEntity.status(200).body(accessToken);
    }

    @Operation(summary = "Obter lista de imagens do usuário", description = "Endpoint para obter a lista de imagens do usuário no Instagram")
    @GetMapping("/listaMedia")
    public ResponseEntity<ListData> getImagensUsuario(@RequestParam String accessToken) {
        ListData listData = instagramService.getImagensInsta(accessToken).block();
        return ResponseEntity.status(200).body(listData);
    }

    @Operation(summary = "Obter imagem", description = "Endpoint para obter uma imagem específica do Instagram")
    @GetMapping("/imagem")
    public ResponseEntity<Media> getImagem(@RequestParam String idImagem, @RequestParam String accessToken) {
        Media media = instagramService.getImagem(idImagem, accessToken).block();
        return ResponseEntity.status(200).body(media);
    }

    @Operation(summary = "Obter lista de imagens", description = "Endpoint para obter a lista de imagens e seus detalhes do Instagram")
    @GetMapping("/listImagem")
    public ResponseEntity<List<Media>> getListImagem(@RequestParam String accessToken) {
        List<Media> listaMedia = instagramService.getListImagens(accessToken);
        return ResponseEntity.status(200).body(listaMedia);
    }

    @Operation(summary = "Obter access token de longa duração", description = "Endpoint para obter o access token de longa duração (60 Dias) do Usuário do Instagram")
    @GetMapping("/longAccessToken")
    public ResponseEntity<LongAccessToken> getLongAccessToken(@RequestParam String accessToken) {
        LongAccessToken longAccessToken = instagramService.getLongAccessToken(accessToken).block();

        // Talves de pra melhorar o tratamento...
        if (longAccessToken != null) {
            return ResponseEntity.status(200).body(longAccessToken);
        } else {
            return ResponseEntity.status(400).build();
        }

    }

    @Operation(summary = "Renovar access token de longa duração", description = "Endpoint para renovar o access token de longa duração por mais 60 dias")
    @GetMapping("/refreshLongAccessToken")
    public ResponseEntity<LongAccessToken> refreshLongAccessToken(@RequestParam String accessToken) {
        LongAccessToken longAccessToken = instagramService.getRefreshedLongAccessToken(accessToken).block();

        if(longAccessToken != null) {
            return ResponseEntity.status(200).body(longAccessToken);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

}
