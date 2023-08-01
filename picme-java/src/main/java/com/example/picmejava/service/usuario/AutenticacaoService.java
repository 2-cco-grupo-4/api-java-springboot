package com.example.picmejava.service.usuario;

import com.example.picmejava.model.Usuario;
import com.example.picmejava.repository.UsuarioRepository;
import com.example.picmejava.service.usuario.dto.ValidarNovoUsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Tag(name = "Autenticação", description = "Serviço de autenticação de usuários")
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Operation(summary = "Carregar usuário pelo nome de usuário", description = "Carrega os detalhes do usuário com base no nome de usuário fornecido.")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    @Operation(summary = "Listar usuários com email ou cpf informado", description = "Retorna quais cpfs e emails já estão cadastrados em nossa base de acordo com o que foi informado")
    public List<ValidarNovoUsuarioDTO> validarEmailCpf(String email, String cpf) {
        List<ValidarNovoUsuarioDTO> listaRepetidos = new ArrayList<>();
        List<Usuario> listaUsuariosRepetidos;
        if(usuarioRepository.findByEmailOrCpf(email, cpf).isPresent()) {
            listaUsuariosRepetidos = usuarioRepository.findByEmailOrCpf(email, cpf).get();
            for (Usuario usuario : listaUsuariosRepetidos){
                ValidarNovoUsuarioDTO modeloEmailCpf = new ValidarNovoUsuarioDTO();
                modeloEmailCpf.setEmail(usuario.getEmail());
                modeloEmailCpf.setCpf(usuario.getCpf());
                listaRepetidos.add(modeloEmailCpf);
            }
        }
        return listaRepetidos;
    }

}
