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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Tema Service", description = "APIs relacionadas a operações de temas")
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Operation(summary = "Cadastrar um novo tema")
    public Tema cadastrar(Tema novoTema){
        Tema tema = temaRepository.save(novoTema);
        return tema;
    }

    @Operation(summary = "Listar todos os temas")
    public List<PerfilTemaDTO> listar(){
        List<Tema> temas = temaRepository.findAll();
        return temas.stream().map(tema -> TemaMapper.toPerfilTemaDTO(tema)).toList();
    }
}
