package com.cefet.ensina_mais.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Verifica se a pessoa existe pelo email
    public boolean existsByEmail(String email);

    // Verifica se a pessoa existe pelo CPF
    public boolean existsByCpf(String cpf);

    // Busca Pessoa por CPF
    public Optional<Professor> findByCpf(String cpf);
}
