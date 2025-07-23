package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Disciplina;

public class DisciplinaDTO {
    
    private Long id;
    private String nome;

    public DisciplinaDTO(Disciplina d) {
        this.id = d.getId();
        this.nome = d.getNome();
    }

    public DisciplinaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
