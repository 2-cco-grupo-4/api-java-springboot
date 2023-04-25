package com.example.picmejava.controller;

import com.example.picmejava.model.Tema;
import com.example.picmejava.service.TemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @PostMapping
    public ResponseEntity<Tema> cadastrar(@RequestBody Tema novoTema){
        return ResponseEntity.status(200).body(temaService.cadastrar(novoTema));
    }

    @Operation(summary = "Listar temas", description = "Lista todos os temas cadastrados")
    @GetMapping
    public ResponseEntity<List<Tema>> listar() throws Exception{
        return ResponseEntity.status(200).body(temaService.listar());
    }
}
