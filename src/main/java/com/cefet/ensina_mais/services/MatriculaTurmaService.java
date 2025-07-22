package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ensina_mais.dto.MatriculaTurmaDTO;
import com.cefet.ensina_mais.entities.Matricula;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.SituacaoMatricula;
import com.cefet.ensina_mais.entities.Turma;
import com.cefet.ensina_mais.repositories.MatriculaRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;
import com.cefet.ensina_mais.repositories.TurmaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MatriculaTurmaService {

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired 
    private MatriculaRepository matriculaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private NotaRepository notaRepository;

    // Buscar todos
    public List<MatriculaTurmaDTO> findAll() {
        List<MatriculaTurma> listaMatriculaTurmas = matriculaTurmaRepository.findAll();
        return listaMatriculaTurmas.stream().map(MatriculaTurmaDTO::new).toList();
    }

    // Buscar por ID
    public MatriculaTurmaDTO findById(Long id) {
        MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + id));
        return new MatriculaTurmaDTO(matriculaTurma);
    }

    // Inserir matrícula na turma
    public MatriculaTurmaDTO insert(MatriculaTurmaDTO matriculaTurmaDTO) {
        // Verifica se a situação é válida (Campo Obrigatório)
        if (matriculaTurmaDTO.getSituacao() == null) {
            throw new IllegalArgumentException("Situação é obrigatória.");
        }

        SituacaoMatricula situacao;
        try {
            situacao = SituacaoMatricula.fromCodigo(matriculaTurmaDTO.getSituacao());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Situação inválida. Deve ser 0 (Reprovado), 1 (Aprovado) ou 2 (Em Andamento).");
        }

        //Verifica se a nota final é null ou não está entre 0 e 100 (Campo Obrigatório)
        if (matriculaTurmaDTO.getNotaFinal() == null || 
            matriculaTurmaDTO.getNotaFinal() < 0 || matriculaTurmaDTO.getNotaFinal() > 100) {
            throw new IllegalArgumentException("Nota final inválida. Deve estar entre 0 e 100.");
        }

        //Verifica se a matrícula existe por ID (Campo Obrigatório)
        Matricula matricula = matriculaRepository.findById(matriculaTurmaDTO.getIdMatricula())
            .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com ID: " + matriculaTurmaDTO.getIdMatricula()));

        //Verifica se a turma existe por ID (Campo Obrigatório)
        Turma turma = turmaRepository.findById(matriculaTurmaDTO.getIdTurma())
            .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + matriculaTurmaDTO.getIdTurma()));

        MatriculaTurma matriculaTurma = new MatriculaTurma();
        matriculaTurma.setSituacao(situacao);
        matriculaTurma.setNotaFinal(matriculaTurmaDTO.getNotaFinal());
        matriculaTurma.setMatricula(matricula);
        matriculaTurma.setTurma(turma);
        matriculaTurma = matriculaTurmaRepository.save(matriculaTurma);
        return new MatriculaTurmaDTO(matriculaTurma);
    }

    // Atualizar matrícula na turma
    public MatriculaTurmaDTO update(Long id, MatriculaTurmaDTO matriculaTurmaDTO) {
        // Verifica se a matrícula na turma existe
        MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + id));

        // Atualiza a situação
        if (matriculaTurmaDTO.getSituacao() != null) {
            try {
                SituacaoMatricula situacao = SituacaoMatricula.fromCodigo(matriculaTurmaDTO.getSituacao());
                matriculaTurma.setSituacao(situacao);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Situação inválida. Os valores permitidos são 0 (Reprovado), 1 (Aprovado) ou 2 (Em Andamento).");
            }
        }

        // Atualiza a nota final
        if (matriculaTurmaDTO.getNotaFinal() != null) {
            if (matriculaTurmaDTO.getNotaFinal() < 0 || matriculaTurmaDTO.getNotaFinal() > 100) {
                throw new IllegalArgumentException("Nota final deve estar no intervalo de 0 a 100.");
            }
            matriculaTurma.setNotaFinal(matriculaTurmaDTO.getNotaFinal());
        }

        matriculaTurma = matriculaTurmaRepository.save(matriculaTurma);
        return new MatriculaTurmaDTO(matriculaTurma);
    }

    // Remover por ID
    @Transactional
    public void delete(Long id) {
        if (!matriculaTurmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + id);
        }

        notaRepository.deleteByMatriculaTurmaId(id);
        matriculaTurmaRepository.deleteById(id);
    }
}
