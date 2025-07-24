package com.cefet.ensina_mais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.ensina_mais.entities.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    
    public List<Avaliacao> findByTurmaId(Long turmaId);
    
}
