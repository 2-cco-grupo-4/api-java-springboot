package com.example.picmejava.controller;

import com.example.picmejava.model.dto.UsuarioDTO;
import com.example.picmejava.model.Fotografo;

import com.example.picmejava.service.FotografoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    private FotografoService serviceFotografo;

    public FotografoController() {
        this.serviceFotografo = new FotografoService();
    }

    @PostMapping()
    public Fotografo cadastrar(@RequestBody Fotografo novoFotografo){
        return serviceFotografo.cadastrar(novoFotografo);
    }

    @PutMapping("/alterar/senha")
    public Fotografo alterarSenha(@RequestBody Fotografo buscarFotografo) throws Exception{
        return serviceFotografo.alterarSenha(buscarFotografo.getId(), buscarFotografo.getSenha());
    }

    @PatchMapping("/entrar")
    public Fotografo login(@RequestBody Fotografo buscarFotografo) throws Exception {
        return serviceFotografo.login(buscarFotografo);
    }

    @PatchMapping("/sair")
    public String logoff(@RequestBody Fotografo buscarFotografo) throws Exception{
        return serviceFotografo.logoff(buscarFotografo);
    }

    @GetMapping("/{idFotografo}")
    public Fotografo buscarFotografoPorId(@PathVariable Integer idFotografo) throws Exception{
        return serviceFotografo.buscarFotografoPorId(idFotografo);
    }
}
