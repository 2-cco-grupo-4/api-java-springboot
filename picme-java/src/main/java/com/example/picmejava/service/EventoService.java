package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Evento;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
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

//    public Evento cadastrar(Evento novoEvento) {
//        if (verificarTema(novoEvento.getTema()) && verificarCliente(novoEvento.getCliente())){
//            for (Fotografo fotografo : novoEvento.getFotografos()){
//                verificarFotografo(fotografo);
//            }
//
//        }
//
//        return eventoRepository.save(novoEvento);
//    }
//
//    public Boolean verificarCliente(Cliente cliente){
//        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
//        optionalCliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não existe"));
//
//        return true;
//    }
//
//    public Boolean verificarFotografo(Fotografo fotografo){
//            Optional<Fotografo> optionalFotografo = fotografoRepository.findById(fotografo.getId());
//            optionalFotografo.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não existe"));
//
//        return true;
//    }
//
//    public Boolean verificarTema(Tema tema){
//        Optional<Tema> optionalTema = temaRepository.findById(tema.getId());
//        optionalTema.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));
//
//        return true;
//    }
}
