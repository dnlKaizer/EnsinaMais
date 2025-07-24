package com.cefet.ensina_mais.dto;

import java.sql.Date;

import com.cefet.ensina_mais.entities.Matricula;

public class MatriculaDTO {
    
    private Long id;
    private String numero;
    private Date data;
    private Long idAluno;

    public MatriculaDTO(Matricula matricula) {
        this.id = matricula.getId();
        this.numero = matricula.getNumero();
        this.data = matricula.getData();
        this.idAluno = matricula.getAluno().getId();
    }

    public MatriculaDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Date getData() {
        return data;
    }

    public Long getIdAluno() {
        return idAluno;
    }

}
