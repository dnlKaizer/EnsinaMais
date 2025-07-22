package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.MatriculaTurma;

public class MatriculaTurmaDTO {
    
    private Long id;
    private Integer situacao;
    private Double notaFinal;
    private Long idMatricula;
    private Long idTurma;

    public MatriculaTurmaDTO(MatriculaTurma matriculaTurma) {
        this.id = matriculaTurma.getId();
        this.situacao = matriculaTurma.getSituacao();
        this.notaFinal = matriculaTurma.getNotaFinal();
        this.idMatricula = matriculaTurma.getMatricula().getId();
        this.idTurma = matriculaTurma.getTurma().getId();
    }
    
    public MatriculaTurmaDTO() {
    }

    public Long getId() {
        return id;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public Long getIdMatricula() {
        return idMatricula;
    }

    public Long getIdTurma() {
        return idTurma;
    }
}