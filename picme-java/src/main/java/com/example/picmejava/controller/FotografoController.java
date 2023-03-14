package com.example.picmejava.controller;

import com.example.picmejava.dto.UsuarioDTO;
import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.service.FotografoService;
import org.apache.catalina.valves.AbstractAccessLogValve;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    private FotografoService serviceFotografo;

    public FotografoController() {
        this.serviceFotografo = new FotografoService();
    }

    @PostMapping()
    public UsuarioDTO cadastrar(@RequestBody Fotografo usuario){
        return new UsuarioDTO(serviceFotografo.cadastrar(usuario));
    }

    @PutMapping("/alterar/senha")
    public UsuarioDTO alterarSenha(@RequestBody Fotografo usuario){
        return new UsuarioDTO(serviceFotografo.alterarSenha(usuario.getId(), usuario.getSenha()));
    }

    @PatchMapping("/entrar")
    public UsuarioDTO login(@RequestBody Fotografo usuario) throws Exception {
        return new UsuarioDTO(serviceFotografo.login(usuario));
    }

    @PatchMapping("/sair")
    public String logoff(@RequestBody Fotografo usuario) throws Exception{
        return serviceFotografo.logoff(usuario);
    }

    @PostMapping("/criar-album")
    public Album criarAlbum(@RequestBody Album album){
        return serviceFotografo.criarAlbum(album);
    }
}
