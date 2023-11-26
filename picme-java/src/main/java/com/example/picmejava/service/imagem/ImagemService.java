package com.example.picmejava.service.imagem;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Imagem;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.s3.S3;
import com.example.picmejava.service.imagem.dto.CadastroImagemDTO;
import com.example.picmejava.service.imagem.dto.FeedImagemDTO;
import com.example.picmejava.service.imagem.dto.RetornoImagemDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.ImagemMapper;
import com.example.picmejava.repository.AlbumRepository;
import com.example.picmejava.repository.ImagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Tag(name = "Imagem Service", description = "APIs relacionadas a operações de imagens")
public class ImagemService {

    private final AlbumRepository albumRepository;
    private final ImagemRepository imagemRepository;
    private final ImagemMapper imagemMapper = new ImagemMapper();
    private final Logger logger = LoggerFactory.getLogger(ImagemService.class);
    @Autowired
    private FotografoRepository fotografoRepository;

    @Autowired
    private S3<Fotografo> s3;

    @Autowired
    public ImagemService(AlbumRepository albumRepository, ImagemRepository imagemRepository) {
        this.albumRepository = albumRepository;
        this.imagemRepository = imagemRepository;
    }

    @Operation(summary = "Cadastrar uma nova imagem em um álbum")
    public RetornoImagemDTO cadastrar(Long idAlbum, CadastroImagemDTO novaImagem) {
        Album album = getAlbum(idAlbum);
        novaImagem.setIdAlbum(idAlbum);
        Imagem imagem = imagemMapper.toImagem(novaImagem, album);
        return imagemMapper.toRetornoImagemDTO(imagemRepository.save(imagem));
    }

    @Operation(summary = "Buscar uma imagem por ID")
    public Imagem buscarPorId(Long idImagem) {
        return imagemRepository.findById(idImagem)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Imagem não encontrada"));
    }

    @Operation(summary = "Deletar uma imagem por ID")
    public void deletar(Long idImagem) {
        buscarPorId(idImagem);
        imagemRepository.deleteById(idImagem);
    }

    @Operation(summary = "Listar todas as imagens")
    public List<RetornoImagemDTO> listar() {
        List<Imagem> imagens = imagemRepository.findAll();
        return imagens.stream()
                .map(imagem -> imagemMapper.toRetornoImagemDTO(imagem))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Listar paths das imagens e IDs dos álbuns")
    public List<FeedImagemDTO> listarFeed() {
        List<Imagem> imagens = imagemRepository.findAll();
        return imagens.stream()
                .map(imagem -> new FeedImagemDTO(imagem.getId(), imagem.getMediaUrl(), imagem.getIdAlbum().getId(), imagem.getIdAlbum().getFotografo().getNome(), imagem.getOrigemImagem()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Forçar teste de imagens de um album para o feed")
    public List<FeedImagemDTO> listarFeed(Long id) {
        List<Imagem> imagens = imagemRepository.findAll();
        return imagens.stream()
                .filter(imagem -> imagem.getIdAlbum().getId() == id)
                .map(imagem -> new FeedImagemDTO(imagem.getId(), imagem.getMediaUrl(), imagem.getIdAlbum().getId(), imagem.getIdAlbum().getFotografo().getNome(), imagem.getOrigemImagem()))
                .collect(Collectors.toList());
    }

    public Album getAlbum(Long idAlbum) {
        return albumRepository.findById(idAlbum)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Album não encontrado"));
    }

    public void putImage(
            Long idFotografo,
            MultipartFile image
    ) {

        Fotografo fotografo = fotografoRepository.findById(idFotografo).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Usuário: " + idFotografo + "Não encontrado")
        );


        String imagemId = s3.putImage(image);

        fotografo.setImageUrl(imagemId);
        fotografoRepository.save(fotografo);
    }

    public byte[] getImage(Long id) {

        Fotografo fotografo = fotografoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fotogrado com o ID: " + id + " não encontrado!")
        );

        if (fotografo.getImageUrl() == null) {
            throw new EntidadeNaoEncontradaException("Usuário com id: " + id + " não tem imagem cadastrada");
        }

        return s3.getImage(fotografo.getImageUrl());
    }

    public void putImageAlbum(
            Long idAlbum,
            MultipartFile image
    ) {

        Album album = albumRepository.findById(idAlbum).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Album: " + idAlbum + "Não encontrado")
        );

        String imagemId = s3.putImageAlbum(image, Long.toString(idAlbum));

        CadastroImagemDTO novaImagem = new CadastroImagemDTO(imagemId, "s3", LocalDateTime.now(), idAlbum);

        Imagem imagem = imagemMapper.toImagemFromS3(novaImagem, album);

        imagemRepository.save(imagem);
    }

    public byte[] getImageAlbum(Long id) {

        Imagem imagem = imagemRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Imagem com o ID: " + id + " não encontrada!")
        );

        if (imagem.getMediaUrl() == null) {
            throw new EntidadeNaoEncontradaException("Imagem com id: " + id + " não tem imagem cadastrada");
        }

        return s3.getImage(imagem.getMediaUrl());
    }

    public void putListImagemAlbum(
            Long idAlbum,
            List<MultipartFile> images
    ) {

        Album album = albumRepository.findById(idAlbum).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Album: " + idAlbum + "Não encontrado")
        );

        for (MultipartFile image : images) {
            String imagemId = s3.putImageAlbum(image, Long.toString(idAlbum));

            CadastroImagemDTO novaImagem = new CadastroImagemDTO(imagemId, "s3", LocalDateTime.now(), idAlbum);

            Imagem imagem = imagemMapper.toImagemFromS3(novaImagem, album);

            imagemRepository.save(imagem);
        }
    }

}
