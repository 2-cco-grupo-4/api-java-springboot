package com.example.picmejava.controller;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.PerfilUsuarioDTO;
import com.example.picmejava.model.mapper.UsuarioMapper;
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
    private UsuarioMapper usuarioMapper = new UsuarioMapper();


    @PostMapping()
    public ResponseEntity<PerfilUsuarioDTO> cadastrar(@RequestBody @Valid Fotografo novoFotografo){
        return ResponseEntity.status(201).body(usuarioMapper.toPerfilFotogradoDTO(
                serviceFotografo.cadastrar(novoFotografo)
        ));
    }

    @PutMapping("/atualizar/{idFotografo}")
    public ResponseEntity<PerfilUsuarioDTO> atualizar(
            @PathVariable Integer idFotografo, @RequestBody @Valid AtualizarUsuarioDTO fotografoAtualizado
    ){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilFotogradoDTO(
                serviceFotografo.atualizar(idFotografo, fotografoAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<PerfilUsuarioDTO> login(@RequestBody @Valid Fotografo buscarFotografo){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilFotogradoDTO(
                serviceFotografo.login(buscarFotografo)
        ));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilUsuarioDTO> logoff(@RequestBody @Valid Fotografo buscarFotografo){
        return ResponseEntity.status(200).body(usuarioMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }
}
