//package com.example.picmejava.service.temaUsuario;
//
//import com.example.picmejava.model.Cliente;
//import com.example.picmejava.model.Fotografo;
//import com.example.picmejava.model.Tema;
//import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
//import com.example.picmejava.repository.ClienteRepository;
//import com.example.picmejava.repository.FotografoRepository;
//import com.example.picmejava.repository.TemaRepository;
//import com.example.picmejava.service.usuario.builder.ClienteBuilder;
//import com.example.picmejava.service.usuario.builder.FotografoBuilder;
//import com.example.picmejava.service.tema.builder.TemaBuilder;
//import com.example.picmejava.service.temaUsuario.builder.TemaUsuarioBuilder;
//import com.example.picmejava.service.temaUsuario.dto.CadastroTemaClienteDTO;
//import com.example.picmejava.service.temaUsuario.dto.CadastroTemaFotografoDTO;
//import com.example.picmejava.service.temaUsuario.dto.RetornoTemaClienteDTO;
//import com.example.picmejava.service.temaUsuario.dto.RetornoTemaFotografoDTO;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class TemaUsuarioServiceTest {
//
//    @Mock
//    private TemaRepository temaRepository;
//
//    @Mock
//    private FotografoRepository fotografoRepository;
//
//    @Mock
//    private ClienteRepository clienteRepository;
//
//    @InjectMocks
//    private TemaUsuarioService temaUsuarioService;
//
//    @Test
//    @DisplayName("Deve retornar exception quando cadastrar TemaFotografo e idTema invalido")
//    void deveRetornarExceptionQuandoCadastrarTemaFotografoEIdTemaInvalido(){
//        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            temaUsuarioService.cadastrarTemaFotografo(cadastro);
//        });
//
//        assertEquals("Tema não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar exception quando cadastrar TemaFotografo e idFotografo invalido")
//    void deveRetornarExceptionQuandoCadastrarTemaFotografoEIdFotografoInvalido(){
//        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
//        Tema tema = TemaBuilder.criarTema();
//
//        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            temaUsuarioService.cadastrarTemaFotografo(cadastro);
//        });
//
//        assertEquals("Fotografo não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar TemaFotografo quando cadastrar TemaFotografo e dados validos")
//    void deveRetornarTemaFotografoQuandoCadastrarTemaFotografoEDadosValidos(){
//        CadastroTemaFotografoDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaFotografo();
//        Tema tema = TemaBuilder.criarTema();
//        Fotografo fotografo = FotografoBuilder.criarFotografo();
//
//        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
//        Mockito.when(fotografoRepository.findById(Mockito.eq(fotografo.getId()))).thenReturn(Optional.of(fotografo));
//
//        RetornoTemaFotografoDTO resultado = temaUsuarioService.cadastrarTemaFotografo(cadastro);
//
//        assertNotNull(resultado);
//        assertEquals(cadastro.getIdFotografo(), resultado.getFotografo().getId());
//        assertEquals(cadastro.getTemas().get(0).getId(), resultado.getTemas().get(0).getId());
//    }
//
//    @Test
//    @DisplayName("Deve retornar exception quando cadastrar TemaCliente e idTema invalido")
//    void deveRetornarExceptionQuandoCadastrarTemaClienteEIdTemaInvalido(){
//        CadastroTemaClienteDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaCliente();
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            temaUsuarioService.cadastrarTemaCliente(cadastro);
//        });
//
//        assertEquals("Tema não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar exception quando cadastrar TemaCliente e idFotografo invalido")
//    void deveRetornarExceptionQuandoCadastrarTemaClienteEIdClienteInvalido(){
//        CadastroTemaClienteDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaCliente();
//        Tema tema = TemaBuilder.criarTema();
//
//        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
//
//        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
//            temaUsuarioService.cadastrarTemaCliente(cadastro);
//        });
//
//        assertEquals("Cliente não encontrado", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Deve retornar TemaCliente quando cadastrar TemaFotografo e dados validos")
//    void deveRetornarTemaClienteQuandoCadastrarTemaClienteEDadosValidos(){
//        CadastroTemaClienteDTO cadastro = TemaUsuarioBuilder.criarCadastroTemaCliente();
//        Tema tema = TemaBuilder.criarTema();
//        Cliente cliente = ClienteBuilder.criarCliente();
//
//        Mockito.when(temaRepository.findById(Mockito.eq(tema.getId()))).thenReturn(Optional.of(tema));
//        Mockito.when(clienteRepository.findById(Mockito.eq(cliente.getId()))).thenReturn(Optional.of(cliente));
//
//        RetornoTemaClienteDTO resultado = temaUsuarioService.cadastrarTemaCliente(cadastro);
//
//        assertNotNull(resultado);
//        assertEquals(cadastro.getIdCliente(), resultado.getCliente().getId());
//        assertEquals(cadastro.getTemas().get(0).getId(), resultado.getTemas().get(0).getId());
//    }
//
//}