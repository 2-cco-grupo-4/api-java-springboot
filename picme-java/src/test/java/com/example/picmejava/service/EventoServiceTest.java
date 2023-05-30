package com.example.picmejava.service;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.RetornoEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoCadastradaException;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;

import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.dto.CadastroEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.repository.*;
import com.example.picmejava.service.builder.EventoBuilder;
import com.example.picmejava.service.builder.FotografoBuilder;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {
    @Mock
    private FotografoRepository fotografoRepository ;
    @Mock
    private ClienteRepository clienteRepository ;
    @Mock
    private TemaRepository temaRepository ;
    @Mock
    private EventoRepository eventoRepository ;
    @InjectMocks
    private EventoService eventoService;
    @Test
    @DisplayName("Deve cadastrar um evento corretamente")
    void cadastrar_Evento() {
        Evento novoEvento = new Evento();

        Fotografo fotografoExistente = new Fotografo();
        fotografoExistente.setId(1);
        Mockito.when(fotografoRepository.findById(fotografoExistente.getId())).thenReturn(Optional.of(fotografoExistente));

        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(1);
        Mockito.when(clienteRepository.findById(clienteExistente.getId())).thenReturn(Optional.of(clienteExistente));

        Tema temaExistente = new Tema();
        temaExistente.setId(1);
        Mockito.when(temaRepository.findById(temaExistente.getId())).thenReturn(Optional.of(temaExistente));

        RetornoEventoDTO resultado = eventoService.cadastrar(novoEvento);

        assertNotNull(resultado);
        Mockito.verify(fotografoRepository).findById(fotografoExistente.getId());
        Mockito.verify(clienteRepository).findById(clienteExistente.getId());
        Mockito.verify(temaRepository).findById(temaExistente.getId());
        Mockito.verify(eventoRepository).save(Mockito.any(Evento.class));
    }


    @Test
    @DisplayName("Deve retornar a lista de eventos corretamente")
    void retornar_ListaDeEventos() {
        List<Evento> eventosExistentes = new ArrayList<>();
        eventosExistentes.add(new Evento());
        eventosExistentes.add(new Evento());
        eventosExistentes.add(new Evento());
        Mockito.when(eventoRepository.findAll()).thenReturn(eventosExistentes);

        List<RetornoEventoDTO> resultado = eventoService.listar();

        assertNotNull(resultado);
        assertEquals(eventosExistentes.size(), resultado.size());
        for (int i = 0; i < eventosExistentes.size(); i++) {
            Evento eventoExistente = eventosExistentes.get(i);
            RetornoEventoDTO eventoDTO = resultado.get(i);
            assertEquals(eventoExistente.getId(), eventoDTO.getId());
        }
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar evento com fotógrafo inexistente")
    void lancarExcecao_AoCadastrarEventoComFotografoInexistente() {
        Evento novoEvento = new Evento();
        Fotografo fotografoInexistente = new Fotografo();
        Mockito.when(fotografoRepository.findById(fotografoInexistente.getId())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(novoEvento);
        });

        Mockito.verify(fotografoRepository).findById(fotografoInexistente.getId());
        Mockito.verify(clienteRepository, Mockito.never()).findById(Mockito.anyInt());
        Mockito.verify(temaRepository, Mockito.never()).findById(Mockito.anyInt());
        Mockito.verify(eventoRepository, Mockito.never()).save(Mockito.any(Evento.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar evento com cliente inexistente")
    void lancarExcecao_AoCadastrarEventoComClienteInexistente() {
        Evento novoEvento = new Evento();
        Cliente clienteInexistente = new Cliente();
        Mockito.when(clienteRepository.findById(clienteInexistente.getId())).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(novoEvento);
        });

        Mockito.verify(fotografoRepository).findById(Mockito.anyInt());
        Mockito.verify(clienteRepository).findById(clienteInexistente.getId());
        Mockito.verify(temaRepository, Mockito.never()).findById(Mockito.anyInt());
        Mockito.verify(eventoRepository, Mockito.never()).save(Mockito.any(Evento.class));
    }


    @Test
    @DisplayName("Deve retornar a lista de eventos vazia")
    void retornar_ListaDeEventosVazia() {
        Mockito.when(eventoRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EntidadeNaoCadastradaException.class, () -> {
            eventoService.listar();
        });

        Mockito.verify(eventoRepository).findAll();
    }



}
=======
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private FotografoRepository fotografoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private TemaRepository temaRepository;

    @InjectMocks
    private EventoService eventoService;

    @Test
    @DisplayName("Deve retornar evento quando cadastrar com dados validos")
    void deveRetonarEventoQuandoCadastrarComDadosValidos(){
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(eventoRepository.save(Mockito.any(Evento.class))).thenReturn(evento);

        Evento resultado = eventoRepository.save(evento);

        assertNotNull(resultado);
        assertEquals(evento, resultado);
    }

    @Test
    @DisplayName("Deve retornar excecao quando idTema invalido")
    void deveRetornarExcecaoQuandoIdTemaInvalido(){
        CadastroEventoDTO cadastroEventoDTO = EventoBuilder.criarCadastroEvento();
        Evento evento = EventoBuilder.criarEvento();

        Mockito.when(fotografoRepository.findById(
                Mockito.eq(cadastroEventoDTO.getIdFotografo()))).thenReturn(Optional.of(evento.getFotografo()));
        Mockito.when(clienteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(evento.getCliente()));
        Mockito.when(temaRepository.findById(Mockito.eq(cadastroEventoDTO.getIdTema()))).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            eventoService.cadastrar(cadastroEventoDTO);
        });

        assertEquals("Tema não encontrado", exception.getMessage());
    }

}

