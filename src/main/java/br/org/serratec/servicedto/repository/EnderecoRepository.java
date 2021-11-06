package br.org.serratec.servicedto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.servicedto.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    public Endereco findByCep(String cep);
}
