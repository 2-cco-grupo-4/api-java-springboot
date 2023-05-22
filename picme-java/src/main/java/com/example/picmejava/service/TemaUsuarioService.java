package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.CadastroTemaUsuarioDTO;
import com.example.picmejava.model.dto.RetornoTemaUsuarioDTO;
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
    private TemaService temaService;
    
    @Autowired
    private FotografoService fotografoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private TemaRepository temaRepository;
    
    @Autowired
    private FotografoRepository fotografoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    private TemaUsuarioMapper temaUsuarioMapper = new TemaUsuarioMapper();
    
    public RetornoTemaUsuarioDTO cadastrar(CadastroTemaUsuarioDTO novoTemaUsuario){

        List<Tema> temas = novoTemaUsuario.getTemas().stream()
                .map(tema -> validarTema(tema))
                .toList();

        if (novoTemaUsuario.getUsuario().getTipoUsuario().equals("fotografo")){
            Fotografo fotografo = validarFotografo(novoTemaUsuario.getUsuario());
            fotografo.setTema(temas);
            temas.stream().map(tema -> tema.getUsuarios().add(fotografo));

            fotografoRepository.save(fotografo);
            temas.stream().map(tema -> temaRepository.save(tema));

            return temaUsuarioMapper.toTemaUsuarioDTO(temas, fotografo);

        }

        if (novoTemaUsuario.getUsuario().getTipoUsuario().equals("cliente")){
            Cliente cliente = validarCliente(novoTemaUsuario.getUsuario());
            cliente.setTema(temas);

            temas.stream().map(tema -> tema.getUsuarios().add(cliente));

            clienteRepository.save(cliente);
            temas.stream().map(tema -> temaRepository.save(tema));

            return temaUsuarioMapper.toTemaUsuarioDTO(temas, cliente);
        }

        throw new EntidadeNaoEncontradaException("Nenhum tipo de usuário encontrado");

    }

    public Fotografo validarFotografo(Usuario usuario){
        Optional<Fotografo> optionalFotografo = fotografoRepository.findById(usuario.getId());
        optionalFotografo.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotografo não existe"));

        return optionalFotografo.get();
    }

    public Cliente validarCliente(Usuario usuario){
        Optional<Cliente> optionalCliente = clienteRepository.findById(usuario.getId());
        optionalCliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não existe"));

        return optionalCliente.get();
    }
    
    public Tema validarTema(Tema tema){
        Optional<Tema> temaOptional = temaRepository.findById(tema.getId());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        return temaOptional.get();
    }
}
