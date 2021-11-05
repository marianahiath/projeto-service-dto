package br.org.serratec.servicedto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.servicedto.config.MailConfig;
import br.org.serratec.servicedto.dto.UsuarioDTO;
import br.org.serratec.servicedto.dto.UsuarioInserirDTO;
import br.org.serratec.servicedto.exception.EmailException;
import br.org.serratec.servicedto.model.Usuario;
import br.org.serratec.servicedto.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired //não fala mais com o controller
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailConfig mailConfig;

    public List<UsuarioDTO> listar() { //aqui não retorna ResponseEntity
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDto = new ArrayList<UsuarioDTO>();
       
        for (Usuario usuario : usuarios) {

            UsuarioDTO usuarioDto = new UsuarioDTO(usuario);

            usuariosDto.add(usuarioDto);
        }

        return usuariosDto;
    }

    public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException {

       if(usuarioRepository.findByEmail(usuarioInserirDTO.getEmail()) != null) {
           throw new EmailException("Email já cadastrado");
       }

       Usuario usuario = new Usuario();
       usuario.setNome(usuarioInserirDTO.getNome());
       usuario.setEmail(usuarioInserirDTO.getEmail());
       usuario.setSenha(bCryptPasswordEncoder.encode(usuarioInserirDTO.getSenha()));
       usuarioRepository.save(usuario);

       mailConfig.enviarEmail(usuario.getEmail(), "Cadastro efetuado com sucesso.", usuario.toString());

       return new UsuarioDTO(usuario);
    }

    public ResponseEntity<Void> deletar(Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}