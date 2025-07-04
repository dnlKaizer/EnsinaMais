package com.cefet.ensina_mais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    
}
