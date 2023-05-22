package com.example.picmejava.service;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.dto.RetornoEnderecoDTO;
import com.example.picmejava.model.mapper.EnderecoMapper;
import com.example.picmejava.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    EnderecoMapper enderecoMapper = new EnderecoMapper();

    public RetornoEnderecoDTO cadastrar(Endereco novoEndereco){
        return enderecoMapper.toRetornoEnderecoDTO(enderecoRepository.save(novoEndereco));
    }
}
