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

import java.util.List;
import java.util.Optional;

@Service
public class TemaUsuarioService {
    
    @Autowired
    private TemaRepository temaRepository;
    
    @Autowired
    private FotografoRepository fotografoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    private TemaUsuarioMapper temaUsuarioMapper = new TemaUsuarioMapper();
    
    public RetornoTemaFotografoDTO cadastrarTemaFotografo(CadastroTemaFotografoDTO novoTemaFotografo) {

        List<Tema> temas = novoTemaFotografo.getTemas().stream()
                .map(tema -> validarTema(tema))
                .toList();

        Fotografo fotografo = validarFotografo(novoTemaFotografo.getFotografo());
        for (Tema tema : temas) {
            tema.adicionar(fotografo);
            fotografo.adicionar(tema);
        }

        fotografoRepository.save(fotografo);
        temas.stream().map(tema -> temaRepository.save(tema));

        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, fotografo);

    }

    public RetornoTemaClienteDTO cadastrarTemaCliente(CadastroTemaClienteDTO novoTemaCliente) {


        List<Tema> temas = novoTemaCliente.getTemas().stream()
                .map(tema -> validarTema(tema))
                .toList();

        Cliente cliente = validarCliente(novoTemaCliente.getCliente());
        for (Tema tema : temas) {
            tema.adicionar(cliente);
            cliente.adicionar(tema);
        }

        clienteRepository.save(cliente);
        temas.stream().map(tema -> temaRepository.save(tema));

        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, cliente);

    }

    public Fotografo validarFotografo(Fotografo fotografo){
        Optional<Fotografo> optionalFotografo = fotografoRepository.findById(fotografo.getId());
        optionalFotografo.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não existe"));

        return optionalFotografo.get();
    }

    public Cliente validarCliente(Cliente cliente){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        optionalCliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não existe"));

        return optionalCliente.get();
    }

    public Tema validarTema(Tema tema){
        Optional<Tema> temaOptional = temaRepository.findById(tema.getId());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        return temaOptional.get();
    }
}
