package com.example.picmejava.service.usuario;

import com.example.picmejava.infra.exception.ConflitoNoCadastroException;
import com.example.picmejava.instagram.controller.InstagramController;
import com.example.picmejava.instagram.model.AccessToken;
import com.example.picmejava.instagram.model.LongAccessToken;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.infra.exception.EntidadeNaoEncontradaException;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.service.usuario.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Tag(name = "Fotógrafo Service", description = "APIs relacionadas a operações de fotógrafos")
public class FotografoService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FotografoRepository fotografoRepository;

    @Autowired
    private InstagramController instagramController;

    private FotografoMapper fotografoMapper = new FotografoMapper();

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

    @Operation(summary = "Validar cadastro")
    public boolean validarCadastro(String cpf, String email) {
        Optional<Fotografo> usuarioPorCpf = fotografoRepository.findByCpf(cpf);
        Optional<Fotografo> usuarioPorEmail = fotografoRepository.findByEmail(email);

        if (usuarioPorCpf.isPresent() || usuarioPorEmail.isPresent()) {
            return false;
        }

        return true;
    }

    @Transactional
    @Operation(summary = "Atualizar access token")
    public Fotografo atualizarAccessToken(Long idFotografo, String codigo) {
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Fotografo não existe")
        );

        AtualizarUsuarioDTO updateTokenUsuarioDTO = new AtualizarUsuarioDTO();



        if(fotografo.getTokenSolicitacao() == null) {
            ResponseEntity<AccessToken> accessToken;
            accessToken = instagramController.acessTokenUsuario(codigo);
            if(accessToken.getStatusCode() == HttpStatusCode.valueOf(200)) {
                ResponseEntity<LongAccessToken> longAccessToken = instagramController.getLongAccessToken(accessToken.getBody().getAccess_token());

                if(longAccessToken.getStatusCode() == HttpStatusCode.valueOf(200)) {
                    updateTokenUsuarioDTO.setTokenSolicitacao(longAccessToken.getBody().getAccess_token());
                }

            }
        } else {
            ResponseEntity<LongAccessToken> accessToken;
            accessToken = instagramController.refreshLongAccessToken(fotografo.getTokenSolicitacao());
            if(accessToken.getStatusCode() == HttpStatusCode.valueOf(200)) {
                updateTokenUsuarioDTO.setTokenSolicitacao(accessToken.getBody().getAccess_token());
            }
        }

        Fotografo teste = fotografoRepository.save(fotografoMapper.toFotografoAtualizado(fotografo, updateTokenUsuarioDTO));

        System.out.printf("Teste:\n\nID Usuário: %d\ntoken: %s\n\n", teste.getId(), teste.getTokenSolicitacao());

        return teste;
    }

    @Operation(summary = "Buscar fotógrafo por ID")
    public Fotografo findById(Long idFotografo) {
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        return fotografoOptional.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Fotografo não existe")
        );
    }

}
