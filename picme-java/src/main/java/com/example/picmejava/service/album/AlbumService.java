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
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Album Service", description = "APIs relacionadas a operações de álbuns")
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final TemaRepository temaRepository;
    private final FotografoRepository fotografoRepository;
    private final AlbumMapper albumMapper = new AlbumMapper();
    private EntityManager entityManager;

    @Autowired
    public AlbumService(
            AlbumRepository albumRepository,
            TemaRepository temaRepository,
            FotografoRepository fotografoRepository,
            EntityManager entityManager){
        this.albumRepository = albumRepository;
        this.temaRepository = temaRepository;
        this.fotografoRepository = fotografoRepository;
        this.entityManager = entityManager;
    }

    @Operation(summary = "Cadastrar um novo álbum")
    public RetornoAlbumDTO cadastrar(CadastroAlbumDTO novoAlbum) {
        Tema tema = getTema(novoAlbum.getIdTema());
        Fotografo fotografo = getFotografo(novoAlbum.getIdFotografo());
        Album album = albumMapper.toAlbum(novoAlbum, fotografo, tema);
        albumRepository.save(album);
        return albumMapper.toRetornoAlbumDTO(album);
    }

    @Operation(summary = "Atualizar um álbum existente")
    public RetornoAlbumDTO atualizar(Long idAlbum, AtualizarAlbumDTO albumAtualizado) {
        Album albumDesatualizado = buscarPorId(idAlbum);
        Tema tema = getTema(albumAtualizado.getIdTema());
        Album album = albumRepository.save(albumMapper.toAlbum(albumDesatualizado, albumAtualizado, tema));
        return albumMapper.toRetornoAlbumDTO(album);
    }

    @Operation(summary = "Deletar um álbum")
    public Album deletar(Long idAlbum) {
        Album album = buscarPorId(idAlbum);
        album.setTema(null);
        albumRepository.deleteById(album.getId());
        return album;
    }

    @Operation(summary = "Listar todos os álbuns")
    public List<RetornoAlbumDTO> listar(Long idFotografo) {
            List<Album> albuns = albumRepository.findAllByFotografo(getFotografo(idFotografo));
            return albuns.stream()
                    .map(albumMapper::toRetornoAlbumDTO)
                    .toList();


    }

    @Operation(summary = "Listar todos os álbuns de um fotógrafo")
    public List<RetornoAlbumDTO> listarAlbunsFotografo(Long idFotografo) {
        List<Album> albums = albumRepository.findAllByFotografo(getFotografo(idFotografo));
        return albums.stream()
                .map(albumMapper::toRetornoAlbumDTO)
                .toList();
    }

    @Operation(summary = "Buscar um álbum por ID")
    public Album buscarPorId(Long idAlbum) {
        return albumRepository.findById(idAlbum)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));
    }

    @Operation(summary = "Buscar um álbum por ID e receber RetornoAlbumDTO")
    public RetornoAlbumDTO buscarPorIdRetornoAlbumDTO(Long idAlbum) {
        return albumMapper.toRetornoAlbumDTO(buscarPorId(idAlbum));
    }

    private Tema getTema(Long idTema) {
        return temaRepository.findById(idTema)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não existe"));
    }

    private Fotografo getFotografo(Long idFotografo) {
        return fotografoRepository.findById(idFotografo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));
    }

}
