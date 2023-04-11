package com.example.picmejava.service;

import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
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

    public Fotografo atualizar(Integer idFotografo, AtualizarUsuarioDTO fotografoAtualizado){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new UsuarioNaoEncontradoException(
                "Fotografo não existe")
        );
        fotografo.atualizarInformacoes(fotografoAtualizado);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo login(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(true);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo logoff(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(false);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo validarFotografo(String email, String senha){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findByEmailAndSenha(email, senha);
        fotografoOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Fotografo não existe"));
        return fotografoRepository.findByEmail(email).get();

    }
}
