package com.example.picmejava.service;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.dto.CadastroEnderecoDTO;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.EnderecoMapper;
import com.example.picmejava.model.mapper.EventoMapper;
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

    public RetornoEnderecoDTO cadastrar(CadastroEnderecoDTO novoEndereco){
        Evento evento = eventoRepository.findById(novoEndereco.getIdEvento()).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Evento não encontrado")
        );
        Endereco endereco = EnderecoMapper.toEndereco(novoEndereco, evento);
        return enderecoMapper.toRetornoEnderecoDTO(enderecoRepository.save(endereco));
    }

    public List<RetornoEnderecoDTO> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        return enderecos.stream().map(endereco -> enderecoMapper.toRetornoEnderecoDTO(endereco)).toList();
    }
}
