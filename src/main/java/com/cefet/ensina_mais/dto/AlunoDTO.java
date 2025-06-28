package com.cefet.ensina_mais.dto;

import java.sql.Date;

import com.cefet.ensina_mais.entities.Aluno;

public class AlunoDTO {
    
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;

    public AlunoDTO(Aluno p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.cpf = p.getCpf();
        this.email = p.getEmail();
        this.dataNascimento = p.getDataNascimento();
    }

    public AlunoDTO() {
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

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
}
