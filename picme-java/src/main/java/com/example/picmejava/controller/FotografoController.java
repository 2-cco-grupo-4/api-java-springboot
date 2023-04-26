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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@Tag(
        name = "Fotografo Controller",
        description = "Controller responsável pela entidade Fotografo"
)

@RestController
@RequestMapping("/fotografos")
public class FotografoController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FotografoService serviceFotografo;
    private FotografoMapper fotografoMapper = new FotografoMapper();

    @Operation(summary = "Cadastrar um novo fotógrafo", description = "Passando os dados necessários, podemos cadastrar um novo fotógrafo")
    @PostMapping("/cadastrar")
    public ResponseEntity<PerfilFotografoDTO> cadastrar(@RequestBody @Valid CadastroUsuarioDTO novoCadastroFotografo){

        String senhaCriptografada = passwordEncoder.encode(novoCadastroFotografo.getSenha());
        novoCadastroFotografo.setSenha(senhaCriptografada);

        return ResponseEntity.status(201).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.cadastrar(novoCadastroFotografo)
        ));
    }

    @Operation(summary = "Listar fotógrafos", description = "Lista todos os fotógrafos cadastrados")
    @SecurityRequirement(name = "Bearer")
    @GetMapping ResponseEntity<List<PerfilFotografoDTO>> listar(){
        return ResponseEntity.status(200).body(serviceFotografo.listar()
                .stream()
                .filter(Objects::nonNull)
                .map(cliente -> fotografoMapper.toPerfilFotogradoDTO(cliente))
                .toList()
        );
    }

    @Operation(summary = "Atualizar dados fotógrafo", description = "Passando o ID do fotógrafo e seus novos dados, podemos atualizar suas informações")
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/atualizar/{idFotografo}")
    public ResponseEntity<PerfilFotografoDTO> atualizar(
            @PathVariable Integer idFotografo, @RequestBody @Valid AtualizarUsuarioDTO fotografoAtualizado
    ){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.atualizar(idFotografo, fotografoAtualizado)
        ));
    }

    @Operation(summary = "Login fotógrafo", description = "Passando as credenciais válidas de um fotógrafo, é realizado o login na API")
    @PatchMapping("/entrar")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        return ResponseEntity.status(200).body(serviceFotografo.autenticar(usuarioLoginDTO));
    }

    @Operation(summary = "Logoff fotógrafo", description = "EndPoint para logoff do fotógrafo, é necessário passar as suas credenciais novamente")
    @SecurityRequirement(name = "Bearer")
    @PatchMapping("/sair")
    public ResponseEntity<PerfilFotografoDTO> logoff(@RequestBody LoginUsuarioDTO buscarFotografo){
        return ResponseEntity.status(200).body(fotografoMapper.toPerfilFotogradoDTO(
                serviceFotografo.logoff(buscarFotografo)
        ));
    }
}
