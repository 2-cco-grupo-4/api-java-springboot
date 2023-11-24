package com.example.picmejava.controller;

import com.example.picmejava.service.pesquisa.PesquisaService;
import com.example.picmejava.service.pesquisa.dto.vwPesquisaTermo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Pesquisa",
        description = "Pesquisa de usuários, temas e tags"
)

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    private PesquisaService pesquisaService;

    @Operation(summary = "Pesquisar termo", description = "Pesquisa termo por nome")
    @GetMapping
    public ResponseEntity<List<vwPesquisaTermo>> pesquisarTermo(@RequestParam String termo){
        if (pesquisaService.pesquisarTermo(termo).isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(pesquisaService.pesquisarTermo(termo));
    }

}
