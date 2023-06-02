package com.example.picmejava.service.usuario;

import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.service.usuario.builder.FotografoBuilder;
import com.example.picmejava.service.usuario.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FotografoServiceTest {

    @Mock
    private FotografoRepository fotografoRepository;

    @InjectMocks
    private FotografoService fotografoService;

    @Test
    @DisplayName("Deve cadastrar o fotografo quando dados validos")
    void deveCadastrarOClienteQuandoDadosValidos(){
        Fotografo fotografo = FotografoBuilder.criarFotografo();
        CadastroUsuarioDTO cadastroDto = FotografoBuilder.criarCadastroUsuarioDto();

        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);

        PerfilFotografoDTO resultado = fotografoService.cadastrar(cadastroDto);

        assertNotNull(resultado);
        assertEquals(fotografo.getId(), resultado.getId());
        assertEquals(fotografo.getAutenticado(), resultado.getAutenticado());
        assertEquals(fotografo.getNome(), resultado.getNome());
        assertEquals(fotografo.getAutenticado(), resultado.getAutenticado());
    }

    @Test
    @DisplayName("Deve retornar tres itens quando tres itens cadastrados")
    void deveRetornarTresItensQuandoTresItensCadastrados(){
        int tamanhoEsperado = 3;
        List<Fotografo> fotografos = FotografoBuilder.criarListaFotografo();

        Mockito.when(fotografoRepository.findAll()).thenReturn(fotografos);

        List<RetornoFotografoDTO> resultado = fotografoService.listar();

        assertFalse(resultado.isEmpty());
        assertEquals(tamanhoEsperado, fotografos.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando nenhum item cadastrado")
    void deveRetornarListaVaziaQuandoNenhumItenCadastrado(){
        int tamanhoEsperado = 0;

        Mockito.when(fotografoRepository.findAll()).thenReturn(new ArrayList<>());

        List<RetornoFotografoDTO> resultado = fotografoService.listar();

        assertTrue(resultado.isEmpty());
        assertEquals(tamanhoEsperado, resultado.size());
    }

    @Test
    @DisplayName("Deve atualizar fotografo quando dados validos")
    void deveAtualizarFotografoQuandoDadosValidos(){
        Fotografo fotografo = FotografoBuilder.criarFotografo();
        AtualizarUsuarioDTO atualizarDto = FotografoBuilder.criarAtualizazrUsuarioDTO();

        Mockito.when(fotografoRepository.findById(fotografo.getId())).thenReturn(Optional.of(fotografo));
        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);

        Fotografo resultado = fotografoService.atualizar(fotografo.getId(), atualizarDto);

        assertNotNull(resultado);
        assertEquals(fotografo, resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando atualizar fotografo e Id não encontrado")
    void deveRetornarExcecaoQuandoAtualizarFotografoEIdNaoEncontrado(){
        Long id = 1L;
        AtualizarUsuarioDTO atualizarDto = FotografoBuilder.criarAtualizazrUsuarioDTO();

        Mockito.lenient().when(fotografoRepository.existsById(id)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            fotografoService.atualizar(id, atualizarDto);
        });

        assertEquals("Fotografo não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar fotografo quando validar fotografo")
    void deveRetornarFotografoQuandoValidarFotografo(){
        String email = "fotografo@email.com";
        String senha = "senha1234";

        Fotografo fotografo = new Fotografo();
        fotografo.setEmail(email);
        fotografo.setSenha(senha);

        Mockito.when(fotografoRepository.findByEmailAndSenha(email, senha)).thenReturn(Optional.of(fotografo));

        Fotografo resultado = fotografoService.validarFotografo(email, senha);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando validar fotografo nao encontrar com email e senha")
    void deveRetornarExcecaoQuandoValidarFotografoNaoEncontrarComEmailESenha(){
        String email = "fotografo@email.com";
        String senha = "senha1234";

        Fotografo fotografo = new Fotografo();
        fotografo.setEmail(email);
        fotografo.setSenha(senha);

        Mockito.lenient().when(fotografoRepository.existsById(Mockito.eq(fotografo.getId()))).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            fotografoService.validarFotografo(email, senha);
        });

        assertEquals("Fotografo não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar fotografo autenticado quando login fotografo")
    void deveRetornarFotografoAutenticadoQuandoLoginFotografo(){
        boolean valorEsperado = true;

        Fotografo fotografo = FotografoBuilder.criarFotografo();
        LoginUsuarioDTO loguinDto = FotografoBuilder.criarLoginUsuarioDto();

        Mockito.when(fotografoRepository.findByEmailAndSenha(
                Mockito.eq(fotografo.getEmail()), Mockito.eq(fotografo.getSenha())
        )).thenReturn(Optional.of(fotografo));
        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);

        Fotografo resultado = fotografoService.login(loguinDto);

        assertNotNull(resultado);
        assertEquals(valorEsperado, resultado.getAutenticado());
    }

    @Test
    @DisplayName("Deve retornar excecao quando autenticar fotografo")
    void deveRetornarExcecaoQuandoLoginFotografo(){
        LoginUsuarioDTO loguinDto = FotografoBuilder.criarLoginUsuarioDto();
        Fotografo fotografo = FotografoBuilder.criarFotografo();

        Mockito.lenient().when(fotografoRepository.existsById(Mockito.eq(fotografo.getId()))).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            fotografoService.logoff(loguinDto);
        });

        assertEquals("Fotografo não existe", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar fotografo desautenticado quando logoff fotografo cam dados validos")
    void deveRetornarFotografoDesautenticadoQuandoLogOffFotografoComDadosValidos(){
        Fotografo fotografo = FotografoBuilder.criarFotografo();
        LoginUsuarioDTO loguinDto = FotografoBuilder.criarLoginUsuarioDto();

        Mockito.when(fotografoRepository.findByEmailAndSenha(
                Mockito.eq(fotografo.getEmail()), Mockito.eq(fotografo.getSenha())
        )).thenReturn(Optional.of(fotografo));
        Mockito.when(fotografoRepository.save(Mockito.any(Fotografo.class))).thenReturn(fotografo);

        Fotografo resultado = fotografoService.logoff(loguinDto);

        assertNotNull(resultado);
        assertEquals(false, resultado.getAutenticado());
    }

    @Test
    @DisplayName("Deve retornar excecao quando validar fotografo nao encontrar com email e senha")
    void deveRetornarExcecaoQuandoLogOffNaoEncontrarComEmailESenha(){
        String email = "fotografo@email.com";
        String senha = "senha1234";

        Fotografo fotografo = new Fotografo();
        fotografo.setEmail(email);
        fotografo.setSenha(senha);

        Mockito.lenient().when(fotografoRepository.existsById(Mockito.eq(fotografo.getId()))).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            fotografoService.validarFotografo(email, senha);
        });

        assertEquals("Fotografo não existe", exception.getMessage());
    }

}