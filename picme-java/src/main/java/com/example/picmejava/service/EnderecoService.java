package com.example.picmejava.service;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.EnderecoMapper;
import com.example.picmejava.repository.EnderecoRepository;
import com.example.picmejava.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Endereco Service", description = "APIs relacionadas a operações de endereços")
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    EnderecoMapper enderecoMapper = new EnderecoMapper();

    @Operation(summary = "Cadastrar um novo endereço")
    public RetornoEnderecoDTO cadastrar(Endereco novoEndereco){
        Evento evento = eventoRepository.findById(novoEndereco.getEvento().getId()).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento não encontrado")
        );
        novoEndereco.setEvento(evento);
        return enderecoMapper.toRetornoEnderecoDTO(enderecoRepository.save(novoEndereco));
    }

    @Operation(summary = "Listar todos os endereços")
    public List<RetornoEnderecoDTO> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        return enderecos.stream().map(endereco -> enderecoMapper.toRetornoEnderecoDTO(endereco)).toList();
    }
}
