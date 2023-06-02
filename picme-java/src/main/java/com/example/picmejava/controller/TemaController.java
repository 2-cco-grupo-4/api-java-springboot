package com.example.picmejava.controller;

import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.service.tema.TemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Tema controller",
        description = "Controller responsável pela entidade tema"
)

@RestController
@RequestMapping("/temas")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @Operation(summary = "Cadastrar um novo tema", description = "Passando os dados necessários, podemos cadastrar um novo tema")
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<PerfilTemaDTO> cadastrar(@RequestBody @Valid CadastroTemaDto novoTema){
        return ResponseEntity.status(200).body(temaService.cadastrar(novoTema));
    }

    @Operation(summary = "Listar temas", description = "Lista todos os temas cadastrados")
    @SecurityRequirement(name = "Bearer")
    @GetMapping
    public ResponseEntity<List<PerfilTemaDTO>> listar(){
        if (temaService.listar().isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(temaService.listar());
    }
}
