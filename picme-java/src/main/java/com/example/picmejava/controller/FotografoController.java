package com.example.picmejava.controller;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.dto.PerfilFotografoDTO;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.service.FotografoService;
import com.example.picmejava.service.autenticacao.dto.UsuarioLoginDTO;
import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FotografoService serviceFotografo;
    private FotografoMapper fotografoMapper = new FotografoMapper();


    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilFotografoDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCadastroFotografo){

        String senhaCriptografada = passwordEncoder.encode(novoCadastroFotografo.getSenha());
        novoCadastroFotografo.setSenha(senhaCriptografada);

        return ResponseEntity.status(201).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.cadastrar(novoCadastroFotografo)
        ));
    }

    @GetMapping ResponseEntity<List<PerfilFotografoDTO>> listar(){
        return ResponseEntity.status(200).body(serviceFotografo.listar()
                .stream()
                .filter(Objects::nonNull)
                .map(cliente -> fotografoMapper.toPerfilFotogradoDTO(cliente))
                .toList()
        );
    }

    @PutMapping("/atualizar/{idFotografo}")
    public ResponseEntity<PerfilFotografoDTO> atualizar(
            @PathVariable Integer idFotografo, @RequestBody @Valid AtualizarUsuarioDTO fotografoAtualizado
    ){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.atualizar(idFotografo, fotografoAtualizado)
        ));
    }

    @PatchMapping("/entrar")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        return ResponseEntity.status(200).body(serviceFotografo.autenticar(usuarioLoginDTO));
    }

    @PatchMapping("/sair")
    public ResponseEntity<PerfilFotografoDTO> logoff(@RequestBody LoginUsuarioDTO buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }
}
