package com.example.picmejava.service;

import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.*;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.repository.FotografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Fotógrafo Service", description = "APIs relacionadas a operações de fotógrafos")
public class FotografoService {

    @Autowired
    private FotografoRepository fotografoRepository;

    private FotografoMapper fotografoMapper = new FotografoMapper();

    @Operation(summary = "Cadastrar um novo fotógrafo")
    public PerfilFotografoDTO cadastrar(CadastroUsuarioDTO novoFotografo){
        Fotografo fotografo = fotografoRepository.save(fotografoMapper.toFotografo(novoFotografo));
        return fotografoMapper.toPerfilFotogradoDTO(fotografo);
    }

    @Operation(summary = "Listar todos os fotógrafos")
    public List<RetornoFotografoDTO> listar() {
        List<Fotografo> fotografos = fotografoRepository.findAll();
        return fotografos.stream()
                .map(fotografo -> fotografoMapper.toRetornoFotografoDTO(fotografo))
                .toList();
    }

    @Operation(summary = "Atualizar um fotógrafo")
    public Fotografo atualizar(Integer idFotografo, AtualizarUsuarioDTO fotografoAtualizado){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Fotografo não existe")
        );
        return fotografoRepository.save(fotografoMapper.toFotografoAtualizado(fotografo, fotografoAtualizado));
    }

    @Operation(summary = "Login de um fotógrafo")
    public Fotografo login(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(true);
        return fotografoRepository.save(fotografo);
    }

    @Operation(summary = "Logoff de um fotógrafo")
    public Fotografo logoff(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(false);
        return fotografoRepository.save(fotografo);
    }

    @Operation(summary = "Validar um fotógrafo")
    public Fotografo validarFotografo(String email, String senha){
        Fotografo fotografo = fotografoRepository.findByEmailAndSenha(email, senha).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fotografo não existe")
        );

        return fotografo;
    }
}
