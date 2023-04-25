package com.example.picmejava.service;

import com.example.picmejava.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.picmejava.exceptionhandler.UsuarioNaoEncontradoException;
import com.example.picmejava.lista.Lista;
import com.example.picmejava.model.Cliente;
import com.example.picmejava.model.Fotografo;
import com.example.picmejava.model.Usuario;
import com.example.picmejava.model.dto.AtualizarUsuarioDTO;
import com.example.picmejava.model.dto.CadastroUsuarioDTO;
import com.example.picmejava.model.dto.LoginUsuarioDTO;
import com.example.picmejava.model.mapper.ClienteMapper;
import com.example.picmejava.model.mapper.FotografoMapper;
import com.example.picmejava.repository.ClienteRepository;
import com.example.picmejava.repository.UsuarioRepository;
import com.example.picmejava.service.autenticacao.dto.UsuarioLoginDTO;
import com.example.picmejava.service.autenticacao.dto.UsuarioTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private ClienteMapper clienteMapper = new ClienteMapper();

    public Cliente cadastrar(CadastroUsuarioDTO novoCliente){
        return usuarioRepository.save(clienteMapper.toCliente(novoCliente));
    }

    public Lista<Cliente> listar() {

        Lista<Cliente> clientes = new Lista();
        for(Cliente i :  clienteRepository.findAll()){
            clientes.add(i);
        }
        return clientes;
    }

    public Cliente atualizar(Integer idCliente, AtualizarUsuarioDTO dadosAtualizados){
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        Cliente cliente = clienteOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
        return clienteRepository.save(clienteMapper.toClienteAtualizado(cliente, dadosAtualizados));
    }

    public Cliente login(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente logoff(LoginUsuarioDTO buscarCliente){
        Cliente cliente = validarCliente(buscarCliente.getEmail(), buscarCliente.getSenha());
        cliente.setAutenticado(false);
        return clienteRepository.save(cliente);
    }

    public Cliente validarCliente(String email, String senha){
        Optional<Cliente> clienteOptional = clienteRepository.findByEmailAndSenha(email, senha);
        clienteOptional.orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
        return clienteOptional.get();
    }

    public UsuarioTokenDTO autenticar(UsuarioLoginDTO usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return FotografoMapper.of(usuarioAutenticado, token);
    }
}
