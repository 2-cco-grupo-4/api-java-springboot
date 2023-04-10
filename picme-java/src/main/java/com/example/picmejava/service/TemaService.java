package com.example.picmejava.service;

import com.example.picmejava.model.Tema;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private TemaRepository repository;

    public Tema cadastrar(Tema novoTema){
        Tema tema = repository.save(novoTema);
        return tema;
    }

    public List<Tema> listar() throws Exception{
        List<Tema> temas = repository.findAll();
        if (temas.isEmpty()){
            throw new Exception("Nenhum tema encontrado!");
        }
        return temas;
    }
}
