package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.dto.RetornoEventoDTO;
import com.example.picmejava.model.exception.EntidadeNaoCadastradaException;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.EventoMapper;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.EventoRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FotografoRepository fotografoRepository;

    @Autowired
    private TemaRepository temaRepository;

    EventoMapper eventoMapper = new EventoMapper();

    public RetornoEventoDTO cadastrar(Evento novoEvento) {
        Fotografo fotografo = verificarFotografo(novoEvento.getFotografo());
        Cliente cliente = verificarCliente(novoEvento.getCliente());
        Tema tema = verificarTema(novoEvento.getTema());

        Evento evento = eventoMapper.toEvento(fotografo, cliente, tema, novoEvento);
        eventoRepository.save(evento);

        return eventoMapper.toRetornoEventoDTO(evento);
    }

    public List<RetornoEventoDTO> listar() {
        List<Evento> eventos = eventoRepository.findAll();

        if (eventos.isEmpty()){
            throw new EntidadeNaoCadastradaException("Nenhum evento cadastrado");
        }

        return eventos.stream()
                .map(evento -> eventoMapper.toRetornoEventoDTO(evento))
                .toList();
    }

    public Cliente verificarCliente(Cliente cliente){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        optionalCliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não existe"));

        return optionalCliente.get();
    }

    public Fotografo verificarFotografo(Fotografo fotografo){
            Optional<Fotografo> optionalFotografo = fotografoRepository.findById(fotografo.getId());
            optionalFotografo.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não existe"));

        return optionalFotografo.get();
    }

    public Tema verificarTema(Tema tema){
        Optional<Tema> optionalTema = temaRepository.findById(tema.getId());
        optionalTema.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        return optionalTema.get();
    }
}
