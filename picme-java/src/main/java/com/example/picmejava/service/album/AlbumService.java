package com.example.picmejava.service.album;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.AlbumMapper;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import com.example.picmejava.service.album.dto.AtualizarAlbumDTO;
import com.example.picmejava.service.album.dto.CadastroAlbumDTO;
import com.example.picmejava.service.album.dto.RetornoAlbumDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public RetornoAlbumDTO cadastrar(CadastroAlbumDTO novoAlbum){
        Tema tema = temaRepository.findById(novoAlbum.getIdTema())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não existe"));

        Fotografo fotografo = fotografoRepository.findById(novoAlbum.getIdFotografo())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));

        Album album = albumMapper.toAlbum(novoAlbum, fotografo, tema);

        albumRepository.save(album);

        return albumMapper.toRetornoAlbumDTO(album);
    }

    public RetornoAlbumDTO atualizar(Long idAlbum, AtualizarAlbumDTO albumAtualizado){
        Album albumDesatualizado = buscarPorId(idAlbum);

        Optional<Tema> temaOptional = temaRepository.findById(albumAtualizado.getIdTema());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        Album album = albumRepository.save(albumMapper.toAlbum(albumDesatualizado, albumAtualizado, temaOptional.get()));

        return albumMapper.toRetornoAlbumDTO(album);
    }

    public Album deletar(Long idAlbum){
        Album album = buscarPorId(idAlbum);

        album.setTema(null);
        albumRepository.deleteById(album.getId());
        return album;
    }

    public List<RetornoAlbumDTO> listar() {
        List<Album> albums = albumRepository.findAll();
        return albums.stream()
                .map((album) -> albumMapper.toRetornoAlbumDTO(album))
                .toList();
    }

    public Album buscarPorId(Long idAlbum){
        Optional<Album> albumOptional = albumRepository.findById(idAlbum);
        Album album = albumOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));
        return album;
    }

}