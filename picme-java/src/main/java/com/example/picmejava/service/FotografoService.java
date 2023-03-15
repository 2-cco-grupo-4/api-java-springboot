package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Imagem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FotografoService {

    private List<Fotografo> fotografos;
    public FotografoService() {
        this.fotografos = new ArrayList<>();
    }

    public Fotografo cadastrar(Fotografo novoFotografo){
        fotografos.add(novoFotografo);
        return novoFotografo;
    }

    public Fotografo buscarFotografoPorId(Integer idFotografo) throws Exception{
        for (Fotografo fotografo : fotografos){
            if (idFotografo.equals(fotografo.getId())){
                return fotografo;
            }
        }
        throw new Exception(String.format("ERRO não encontrou, idFotografo: " + idFotografo));
    }

    public Fotografo alterarSenha(Integer idFotografo, String novaSenha)throws Exception{
        Fotografo fotografo = buscarFotografoPorId(idFotografo);
        if (!fotografo.equals(null)){
            fotografo.setSenha(novaSenha);
            return fotografo;
        }
        throw new Exception("Fotografo não encontrado!");
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
        throw new Exception(String.format("Fotografo não encontrado"));
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

    public Fotografo adicionarAlbumAoFotografo(Integer idFotografo, Album album) throws Exception{
        Fotografo fotografo = buscarFotografoPorId(idFotografo);
        if (!fotografo.equals(null)){
            fotografo.getAlbuns().add(album);
            return fotografo;
        }else {
            throw new Exception(String.format("Erro adicionarAlbumAoFotografo"));
        }
    }

    public Album adicionarImagemAoAlbum(Integer idFotografo, Integer idAlbum, Imagem novaImagem) throws Exception{
        Fotografo fotografo = buscarFotografoPorId(idFotografo);
        if (!fotografo.equals(null)){
            for(Album album : fotografo.getAlbuns()){
                if (idAlbum.equals(album.getId())){
                    album.getImagems().add(novaImagem);
                    return album;
                }
            }
        }
        throw new Exception(String.format("Erro adiconarImagemAoAlbum"));
    }
}
