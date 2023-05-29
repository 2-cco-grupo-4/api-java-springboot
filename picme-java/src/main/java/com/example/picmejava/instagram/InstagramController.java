package com.example.picmejava.instagram;


import com.example.picmejava.instagram.UsuarioInstagram;
import com.example.picmejava.instagram.InstagramService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping("/listaImagens")
    public ResponseEntity<ListaImagensInstagram> getImagensUsuario(@RequestParam String accessToken) {
        ListaImagensInstagram listaImagensInstagram = instagramService.getImagensInsta(accessToken).block();
        return ResponseEntity.status(200).body(listaImagensInstagram);
    }

    @GetMapping("/imagem")
    public ResponseEntity<MediaInstagram> getImagem(@RequestParam String idImagem, @RequestParam String accessToken) {
        MediaInstagram mediaInstagram = instagramService.getImagem(idImagem, accessToken).block();
        return ResponseEntity.status(200).body(mediaInstagram);
    }

}
