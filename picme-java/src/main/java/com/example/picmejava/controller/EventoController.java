package com.example.picmejava.controller;

import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Pagamento;
import com.example.picmejava.model.Sessao;
import com.example.picmejava.service.evento.dto.*;
import com.example.picmejava.service.evento.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Sessao Controller",
        description = "Controller responsável pela entidade Sessao"
)
@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private SessaoService sessaoService;

    @Operation(summary = "Cadastrar um novo evento", description = "Passando o JSON do evento, podemos cadastrar um novo evento")
    @PostMapping
    public ResponseEntity<RetornoEventoDTO> cadastrar(@RequestBody @Valid CadastroSessaoDTO novoEvento){
        return ResponseEntity.status(201).body(sessaoService.cadastrar(novoEvento));
    }

    @PostMapping("/contrato")
    @Operation(summary = "Cadastrar um novo contrato")
    public ResponseEntity<?> cadastrarContrato(@Valid @RequestBody CadastroContratoDTO contratoDTO) {
        try {
            RetornoEventoDTO contratoCadastrado = sessaoService.cadastrarContrato(contratoDTO);
            return new ResponseEntity<>(contratoCadastrado, HttpStatus.CREATED);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada: " + e.getMessage());
            return new ResponseEntity<>("Entidade não encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição: " + e.getMessage());
            return new ResponseEntity<>("Erro ao processar a requisição", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cadastrarPagamento")
    @Operation(summary = "Cadastrar pagamento para uma sessão")
    public ResponseEntity<Pagamento> cadastrarPagamento(@RequestBody CadastrarPagamentoDTO cadastrarPagamentoDTO) {
        try {
            Pagamento pagamento = sessaoService.cadastrarPagamento(cadastrarPagamentoDTO);
            return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Listar eventos", description = "Obter a lista de todos os eventos")
    @GetMapping
    public ResponseEntity<List<RetornoEventoDTO>> listar(){
        if (sessaoService.listar().isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(sessaoService.listar());
    }

    @Operation(summary = "Cadastrar Sessão Externa", description = "Passando o JSON da sessão, podemos cadastrar uma nova sessão externa")
    @PostMapping("/cadastrarExterno")
    public ResponseEntity<Sessao> cadastrarExterno(@RequestBody CadastroSessaoExternoDTO novoEvento){
        return ResponseEntity.status(201).body(sessaoService.cadastrarExterno(novoEvento));
    }

    @Operation(summary = "Listar sessões do fotógrafo", description = "Obter a lista de todas as sessões do fotógrafo")
    @GetMapping("/sessoes")
    public ResponseEntity<List<RetornoEventoDTO>> listarSessoesFotografo(@RequestParam Long idFotografo){

        return ResponseEntity.status(200).body(sessaoService.listarPorFotografo(idFotografo));
    }
}
