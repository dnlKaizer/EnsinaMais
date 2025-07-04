package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.AlunoDTO;
import com.cefet.ensina_mais.entities.Aluno;
import com.cefet.ensina_mais.repositories.AlunoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    // Buscar todos
    public List<AlunoDTO> findAll() {
        List<Aluno> listaAluno = alunoRepository.findAll();
        return listaAluno.stream().map(AlunoDTO::new).toList();
    }

    // Buscar por ID
    public AlunoDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
        return new AlunoDTO(aluno);
    }

    // Inserir Aluno
    public AlunoDTO insert(AlunoDTO alunoDTO) {
        // Verifica se o nome não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(alunoDTO.getNome()))
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");

        // Verifica se o cpf não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(alunoDTO.getCpf()))
            throw new IllegalArgumentException("O CPF do aluno não pode ser vazio.");

        // Verifica se a data não é nulo ou vazia (Campo Obrigatório)
        if (alunoDTO.getDataNascimento() == null || !StringUtils.hasText(alunoDTO.getDataNascimento().toString()))
            throw new IllegalArgumentException("A data de nascimento do aluno não pode ser vazia.");

        // Verifica se o cpf já existe (Regra de Negócio -> Cpf único)
        if (alunoRepository.existsByCpf(alunoDTO.getCpf()))
            throw new IllegalArgumentException("Já existe um aluno com o CPF: " + alunoDTO.getCpf());

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno = alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    // Atualizar Aluno
    public AlunoDTO update(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        // Verifica se o DTO tem o campo CPF e se ele é diferente do cpf no banco
        if (StringUtils.hasText(alunoDTO.getCpf()) && !alunoDTO.getCpf().equals(aluno.getCpf()))
            throw new IllegalArgumentException("Não é permitido alterar o CPF do aluno.");

        // Se o DTO tiver o campo nome, altera o nome
        if (StringUtils.hasText(alunoDTO.getNome()))
            aluno.setNome(alunoDTO.getNome());

        // Se o DTO tiver o campo email, altera o email
        if (StringUtils.hasText(alunoDTO.getEmail()))
            aluno.setEmail(alunoDTO.getEmail());

        // Se o DTO tiver o campo data, altera a data
        if (StringUtils.hasText(alunoDTO.getDataNascimento().toString()))
            aluno.setDataNascimento(alunoDTO.getDataNascimento());

        aluno = alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + id);
        }
        alunoRepository.deleteById(id);
    }

}
