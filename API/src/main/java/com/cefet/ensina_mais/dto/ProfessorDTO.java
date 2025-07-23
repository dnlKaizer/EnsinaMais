package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Professor;

public class ProfessorDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String titulacao;

    public ProfessorDTO() {
    }

    public ProfessorDTO(Professor p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.cpf = p.getCpf();
        this.email = p.getEmail();
        this.titulacao = p.getTitulacao();
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

    public String getTitulacao() {
        return titulacao;
    }

}
