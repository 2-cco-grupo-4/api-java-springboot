package com.example.picmejava.controller;

import com.example.picmejava.model.Fotografo;

import com.example.picmejava.model.dto.PerfilFotogradoDTO;
import com.example.picmejava.model.mapper.FotografoMapper;
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
    private FotografoMapper fotografoMapper = new FotografoMapper();


    @PostMapping()
    public ResponseEntity<PerfilFotogradoDTO> cadastrar(@RequestBody @Valid Fotografo novoFotografo){
        return ResponseEntity.status(201).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.cadastrar(novoFotografo)
        ));
    }

    @PutMapping("/alterar/senha")
    public ResponseEntity<PerfilFotogradoDTO> alterarSenha(@RequestBody @Valid Fotografo fotografoAtualizado){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.alterarSenha(fotografoAtualizado.getId(), fotografoAtualizado.getSenha())
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilFotogradoDTO> login(@RequestBody @Valid Fotografo buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.login(buscarFotografo)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilFotogradoDTO> logoff(@RequestBody @Valid Fotografo buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }
}
