package com.example.picmejava.service.tema;

import com.example.picmejava.model.Tema;
import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.model.mapper.TemaMapper;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Tema Service", description = "APIs relacionadas a operações de temas")
public class TemaService {
    private final TemaRepository temaRepository;

    @Autowired
    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    @Operation(summary = "Cadastrar um novo tema")
    public PerfilTemaDTO cadastrar(CadastroTemaDto novoTema){
        Tema tema = TemaMapper.toTema(novoTema);

        return TemaMapper.toPerfilTemaDTO(temaRepository.save(tema));
    }

    @Operation(summary = "Listar todos os temas")
    public List<PerfilTemaDTO> listar(){
        List<PerfilTemaDTO> perfilTemaDTOS = temaRepository.findAll().stream().map(TemaMapper::toPerfilTemaDTO).toList();
        return perfilTemaDTOS;

    }


}
