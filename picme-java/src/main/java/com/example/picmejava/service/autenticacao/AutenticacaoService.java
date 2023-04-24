//package com.example.picmejava.service.autenticacao;
//
//import com.example.picmejava.model.Fotografo;
//import com.example.picmejava.repository.FotografoRepository;
//import com.example.picmejava.service.autenticacao.dto.UsuarioDetalhesDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AutenticacaoService implements UserDetailsService {
//
//    @Autowired
//    private FotografoRepository fotografoRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Fotografo> fotografoOptional = fotografoRepository.findByEmail(username);
//
//        fotografoOptional.orElseThrow(() -> new UsernameNotFoundException(
//                String.format("Usuário: %s não encontrado", username))
//        );
//
//        return new UsuarioDetalhesDTO(fotografoOptional.get());
//    }
//}
