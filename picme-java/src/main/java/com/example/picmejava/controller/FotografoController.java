package com.example.picmejava.controller;

import com.example.picmejava.model.Fotografo;

import com.example.picmejava.service.FotografoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private FotografoService serviceFotografo;


    @PostMapping()
    public ResponseEntity<Fotografo> cadastrar(@RequestBody @Valid Fotografo novoFotografo){
        return ResponseEntity.status(201).body(serviceFotografo.cadastrar(novoFotografo));
    }

    @PutMapping("/alterar/senha")
    public ResponseEntity<Fotografo> alterarSenha(@RequestBody @Valid Fotografo fotografoAtualizado) throws Exception {
        return ResponseEntity.status(200).body(serviceFotografo.alterarSenha(fotografoAtualizado.getId(), fotografoAtualizado.getSenha()));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<Fotografo> login(@RequestBody @Valid Fotografo buscarFotografo) throws Exception {
        return ResponseEntity.status(200).body(serviceFotografo.login(buscarFotografo));
    }

    @PatchMapping("/sair")
    public ResponseEntity<Fotografo> logoff(@RequestBody @Valid Fotografo buscarFotografo) throws Exception{
        return ResponseEntity.status(200).body(serviceFotografo.logoff(buscarFotografo));
    }
}
