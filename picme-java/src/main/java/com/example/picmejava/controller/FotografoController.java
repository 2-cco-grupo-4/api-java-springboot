package com.example.picmejava.controller;


import com.example.picmejava.model.dto.PerfilFotografoDTO;
import com.example.picmejava.model.Fotografo;

import com.example.picmejava.service.FotografoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    private FotografoService serviceFotografo;

    public FotografoController() {
        this.serviceFotografo = new FotografoService();
    }

    @PostMapping()
    public ResponseEntity<PerfilFotografoDTO> cadastrar(@RequestBody Fotografo novoFotografo){
        return ResponseEntity.status(201).body(serviceFotografo.cadastrar(novoFotografo));
    }

    @PutMapping("/alterar/senha")
    public PerfilFotografoDTO alterarSenha(@RequestBody Fotografo buscarFotografo){
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
    public Fotografo buscarFotografoPorId(@PathVariable Integer idFotografo){
        return serviceFotografo.buscarFotografoPorId(idFotografo);
    }
}
