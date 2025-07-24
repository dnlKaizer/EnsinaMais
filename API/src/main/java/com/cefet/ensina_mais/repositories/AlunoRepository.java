package com.cefet.ensina_mais.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ensina_mais.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Verifica se a pessoa existe pelo email
    public boolean existsByEmail(String email);

    // Verifica se a pessoa existe pelo CPF
    public boolean existsByCpf(String cpf);

    // Busca Pessoa por CPF
    public Optional<Aluno> findByCpf(String cpf);

    public Optional<Aluno> findByUsuarioId(Long usuarioId);

    public Optional<Aluno> findByUsuarioLogin(String usuarioLogin);
}
