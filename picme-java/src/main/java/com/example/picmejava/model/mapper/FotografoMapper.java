package com.example.picmejava.model.mapper;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.PerfilFotografoDTO;
//import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;

public class FotografoMapper {

//    public static UsuarioTokenDTO of(Fotografo fotografo, String token){
//        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();
//
//        usuarioTokenDTO.setUserId(fotografo.getId());
//        usuarioTokenDTO.setNome(fotografo.getNome());
//        usuarioTokenDTO.setEmail(fotografo.getEmail());
//        usuarioTokenDTO.setToken(token);
//
//        return usuarioTokenDTO;
//    }

    public PerfilFotografoDTO toPerfilFotogradoDTO(Fotografo fotografo){
        PerfilFotografoDTO dto = new PerfilFotografoDTO();

        dto.setId(fotografo.getId());
        dto.setNome(fotografo.getNome());
        dto.setAutenticado(fotografo.getAutenticado());
        dto.setTipoUsuario(fotografo.getTipoUsuario());
        dto.setTokenSolicitacao(fotografo.getTokenSolicitacao());

        return dto;
    }

    public Fotografo toFotografo(CadastroUsuarioDTO dados){
        Fotografo fotografo = new Fotografo();

        fotografo.setNome(dados.getNome());
        fotografo.setCpf(dados.getCpf());
        fotografo.setDataNasc(dados.getDataNasc());
        fotografo.setEmail(dados.getEmail());
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
}
