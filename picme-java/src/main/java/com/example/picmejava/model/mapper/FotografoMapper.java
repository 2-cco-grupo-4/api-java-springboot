package com.example.picmejava.model.mapper;

import com.example.picmejava.instagram.model.AccessToken;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.service.usuario.dto.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class FotografoMapper {

    public RetornoFotografoDTO toRetornoFotografoDTO(Fotografo dados){
        RetornoFotografoDTO dto = new RetornoFotografoDTO();

        dto.setId(dados.getId());
        if (dados.getAlbums() != null){
            dto.setAlbums(dados.getAlbums().stream().map(AlbumMapper::toAlbumDTO).collect(Collectors.toList()));
        }
        dto.setAutenticado(dados.getAutenticado());
        dto.setNome(dados.getNome());
        dto.setTipoUsuario(dados.getTipoUsuario());
        if (dados.getTemas() != null){
            dto.setTemas(dados.getTemas().stream().map(TemaMapper::toPerfilTemaDTO).toList());
        }

        return dto;
    }

    public PerfilFotografoDTO toPerfilFotogradoDTO(Fotografo fotografo){
        PerfilFotografoDTO dto = new PerfilFotografoDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());
        if (fotografo.getTemas() != null){
            dto.setTemas(fotografo.getTemas().stream().map(TemaMapper::toPerfilTemaDTO).toList());
        }

        return dto;
    }

    public Fotografo toFotografo(CadastroUsuarioDTO dados){
        Fotografo fotografo = new Fotografo();

        fotografo.setNome(dados.getNome());
        fotografo.setCpf(dados.getCpf());
        fotografo.setDataNasc(dados.getDataNasc());
        fotografo.setEmail(dados.getEmail());
        fotografo.setDataCadastro(LocalDate.now());
        fotografo.setSenha(dados.getSenha());
        fotografo.setNumCelular(dados.getNumCelular());
        fotografo.setAutenticado(false);

        return fotografo;
    }

    public Fotografo toFotografoAtualizado(Fotografo fotografo, AtualizarUsuarioDTO dadosAtualizados){
        if (dadosAtualizados.getNome() != null){
            fotografo.setNome(dadosAtualizados.getNome());
        }
        if (dadosAtualizados.getSenha() != null){
            fotografo.setSenha(dadosAtualizados.getSenha());
        }
        if (dadosAtualizados.getNumCelular() != null){
            fotografo.setNumCelular(dadosAtualizados.getNumCelular());
        }
        if (dadosAtualizados.getDataNasc() != null){
            fotografo.setDataNasc(dadosAtualizados.getDataNasc());
        }
        return fotografo;
    }

    public Fotografo toFotografoAccessTokenAtualizado(Fotografo fotografo, UpdateTokenUsuarioDTO updateTokenUsuarioDTO) {
        fotografo.setTokenSolicitacao(updateTokenUsuarioDTO.getTokenSolicitacao());

        return fotografo;
    }
}
