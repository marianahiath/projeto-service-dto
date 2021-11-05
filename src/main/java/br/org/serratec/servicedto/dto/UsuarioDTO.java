package br.org.serratec.servicedto.dto;

import br.org.serratec.servicedto.model.Usuario;

public class UsuarioDTO {

    private String nome; //só isso vai aparecer na resposta
    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
