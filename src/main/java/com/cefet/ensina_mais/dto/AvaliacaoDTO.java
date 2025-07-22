package com.cefet.ensina_mais.dto;

import java.sql.Date;
import com.cefet.ensina_mais.entities.Avaliacao;

public class AvaliacaoDTO {
    private Long id;
    private Date data;
    private String descricao;
    private Double notaMaxima;
    private Long idTurma;

    public AvaliacaoDTO(Avaliacao a) {
        this.id = a.getId();
        this.data = a.getData();
        this.descricao = a.getDescricao();
        this.notaMaxima = a.getNotaMaxima();
        this.idTurma = a.getTurma().getId();
    }

    public AvaliacaoDTO() {
    }

    public Long getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getNotaMaxima() {
        return notaMaxima;
    }

    public Long getIdTurma() {
        return idTurma;
    }
}
