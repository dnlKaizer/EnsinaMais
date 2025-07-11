package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Turma;

public class TurmaDTO {

    private Long id;
    private String semestre;
    private Integer vagas;
    private Long idDisciplina;
    private Long idProfessor;

    public TurmaDTO(Turma turma) {
        this.id = turma.getId();
        this.semestre = turma.getSemestre();
        this.vagas = turma.getVagas();
        this.idDisciplina = turma.getDisciplina().getId();
        this.idProfessor = turma.getProfessor().getId();
    }

    public TurmaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getSemestre() {
        return semestre;
    }

    public Integer getVagas() {
        return vagas;
    }

    public Long getIdDisciplina() {
        return idDisciplina;
    }

    public Long getIdProfessor() {
        return idProfessor;
    }
    
}
