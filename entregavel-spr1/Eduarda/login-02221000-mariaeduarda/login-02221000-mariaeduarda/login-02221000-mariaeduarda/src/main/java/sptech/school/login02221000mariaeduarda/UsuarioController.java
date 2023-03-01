package sptech.school.login02221000mariaeduarda;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> usuarios;

    public UsuarioController() {
        this.usuarios = new ArrayList();
    }

 @PostMapping
 public UsuarioDTO cadastrar(@RequestBody Usuario novoUsuario){

        usuarios.add(novoUsuario);

        return new UsuarioDTO(novoUsuario);
 }

    @GetMapping
    public List<UsuarioDTO> listar(){
        List <UsuarioDTO> listaBusca = new ArrayList();

        for (Usuario usuario : usuarios) {
            {
                listaBusca.add(new UsuarioDTO(usuario));
            }
        }
        return listaBusca;
    }

    @PostMapping("autenticacao/{usuario}/{senha}")
        public Usuario autenticar(@PathVariable String usuario, @PathVariable String senha){
            for(Usuario u : usuarios){
                if(u.getUsuario().equals(usuario) && u.getSenha().equals(senha)){
                    u.setAutenticado(true);

                    return u;
                }
            }
                    return null;
    }

    @DeleteMapping("/autenticacao/{usuario}")
    public String deletar(@PathVariable String usuario){
        for(Usuario u : usuarios){
            if(u.getUsuario().equals(usuario)){
                if(u.isAutenticado()){
                    u.setAutenticado(false);
                    return String.format("Logoff do usuário %s concluído!", u.getNome());
                }
                else{
                    return String.format("Usuário %s não está autenticado.", u.getNome());
                }

            }
        }
        return String.format ("Usuário %s não encontrado.", usuario);
    }

    @PutMapping("/atualizacao/{indice}")
    public UsuarioDTO atualizar(@PathVariable int indice, @RequestBody Usuario usuario){

        if (indice >= 0 && indice < usuarios.size()) {

            usuarios.set(indice, usuario);

            return new UsuarioDTO(usuario);
        }

        return null;
    }

}
