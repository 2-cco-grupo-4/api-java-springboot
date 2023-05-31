package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.CadastroTemaClienteDTO;
import com.example.picmejava.model.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.model.dto.RetornoTemaClienteDTO;
import com.example.picmejava.model.dto.RetornoTemaFotografoDTO;
import com.example.picmejava.model.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.TemaUsuarioMapper;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemaUsuarioService {
    
    @Autowired
    private TemaRepository temaRepository;
    
    @Autowired
    private FotografoRepository fotografoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    private final TemaUsuarioMapper temaUsuarioMapper = new TemaUsuarioMapper();
    
    public RetornoTemaFotografoDTO cadastrarTemaFotografo(CadastroTemaFotografoDTO novoTemaFotografo) {

        List<Tema> temas = new ArrayList<>();
        novoTemaFotografo.getTemas().forEach(tema -> temas.add(temaRepository.findById(tema.getId()).get()));

        Fotografo fotografo = fotografoRepository.findById(novoTemaFotografo.getIdFotografo())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não encontrado"));


        fotografo.setTemas(temas);
        fotografoRepository.save(fotografo);

        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, fotografo);

    }

    public RetornoTemaClienteDTO cadastrarTemaCliente(CadastroTemaClienteDTO novoTemaCliente) {

        List<Tema> temas = new ArrayList<>();
        novoTemaCliente.getTemas().forEach(tema -> temas.add(temaRepository.findById(tema.getId()).get()));

        Cliente cliente = clienteRepository.findById(novoTemaCliente.getIdCliente())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));

        cliente.setTemas(temas);
        clienteRepository.save(cliente);

        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, cliente);
    }
}
