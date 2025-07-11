package com.cefet.ensina_mais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Disciplina;
import com.cefet.ensina_mais.entities.Professor;
import com.cefet.ensina_mais.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
    public List<Turma> findByProfessor(Professor professor);

    public List<Turma> findByDisciplina(Disciplina disciplina);

}
