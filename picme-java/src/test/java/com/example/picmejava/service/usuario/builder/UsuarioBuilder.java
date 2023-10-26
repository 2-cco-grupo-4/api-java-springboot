//package com.example.picmejava.service.usuario.builder;
//
//import com.example.picmejava.service.usuario.dto.AtualizarUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.CadastroUsuarioDTO;
//import com.example.picmejava.service.usuario.dto.LoginUsuarioDTO;
//
//import java.time.LocalDate;
//
//public class UsuarioBuilder {
//
//    public UsuarioBuilder() {
//        throw new IllegalArgumentException("Classe utilitaria");
//    }
//
//    public static CadastroUsuarioDTO criarCadastroUsuarioDto(){
//        CadastroUsuarioDTO cadastroDto = new CadastroUsuarioDTO();
//
//        cadastroDto.setNome("Fotografo 01");
//        cadastroDto.setSenha("senha");
//        cadastroDto.setCpf("12345678912");
//        cadastroDto.setNumCelular("(11)123412345");
//        cadastroDto.setDataNasc(LocalDate.ofYearDay(1999,12));
//        cadastroDto.setEmail("fotografo@email");
//
//        return cadastroDto;
//    }
//
//    public static AtualizarUsuarioDTO criarAtualizazrUsuarioDTO(){
//        AtualizarUsuarioDTO atualizarDto = new AtualizarUsuarioDTO();
//
//        atualizarDto.setNome("Fotografo 01");
//        atualizarDto.setSenha("senha");
//        atualizarDto.setNumCelular("(11)123412345");
//        atualizarDto.setDataNasc(LocalDate.ofYearDay(1999, 13));
//
//        return atualizarDto;
//    }
//
//    public static LoginUsuarioDTO criarLoginUsuarioDto(){
//        LoginUsuarioDTO loginUsuarioDTO = new LoginUsuarioDTO();
//
//        loginUsuarioDTO.setEmail("fotografo@email");
//        loginUsuarioDTO.setSenha("senha");
//
//        return loginUsuarioDTO;
//    }
//}
