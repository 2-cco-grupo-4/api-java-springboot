package com.example.picmejava.service.tema;

import com.example.picmejava.model.Tema;
import com.example.picmejava.service.tema.dto.CadastroTemaDto;
import com.example.picmejava.service.tema.dto.PerfilTemaDTO;
import com.example.picmejava.model.mapper.TemaMapper;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {
    @Autowired
    private TemaRepository temaRepository;

    public PerfilTemaDTO cadastrar(CadastroTemaDto novoTema){
        Tema tema = TemaMapper.toTema(novoTema);

        return TemaMapper.toPerfilTemaDTO(temaRepository.save(tema));
    }

    public List<PerfilTemaDTO> listar(){
        List<PerfilTemaDTO> perfilTemaDTOS = temaRepository.findAll().stream().map(TemaMapper::toPerfilTemaDTO).toList();
        return perfilTemaDTOS;

    }


}
