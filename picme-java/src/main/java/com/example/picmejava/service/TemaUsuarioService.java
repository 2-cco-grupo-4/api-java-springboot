package com.example.picmejava.service;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Tema Usuário Service", description = "APIs relacionadas a operações de temas para usuários")
public class TemaUsuarioService {

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private FotografoRepository fotografoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private TemaUsuarioMapper temaUsuarioMapper = new TemaUsuarioMapper();

    @Operation(summary = "Cadastrar temas para um fotógrafo")
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

    @Operation(summary = "Cadastrar temas para um cliente")
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
    @Operation(summary = "Validar fotógrafo existente")
    private Fotografo validarFotografo(Fotografo fotografo) {
        Optional<Fotografo> optionalFotografo = fotografoRepository.findById(fotografo.getId());
        optionalFotografo.orElseThrow(() -> new EntidadeNaoEncontradaException("Fotógrafo não existe"));

        return optionalFotografo.get();
    }

    @Operation(summary = "Validar cliente existente")
    private Cliente validarCliente(Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        optionalCliente.orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não existe"));

        return optionalCliente.get();
    }

    @Operation(summary = "Validar tema existente")
    private Tema validarTema(Tema tema) {
        Optional<Tema> temaOptional = temaRepository.findById(tema.getId());
        temaOptional.orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        return temaOptional.get();
    }
}
