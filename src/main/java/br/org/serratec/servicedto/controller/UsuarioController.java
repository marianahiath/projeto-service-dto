package br.org.serratec.servicedto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.servicedto.dto.UsuarioDTO;
import br.org.serratec.servicedto.exception.EmailException;
import br.org.serratec.servicedto.model.Usuario;
import br.org.serratec.servicedto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService; //agora comunica com o service

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> inserir(@RequestBody Usuario usuario) {
        try {
            usuario = usuarioService.inserir(usuario);

           URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(usuario.getId())
            .toUri();

           return ResponseEntity.created(uri).body(usuario);
        } catch (EmailException e) {
           return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }

    }

    /*
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    } 
    */

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }
}
