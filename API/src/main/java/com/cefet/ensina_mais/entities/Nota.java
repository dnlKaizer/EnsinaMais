package com.cefet.ensina_mais.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_nota", 
    uniqueConstraints = @UniqueConstraint(columnNames = {"matricula_turma_id", "avaliacao_id"}))
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    // Nota pode ser nula, por exemplo, se a avaliação ainda não foi realizada
    private Double nota;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id", nullable = false)
    private Avaliacao avaliacao;

    @ManyToOne
    @JoinColumn(name = "matricula_turma_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    public Nota() {}

    public Nota(Long id, Double nota, Avaliacao avaliacao, MatriculaTurma matriculaTurma) {
        this.id = id;
        this.nota = nota;
        this.avaliacao = avaliacao;
        this.matriculaTurma = matriculaTurma;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public MatriculaTurma getMatriculaTurma() {
        return matriculaTurma;
    }
    public void setMatriculaTurma(MatriculaTurma matriculaTurma) {
        this.matriculaTurma = matriculaTurma;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nota other = (Nota) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
