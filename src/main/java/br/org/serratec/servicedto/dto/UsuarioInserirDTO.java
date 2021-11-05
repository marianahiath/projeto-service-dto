package br.org.serratec.servicedto.dto;

import br.org.serratec.servicedto.model.Usuario;

public class UsuarioInserirDTO { //todas as infos que vão ficar visíveis ao usuário
    
    private String nome;
    private String email;
    private String senha;

    public UsuarioInserirDTO() {
    }

    public UsuarioInserirDTO(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
