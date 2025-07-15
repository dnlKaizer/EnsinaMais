package com.cefet.ensina_mais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.ensina_mais.entities.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    public void deleteByTurmaId(Long turmaId);
    
}
