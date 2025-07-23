package com.cefet.ensina_mais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    
    public List<Nota> deleteByAvaliacaoId(Long avaliacaoId);

    public List<Nota> deleteByMatriculaTurmaId(Long matriculaTurmaId);
}
