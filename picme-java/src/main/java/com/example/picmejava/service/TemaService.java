package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.PerfilTemaDTO;
import com.example.picmejava.model.exception.EntidadeNaoCadastradaException;
import com.example.picmejava.model.mapper.TemaMapper;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private TemaRepository temaRepository;

    public Tema cadastrar(Tema novoTema){
        Tema tema = temaRepository.save(novoTema);
        return tema;
    }

    public List<PerfilTemaDTO> listar(){
        List<Tema> temas = temaRepository.findAll();

        if (temas.isEmpty()){
            throw new EntidadeNaoCadastradaException("Nenhum tema cadastrado");
        }

        return temas.stream().map(tema -> TemaMapper.toPerfilTemaDTO(tema)).toList();

    }


}
