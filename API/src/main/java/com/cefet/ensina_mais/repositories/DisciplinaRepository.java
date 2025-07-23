package com.cefet.ensina_mais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    // Verifica se já existe uma disciplina com o mesmo nome
    boolean existsByNome(String nome);
}
