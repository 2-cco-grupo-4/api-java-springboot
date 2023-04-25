package com.example.picmejava.service;

import com.example.picmejava.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.repository.FotografoRepository;
import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FotografoService {

    @Autowired
    private FotografoRepository fotografoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    private FotografoMapper fotografoMapper = new FotografoMapper();

    public Fotografo cadastrar(CadastroUsuarioDTO novoFotografoDTO){
        Fotografo novoFotografo = fotografoMapper.toFotografo(novoFotografoDTO);

        String senhaCriptografada = passwordEncoder.encode(novoFotografo.getSenha());
        novoFotografo.setSenha(senhaCriptografada);
        return fotografoRepository.save(novoFotografo);
    }

    public Lista<Fotografo> listar() {
        Lista<Fotografo> fotografos = (Lista<Fotografo>) fotografoRepository.findAll();
        return fotografos;
    }

    public Fotografo atualizar(Integer idFotografo, AtualizarUsuarioDTO fotografoAtualizado){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findById(idFotografo);
        Fotografo fotografo = fotografoOptional.orElseThrow(() -> new UsuarioNaoEncontradoException(
                "Fotografo não existe")
        );
        return fotografoRepository.save(fotografoMapper.toFotografoAtualizado(fotografo, fotografoAtualizado));
    }

    public Fotografo login(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(true);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo logoff(LoginUsuarioDTO buscarFotografo){
        Fotografo fotografo = validarFotografo(buscarFotografo.getEmail(), buscarFotografo.getSenha());
        fotografo.setAutenticado(false);
        return fotografoRepository.save(fotografo);
    }

    public Fotografo validarFotografo(String email, String senha){
        Optional<Fotografo> fotografoOptional = fotografoRepository.findByEmailAndSenha(email, senha);
        fotografoOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Fotografo não existe"));
        return fotografoRepository.findByEmail(email).get();

    }

    public UsuarioTokenDTO autenticar(LoginUsuarioDTO usuarioLoginDTO) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Fotografo fotografoAutenticado =
                fotografoRepository.findByEmail(usuarioLoginDTO.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FotografoMapper.of(fotografoAutenticado, token);
    }
}
