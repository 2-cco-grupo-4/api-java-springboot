package com.example.picmejava.service;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco cadastrar(Endereco novoEndereco){
        return enderecoRepository.save(novoEndereco);
    }
}
