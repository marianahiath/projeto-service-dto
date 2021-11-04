package br.org.serratec.servicedto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.servicedto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String email); //poderia ser getBy
    Usuario findByNome(String nome);
}