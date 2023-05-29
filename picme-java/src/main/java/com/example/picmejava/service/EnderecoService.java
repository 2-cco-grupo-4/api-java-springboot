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

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EventoRepository eventoRepository;

    EnderecoMapper enderecoMapper = new EnderecoMapper();

    public RetornoEnderecoDTO cadastrar(Endereco novoEndereco){
        Evento evento = eventoRepository.findById(novoEndereco.getEvento().getId()).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento não encontrado")
        );
        novoEndereco.setEvento(evento);
        return enderecoMapper.toRetornoEnderecoDTO(enderecoRepository.save(novoEndereco));
    }

    public List<RetornoEnderecoDTO> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        return enderecos.stream().map(endereco -> enderecoMapper.toRetornoEnderecoDTO(endereco)).toList();
    }
}
