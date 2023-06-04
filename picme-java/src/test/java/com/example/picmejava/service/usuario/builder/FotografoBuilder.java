package com.example.picmejava.service.usuario.builder;

import com.example.picmejava.model.Album;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.service.tema.builder.TemaBuilder;
import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
import com.example.picmejava.service.usuario.dto.PerfilFotografoDTO;

import java.time.LocalDate;
import java.util.List;

public class FotografoBuilder {

    public FotografoBuilder() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static Fotografo criarFotografo(){
        Fotografo fotografo = new Fotografo();

        fotografo.setId(1L);
        fotografo.setAutenticado(false);
        fotografo.setSenha("senha");
        fotografo.setCpf("12345678912");
        fotografo.setNome("Fotografo 01");
        fotografo.setEmail("fotografo@email");
        fotografo.setDataCadastro(LocalDate.now());
        fotografo.setDataNasc(LocalDate.ofYearDay(1999, 12));
        fotografo.setNumCelular("(11)123412345");
        fotografo.setTipoUsuario(2);
        fotografo.setAlbums(List.of(
                new Album()
        ));

        return fotografo;
    }

    public static CadastroUsuarioDTO criarCadastroUsuarioDto(){
        CadastroUsuarioDTO cadastroDto = new CadastroUsuarioDTO();

        cadastroDto.setNome("Fotografo 01");
        cadastroDto.setSenha("senha");
        cadastroDto.setCpf("12345678912");
        cadastroDto.setNumCelular("(11)123412345");
        cadastroDto.setDataNasc(LocalDate.ofYearDay(1999,12));
        cadastroDto.setEmail("fotografo@email");

        return cadastroDto;
    }

    public static AtualizarUsuarioDTO criarAtualizazrUsuarioDTO(){
        AtualizarUsuarioDTO atualizarDto = new AtualizarUsuarioDTO();

        atualizarDto.setNome("Fotografo 01");
        atualizarDto.setSenha("senha");
        atualizarDto.setNumCelular("(11)123412345");
        atualizarDto.setDataNasc(LocalDate.ofYearDay(1999, 13));

        return atualizarDto;
    }

    public static LoginUsuarioDTO criarLoginUsuarioDto(){
        LoginUsuarioDTO loginUsuarioDTO = new LoginUsuarioDTO();

        loginUsuarioDTO.setEmail("fotografo@email");
        loginUsuarioDTO.setSenha("senha");

        return loginUsuarioDTO;
    }

    public static PerfilFotografoDTO criarPerfilFotografoDto(){
        PerfilFotografoDTO perfilFotografoDTO = new PerfilFotografoDTO();

        perfilFotografoDTO.setId(1L);
        perfilFotografoDTO.setTemas(List.of(TemaBuilder.criarPerfilTemaDto()));
        perfilFotografoDTO.setAutenticado(false);
        perfilFotografoDTO.setTipoUsuario(2);
        return perfilFotografoDTO;
    }

    public static List<Fotografo> criarListaFotografo(){
        return List.of(
                new Fotografo(),
                new Fotografo(),
                new Fotografo()
        );
    }

}