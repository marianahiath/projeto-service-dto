package br.org.serratec.servicedto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.servicedto.dto.UsuarioDTO;
import br.org.serratec.servicedto.exception.EmailException;
import br.org.serratec.servicedto.model.Usuario;
import br.org.serratec.servicedto.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired //não fala mais com o controller
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UsuarioDTO> listar() { //aqui não retorna ResponseEntity
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDto = new ArrayList<UsuarioDTO>();
       
        for (Usuario usuario : usuarios) {

            UsuarioDTO usuarioDto = new UsuarioDTO(usuario);

            usuariosDto.add(usuarioDto);
        }

        return usuariosDto;
    }

    public Usuario inserir(Usuario user) throws EmailException {

       Usuario usuario = usuarioRepository.findByEmail(user.getEmail());

       if(usuario != null) {
           throw new EmailException("Email já cadastrado");
       }

       user.setSenha(bCryptPasswordEncoder.encode(user.getSenha())); //criptografando

       return usuarioRepository.save(user);
    }
}