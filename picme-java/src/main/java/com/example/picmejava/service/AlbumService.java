package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Tema;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.picmejava.lista.Lista;

import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private TemaRepository temaRepository;

    public Album cadastrar(Integer idFotografo, Album novoAlbum) throws Exception{
        Optional<Tema> temaOptional = temaRepository.findById(novoAlbum.getTipo().getId());
        temaOptional.orElseThrow(() -> new Exception("Tema não encontrado"));
        novoAlbum.setTipo(temaOptional.get());
        novoAlbum.setIdFotografo(idFotografo);
        return albumRepository.save(novoAlbum);
    }

    public Album atualizar(Integer idAlbum, Album albumAtualizado) throws Exception {
        Album album = buscarPorId(idAlbum);
        albumAtualizado.setId(album.getId());
        return albumRepository.save(albumAtualizado);
    }

    public Album buscarPorId(Integer idAlbum) throws Exception {
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        Album album = albumOptional.orElseThrow(() -> new Exception("Album não encontrado"));
        return album;
    }

    public Album deletar(Integer idAlbum) throws Exception{
        Album album = buscarPorId(idAlbum);
        albumRepository.deleteById(idAlbum);
        return album;
    }

    public Lista<Album> listar(Integer idFotografo) {
        Lista<Album> albumsDoFotografo = new Lista();
        for(Album i :  albumRepository.findAllByIdFotografo(idFotografo)){
            albumsDoFotografo.add(i);
        }
        return albumsDoFotografo;
    }

}
