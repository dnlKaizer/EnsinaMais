package com.cefet.ensina_mais.dto;

import com.cefet.ensina_mais.entities.Nota;

public class NotaDTO {
    private Long id;
    private Double nota;
    private Long idAvaliacao;
    private Long idMatriculaTurma;

    public NotaDTO(Nota nota) {
        this.id = nota.getId();
        this.nota = nota.getNota();
        this.idAvaliacao = nota.getAvaliacao().getId();
        this.idMatriculaTurma = nota.getMatriculaTurma().getId();
    }

    public NotaDTO() {}

    public Long getId() {
        return id;
    }

    public Double getNota() {
        return nota;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public Long getIdMatriculaTurma() {
        return idMatriculaTurma;
    }
}
