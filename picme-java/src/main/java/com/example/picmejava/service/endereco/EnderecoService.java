package com.example.picmejava.service.endereco;

import com.example.picmejava.model.Endereco;
import com.example.picmejava.model.Sessao;
import com.example.picmejava.service.endereco.dto.CadastroEnderecoDTO;
import com.example.picmejava.service.endereco.dto.EditaEnderecoDTO;
import com.example.picmejava.service.endereco.dto.RetornoEnderecoDTO;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.mapper.EnderecoMapper;
import com.example.picmejava.repository.EnderecoRepository;
import com.example.picmejava.repository.SessaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Service
@Tag(name = "Endereco Service", description = "APIs relacionadas a operações de endereços")
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final SessaoRepository sessaoRepository;
    private final EnderecoMapper enderecoMapper = new EnderecoMapper();

    @Autowired
    public EnderecoService(
            EnderecoRepository enderecoRepository,
            SessaoRepository sessaoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.sessaoRepository = sessaoRepository;
    }

    @Operation(summary = "Cadastrar um novo endereço")
    public RetornoEnderecoDTO cadastrar(CadastroEnderecoDTO novoEndereco){
        Sessao sessao = sessaoRepository.findById(novoEndereco.getIdEvento()).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Sessao não encontrado")
        );
        Endereco endereco = EnderecoMapper.toEndereco(novoEndereco, sessao);
        return enderecoMapper.toRetornoEnderecoDTO(enderecoRepository.save(endereco));
    }

    public RetornoEnderecoDTO editarEndereco(Long idEndereco, EditaEnderecoDTO enderecoAtualizado) {
        try {
            Endereco endereco = enderecoRepository.findById(idEndereco)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

            BeanUtils.copyProperties(enderecoAtualizado, endereco, "id", "sessao");

            enderecoRepository.save(endereco);

            return enderecoMapper.toRetornoEnderecoDTO(endereco);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Entidade não encontrada no service: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erro ao processar a requisição no service: " + e.getMessage());
            throw new RuntimeException("Erro ao processar a requisição");
        }
    }

    @Operation(summary = "Listar todos os endereços")
    public List<RetornoEnderecoDTO> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(endereco -> enderecoMapper.toRetornoEnderecoDTO(endereco)).toList();
    }

    @Operation(summary = "Visualizar um endereço específico")
    public RetornoEnderecoDTO visualizarEndereco(Long idEndereco) {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado"));

        return enderecoMapper.toRetornoEnderecoDTO(endereco);
    }


}
