package com.example.picmejava.service;


import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.PerfilFotografoDTO;
import com.example.picmejava.model.mapper.FotografoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FotografoService {

    private List<Fotografo> fotografos;
    private FotografoMapper fotografoMapper = new FotografoMapper();
    public FotografoService() {
        this.fotografos = new ArrayList<>();
    }

    public PerfilFotografoDTO cadastrar(Fotografo novoFotografo){
        fotografos.add(novoFotografo);
        return fotografoMapper.toPerfilFotografoDTO(novoFotografo);
    }

    public PerfilFotografoDTO alterarSenha(Integer idFotografo, String novaSenha){
        Fotografo fotografo = buscarFotografoPorId(idFotografo);
            fotografo.setSenha(novaSenha);
            return fotografoMapper.toPerfilFotografoDTO(fotografo);
    }

    public Fotografo buscarFotografoPorId(Integer idFotografo){
        for (Fotografo fotografo : fotografos){
            if (idFotografo.equals(fotografo.getId())){
                return fotografo;
            }
        }
        throw new UsuarioNaoEncontradoException(String.format("Fotografo com id %d não encontrado", idFotografo));
    }

    public Fotografo login(Fotografo buscarFotografo) throws Exception{
        for (Fotografo fotografo : fotografos){
            if (fotografo.verificarUsuario(fotografo, buscarFotografo)){
                if (fotografo.getAutenticado().equals(true)){
                    throw new Exception(String.format("Fotografo %s não já está ativo", fotografo.getNome()));
                }else {
                    fotografo.setAutenticado(true);
                    return fotografo;
                }
            }
        }
        throw new UsuarioNaoEncontradoException(String.format("Fotografo com id %d não encontrado", buscarFotografo.getId()));
    }

    public String logoff(Fotografo buscarFotografo) throws Exception{
        for (Fotografo fotografo : fotografos){
            if (fotografo.verificarUsuario(fotografo, buscarFotografo)){
                if (fotografo.getAutenticado().equals(false)){
                    throw new Exception(String.format("Fotografo %s não está ativo", fotografo.getNome()));
                }else {
                    fotografo.setAutenticado(false);
                    return String.format("Fotografo %s fez logoff com sucesso!", fotografo.getNome());
                }
            }
        }
        throw new Exception(String.format("Fotografo não encontrado!"));
    }
}
