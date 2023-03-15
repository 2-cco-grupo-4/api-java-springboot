package com.example.picmejava.controller;

import com.example.picmejava.dto.FotografoDTO;
import com.example.picmejava.dto.UsuarioDTO;
import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Imagem;
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
    public UsuarioDTO cadastrar(@RequestBody Fotografo novoFotografo){
        return new UsuarioDTO(serviceFotografo.cadastrar(novoFotografo));
    }

    @PutMapping("/alterar/senha")
    public UsuarioDTO alterarSenha(@RequestBody Fotografo buscarFotografo) throws Exception{
        return new UsuarioDTO(serviceFotografo.alterarSenha(buscarFotografo.getId(), buscarFotografo.getSenha()));
    }

    @PatchMapping("/entrar")
    public UsuarioDTO login(@RequestBody Fotografo buscarFotografo) throws Exception {
        return new UsuarioDTO(serviceFotografo.login(buscarFotografo));
    }

    @PatchMapping("/sair")
    public String logoff(@RequestBody Fotografo buscarFotografo) throws Exception{
        return serviceFotografo.logoff(buscarFotografo);
    }

    @GetMapping("/{idFotografo}")
    public FotografoDTO buscarFotografoPorId(@PathVariable Integer idFotografo) throws Exception{
        return new FotografoDTO(serviceFotografo.buscarFotografoPorId(idFotografo));
    }

    @PostMapping("/album/{idFotografo}")
    public FotografoDTO adicionarAlbumAoFotografo(@PathVariable Integer idFotografo, @RequestBody Album album) throws Exception{
        return new FotografoDTO(serviceFotografo.adicionarAlbumAoFotografo(idFotografo, album));
    }

    @PutMapping("/{idFotografo}/albums/{idAlbum}")
    public Album adicionarImagemAoAlbum(@PathVariable Integer idFotografo, @PathVariable Integer idAlbum, @RequestBody Imagem imagem) throws Exception{
        return serviceFotografo.adicionarImagemAoAlbum(idFotografo, idAlbum, imagem);
    }
}
