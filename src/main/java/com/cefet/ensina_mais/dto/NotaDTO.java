package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Nota;

public class NotaDTO {
    private Long id;
    private Double nota;
    private Long avaliacaoId;
    private Long matriculaTurmaId;

    public NotaDTO(Nota nota) {
        this.id = nota.getId();
        this.nota = nota.getNota();
        this.avaliacaoId = nota.getAvaliacao().getId();
        this.matriculaTurmaId = nota.getMatriculaTurma().getId();
    }

    public NotaDTO() {}

    public Long getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public Long getIdAvaliacao() {
        return avaliacaoId;
    }

    public Long getIdMatriculaTurma() {
        return matriculaTurmaId;
    }
}
