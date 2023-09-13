package com.example.picmejava.service.usuario;

import com.example.picmejava.infra.exception.ConflitoNoCadastroException;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.service.usuario.dto.*;
import com.example.picmejava.service.usuario.mapper.PerfilClienteDtoMapper;
import com.example.picmejava.service.usuario.mapper.PerfilFotografoDTOMapper;
import com.example.picmejava.service.utils.TabelaHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Fotógrafo Service", description = "APIs relacionadas a operações de fotógrafos")
public class FotografoService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FotografoRepository fotografoRepository;
    private FotografoMapper fotografoMapper = new FotografoMapper();

    private TabelaHash<String> tabelaHash = new TabelaHash();
    @Operation(summary = "Cadastrar um novo fotógrafo")
    public PerfilFotografoDTO cadastrar(CadastroUsuarioDTO novoFotografo){
        String senhaCriptografada = passwordEncoder.encode(novoFotografo.getSenha());
        novoFotografo.setSenha(senhaCriptografada);

        boolean isValido = validarCadastro(novoFotografo.getCpf(), novoFotografo.getEmail());
        if (!isValido){
            throw new ConflitoNoCadastroException("Cadastro ja existe");
        }

        Fotografo fotografo = fotografoRepository.save(fotografoMapper.toFotografo(novoFotografo));
        return fotografoMapper.toPerfilFotogradoDTO(fotografo);
    }

    @Operation(summary = "Listar todos os fotógrafos")
    public List<RetornoFotografoDTO> listar() {
        List<Fotografo> fotografos = fotografoRepository.findAll();
        return fotografos.stream()
                .map(fotografo -> fotografoMapper.toRetornoFotografoDTO(fotografo))
                .toList();
    }

    @Operation(summary = "Atualizar um fotógrafo")
    public Fotografo atualizar(Long idFotografo, AtualizarUsuarioDTO fotografoAtualizado){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Fotografo não existe")
        );
        return fotografoRepository.save(fotografoMapper.toFotografoAtualizado(fotografo, fotografoAtualizado));
    }

    @Operation(summary = "Login de um fotógrafo")
    public Fotografo login(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(true);
        return fotografoRepository.save(fotografo);
    }

    @Operation(summary = "Logoff de um fotógrafo")
    public Fotografo logoff(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(false);
        return fotografoRepository.save(fotografo);
    }

    @Operation(summary = "Validar um fotógrafo")
    public Fotografo validarFotografo(String email, String senha){
        Fotografo fotografo = fotografoRepository.findByEmailAndSenha(email, senha).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Fotografo não existe")
        );

        return fotografo;
    }

    @Operation(summary = "Buscar um cliente pelo nome")
    public List<PerfilFotografoDTO> buscarFotografo(String nome) {

        adicionarFotografoNaTabela();

        List<PerfilFotografoDTO> fotografos = tabelaHash.searchByString(nome);

        return fotografos;
    }

    private void adicionarFotografoNaTabela() {
        List<Fotografo> todosFotografos = fotografoRepository.findAll();


        for (Fotografo fotografo : todosFotografos) {
            PerfilFotografoDTO perfilFotografoDto = PerfilFotografoDTOMapper.mapFotografo(fotografo);
            tabelaHash.add(perfilFotografoDto);
        }
    }

    @Operation(summary = "Validar cadastro")
    public boolean validarCadastro(String cpf, String email) {
        Optional<Fotografo> usuarioPorCpf = fotografoRepository.findByCpf(cpf);
        Optional<Fotografo> usuarioPorEmail = fotografoRepository.findByEmail(email);

        if (usuarioPorCpf.isPresent() || usuarioPorEmail.isPresent()) {
            return false;
        }

        return true;
    }

    
}
