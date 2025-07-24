package com.cefet.ensina_mais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    
    public List<Nota> findByAvaliacaoId(Long avaliacaoId);

    public List<Nota> findByMatriculaTurmaId(Long matriculaTurmaId);
    
    // Método para verificar se já existe nota para uma avaliação específica e matrícula-turma
    public List<Nota> findByAvaliacaoIdAndMatriculaTurmaId(Long avaliacaoId, Long matriculaTurmaId);
}
