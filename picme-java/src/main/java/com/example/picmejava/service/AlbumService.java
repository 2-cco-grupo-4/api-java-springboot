package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.AtualizarAlbumDTO;
import com.example.picmejava.model.dto.RetornoAlbumDTO;
import com.example.picmejava.model.exception.EntidadeNaoCadastradaException;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.AlbumMapper;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.picmejava.lista.Lista;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private FotografoRepository fotografoRepository;

    private AlbumMapper albumMapper = new AlbumMapper();

    public RetornoAlbumDTO cadastrar(Album novoAlbum){
        Optional<Tema> temaOptional = temaRepository.findById(novoAlbum.getTema().getId());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não existe"));

        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(novoAlbum.getFotografo().getId());
        fotografoOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));

        fotografoOptional.get().adicionarAlbum(novoAlbum);

        return albumMapper.toRetornoAlbumDTO(albumRepository.save(novoAlbum));
    }

    public RetornoAlbumDTO atualizar(Integer idAlbum, AtualizarAlbumDTO albumAtualizado){
        Album albumDesatualizado = buscarPorId(idAlbum);

        Optional<Tema> temaOptional = temaRepository.findById(albumAtualizado.getIdTema());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        Album album = albumRepository.save(albumMapper.toAlbum(albumDesatualizado, albumAtualizado, temaOptional.get()));

        return albumMapper.toRetornoAlbumDTO(album);
    }

    public Album deletar(Integer idAlbum){
        Album album = buscarPorId(idAlbum);

        albumRepository.deleteById(idAlbum);
        return album;
    }

    public List<RetornoAlbumDTO> listar() {
        List<Album> albums = albumRepository.findAll();

        if (albums.isEmpty()){
            throw new EntidadeNaoCadastradaException("Nenhum Album cadastrado");
        }

        return albums.stream()
                .map((album) -> albumMapper.toRetornoAlbumDTO(album))
                .toList();
    }

    public Album buscarPorId(Integer idAlbum){
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        Album album = albumOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));
        return album;
    }

}
