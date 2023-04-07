package com.example.picmejava.service;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.repository.FotografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FotografoService {

    @Autowired
    private FotografoRepository fotografoRepository;

    public Fotografo cadastrar(Fotografo novoFotografo){
        novoFotografo.setAutenticado(false);
        return fotografoRepository.save(novoFotografo);
    }

    public Fotografo alterarSenha(Integer idFotografo, String senhaAtualizada)throws Exception{
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new Exception("Fotografo não existe"));
        fotografo.setSenha(senhaAtualizada);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo login(Fotografo buscarFotografo) throws Exception{
        Optional<Fotografo> fotografoOptional = fotografoRepository.findByEmail(buscarFotografo.getEmail());
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new Exception("Fotografo não existe"));
        fotografo.setAutenticado(true);
        return fotografoRepository.save(fotografoRepository.save(fotografo));
    }

    public Fotografo logoff(Fotografo buscarFotografo) throws Exception{
        Optional<Fotografo> fotografoOptional = fotografoRepository.findByEmail(buscarFotografo.getEmail());
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new Exception("Fotografo não existe"));
        fotografo.setAutenticado(false);
        return fotografoRepository.save(fotografoRepository.save(fotografo));
    }
}
