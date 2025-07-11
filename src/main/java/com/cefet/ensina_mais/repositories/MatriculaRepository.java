package com.cefet.ensina_mais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Aluno;
import com.cefet.ensina_mais.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
    public List<Matricula> findByAluno(Aluno aluno);
}
