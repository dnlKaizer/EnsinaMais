package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Pessoa;

public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    
    public PessoaDTO(Pessoa p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.cpf = p.getCpf();
        this.email = p.getEmail();
    }

    public PessoaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
    
}
