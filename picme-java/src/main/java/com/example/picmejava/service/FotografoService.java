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

    public Fotografo cadastrar(Fotografo novoUsuario){
        fotografos.add(novoUsuario);
        return novoUsuario;
    }

    public Fotografo buscarPorId(Integer idFotografo) throws Exception{
        for (Fotografo fotografo : fotografos){
            if (idFotografo.equals(fotografo.getId())){
                return fotografo;
            }
        }
        throw new Exception(String.format("ERROOOOOOOO idFotografo: " + idFotografo));
    }

    public Fotografo adicionarAlbumAoFotografo(Integer id, Album album) throws Exception{
        Fotografo fotografo = buscarPorId(id);
        if (!fotografo.equals(null)){
            fotografo.getAlbuns().add(album);
            return fotografo;
        }else {
            return null;
        }
    }

    public Album adiconarImagemAoAlbum(Integer idFotografo, Integer idAlbum, Imagem novaImagem) throws Exception{
        Fotografo fotografo = buscarPorId(idFotografo);
        if (!fotografo.equals(null)){
            List <Album> listaAlbum = fotografo.getAlbuns();
            for(Album album : listaAlbum){
                if (idAlbum.equals(album.getId())){
                    album.getImagems().add(novaImagem);
                    return album;
                }
            }
        }
        return null;
    }

    public Fotografo alterarSenha(Integer id, String novaSenha){
        for (Fotografo usuario : fotografos){
            if (usuario.getId() == id){
                usuario.setSenha(novaSenha);
                return usuario;
            }
        }
        return null;
    }

    public Fotografo login(Fotografo buscarUsuario) throws Exception{
        for (Fotografo usuario : fotografos){
            if (usuario.verificarUsuario(usuario, buscarUsuario)){
                if (usuario.getAutenticado().equals(true)){
                    throw new Exception(String.format("Usuário %s não já está ativo", usuario.getNome()));
                }else {
                    usuario.setAutenticado(true);
                    return usuario;
                }
            }
        }
        throw new Exception(String.format("Usuário não encontrado"));
    }

    public String logoff(Fotografo buscarUsuario) throws Exception{
        for (Fotografo usuario : fotografos){
            if (usuario.verificarUsuario(usuario, buscarUsuario)){
                if (usuario.getAutenticado().equals(false)){
                    throw new Exception(String.format("Usuário %s não está ativo", usuario.getNome()));
                }else {
                    usuario.setAutenticado(false);
                    return String.format("Usuario %s fez logoff com sucesso!", usuario.getNome());
                }
            }
        }

        throw new Exception(String.format("Usuário não encontrado!"));
    }
}
