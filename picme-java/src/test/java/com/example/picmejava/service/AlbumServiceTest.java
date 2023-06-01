package com.example.picmejava.service;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.AtualizarAlbumDTO;
import com.example.picmejava.model.dto.CadastroAlbumDTO;
import com.example.picmejava.model.dto.RetornoAlbumDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import com.example.picmejava.service.builder.AlbumBuilder;
import com.example.picmejava.service.builder.FotografoBuilder;
import com.example.picmejava.service.builder.TemaBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private TemaRepository temaRepository;

    @Mock
    private FotografoRepository fotografoRepository;

    @InjectMocks
    private AlbumService albumService;

    @Test
    @DisplayName("Deve retornar Album quando cadastrar com dados validos")
    void deveRetornarAlbumQuandoCadastrarComDadosValidos(){
        Album album = AlbumBuilder.criarAlbum();
        CadastroAlbumDTO cadastroAlbumDTO = AlbumBuilder.criarCadastroAlbum();
        Tema tema = TemaBuilder.criarTema();
        Fotografo fotografo = FotografoBuilder.criarFotografo();

        Mockito.when(albumRepository.save(Mockito.any(Album.class))).thenReturn(album);
        Mockito.when(temaRepository.findById(Mockito.eq(cadastroAlbumDTO.getIdTema()))).thenReturn(Optional.of(tema));
        Mockito.when(fotografoRepository.findById(Mockito.eq(fotografo.getId()))).thenReturn(Optional.of(fotografo));

        RetornoAlbumDTO resultado = albumService.cadastrar(cadastroAlbumDTO);

        assertNotNull(resultado);
        assertEquals(album.getId(),resultado.getId());
        assertEquals(album.getTema().getId(), resultado.getTema().getId());
        assertEquals(album.getTitulo(), resultado.getTitulo());
        assertEquals(album.getImagems(), resultado.getImagems());
    }

    @Test
    @DisplayName("Deve retornar excecao quando cadastrar album e Tema invalido")
    void deveRetornarExcecaoQuandoCadastrarAlbumETemaInvalido(){
        CadastroAlbumDTO cadastroAlbumDTO = AlbumBuilder.criarCadastroAlbum();

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            RetornoAlbumDTO resultado = albumService.cadastrar(cadastroAlbumDTO);
        });

        assertEquals("Tema não existe", exception.getMessage());

    }

    @Test
    @DisplayName("Deve retornar excecao quando cadastrar album e Fotografo invalido")
    void deveRetornarExcecaoQuandoCadastrarAlbumEFotografoInvalido(){
        CadastroAlbumDTO cadastroAlbumDTO = AlbumBuilder.criarCadastroAlbum();
        Tema tema = TemaBuilder.criarTema();
        Fotografo fotografo = FotografoBuilder.criarFotografo();

        Mockito.when(temaRepository.findById(Mockito.eq(cadastroAlbumDTO.getIdTema()))).thenReturn(Optional.of(tema));
        Mockito.when(fotografoRepository.findById(Mockito.eq(fotografo.getId()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            albumService.cadastrar(cadastroAlbumDTO);
        });

        assertEquals("Fotografo não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar album quando album atualizado e valores validos")
    void deveRetornarAlbumQuandoAlbumAtualizadoEValoresValidos(){
        Album album = AlbumBuilder.criarAlbum();
        Tema tema = TemaBuilder.criarTema();
        AtualizarAlbumDTO atualizarAlbum = AlbumBuilder.criarAtualizarAlbum();
        atualizarAlbum.setIdTema(tema.getId());

        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
        Mockito.when(albumRepository.findById(Mockito.eq(album.getId()))).thenReturn(Optional.of(album));
        Mockito.when(albumRepository.save(Mockito.any(Album.class))).thenReturn(album);

        RetornoAlbumDTO resultado = albumService.atualizar(album.getId(), atualizarAlbum);

        assertNotNull(resultado);
        assertEquals(album.getId(), resultado.getId());
        assertEquals(album.getTema(), resultado.getTema());
        assertEquals(album.getTitulo(), resultado.getTitulo());
    }

    @Test
    @DisplayName("Deve retornar excecao quando atualizar album e tema invalido")
    void deveRetornarExcecaoQuandoAtualizarAlbumETemaInvalido(){
        Album album = AlbumBuilder.criarAlbum();
        AtualizarAlbumDTO atualizarAlbum = AlbumBuilder.criarAtualizarAlbum();

        Mockito.when(albumRepository.findById(album.getId())).thenReturn(Optional.of(album));

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            albumService.atualizar(album.getId(), atualizarAlbum);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar excecao quando atualizar album e idAlbum invalido")
    void deveRetornarExecaoQuandoAtualizarAlbumEAlbumNaoExiste(){
        Album album = AlbumBuilder.criarAlbum();
        AtualizarAlbumDTO atualizarAlbum = AlbumBuilder.criarAtualizarAlbum();

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            albumService.atualizar(album.getId(), atualizarAlbum);
        });

        assertEquals("Album não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve deletar album quando dados validos")
    void deveDeletarAlbumQuandoAlbumDeletadoEDadosValidos(){
        Album album = AlbumBuilder.criarAlbum();

        Mockito.when(albumRepository.findById(Mockito.eq(album.getId()))).thenReturn(Optional.of(album));
        Mockito.doNothing().when(albumRepository).deleteById(Mockito.eq(album.getId()));

        Album resultado = albumService.deletar(album.getId());

        assertNotNull(resultado);
        assertEquals(album, resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando deletar album e IdAlbum nao existe")
    void deveRetornarExcecaoQuandoDeletarAlbumEIdAlbumNaoExiste(){
        Album album = AlbumBuilder.criarAlbum();

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            albumService.deletar(album.getId());
        });

        assertEquals("Album não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar tres itens quando tres itens cadastrados")
        void deveRetornarTresItensQuandoTresItensCadastrados(){
        int tamanhoEsperado = 3;
        List<Album> albums = AlbumBuilder.criarListaAlbum();

        Mockito.when(albumRepository.findAll()).thenReturn(albums);

        List<RetornoAlbumDTO> resultado = albumService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(tamanhoEsperado, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItemCadastrado(){
        int tamanhoEsperado = 0;

        Mockito.when(albumRepository.findAll()).thenReturn(new ArrayList<>());

        List<RetornoAlbumDTO> resultado = albumService.listar();

        assertTrue(resultado.isEmpty());
        assertEquals(tamanhoEsperado, resultado.size());

        Mockito.verify(albumRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar album quando buscar pro id e valores validos")
    void deveRetornarAlbumQuandoBuscarPorIdEValoresValidos(){
        Album album = AlbumBuilder.criarAlbum();

        Mockito.when(albumRepository.findById(Mockito.eq(album.getId()))).thenReturn(Optional.of(album));

        Album resultado = albumService.buscarPorId(album.getId());

        assertNotNull(resultado);
        assertEquals(album, resultado);

        Mockito.verify(albumRepository, Mockito.times(1)).findById(Mockito.eq(album.getId()));
    }

    @Test
    @DisplayName("Deve retornar excecao quando buscar por id e id nao encontrado")
    void deveRetornarExcecaoQuandoBuscarPorIdEIdInvalido(){
        int idAlbum = 1;

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            albumService.buscarPorId(idAlbum);
        });

        assertEquals("Album não encontrado", exception.getMessage());
    }
}