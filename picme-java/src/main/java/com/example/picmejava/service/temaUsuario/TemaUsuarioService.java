package com.example.picmejava.service.temaUsuario;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Tema;
import com.example.picmejava.service.temaUsuario.dto.CadastroTemaClienteDTO;
import com.example.picmejava.service.temaUsuario.dto.CadastroTemaFotografoDTO;
import com.example.picmejava.service.temaUsuario.dto.RetornoTemaClienteDTO;
import com.example.picmejava.service.temaUsuario.dto.RetornoTemaFotografoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.TemaUsuarioMapper;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Tema Usuário Service", description = "APIs relacionadas a operações de temas para usuários")
public class TemaUsuarioService {

    private final TemaRepository temaRepository;
    private final FotografoRepository fotografoRepository;
    private final ClienteRepository clienteRepository;
    private final TemaUsuarioMapper temaUsuarioMapper = new TemaUsuarioMapper();

    @Autowired
    public TemaUsuarioService(
            TemaRepository temaRepository,
            FotografoRepository fotografoRepository,
            ClienteRepository clienteRepository) {
        this.temaRepository = temaRepository;
        this.fotografoRepository = fotografoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Operation(summary = "Cadastrar temas para um fotógrafo")
    public RetornoTemaFotografoDTO cadastrarTemaFotografo(CadastroTemaFotografoDTO novoTemaFotografo) {
        Fotografo fotografo = getFotografoById(novoTemaFotografo.getIdFotografo());
        List<Tema> temas = getTemasByIds(novoTemaFotografo.getTemas());
        fotografo.setTemas(temas);
        fotografoRepository.save(fotografo);
        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, fotografo);
    }

    @Operation(summary = "Cadastrar temas para um cliente")
    public RetornoTemaClienteDTO cadastrarTemaCliente(CadastroTemaClienteDTO novoTemaCliente) {
        Cliente cliente = getClienteById(novoTemaCliente.getIdCliente());
        List<Tema> temas = getTemasByIds(novoTemaCliente.getTemas());
        cliente.setTemas(temas);
        clienteRepository.save(cliente);
        return temaUsuarioMapper.toRetornoTemaUsuarioDTO(temas, cliente);
    }

    private Fotografo getFotografoById(Long fotografoId) {
        return fotografoRepository.findById(fotografoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Fotógrafo não encontrado"));
    }

    private Cliente getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }

    private List<Tema> getTemasByIds(List<Long> temaIds) {
        List<Tema> temas = new ArrayList<>();
        for (Long temaId : temaIds) {
            Tema tema = temaRepository.findById(temaId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));
            temas.add(tema);
        }
        return temas;
    }
}
