package com.cefet.ensina_mais.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.MatriculaDTO;
import com.cefet.ensina_mais.entities.Aluno;
import com.cefet.ensina_mais.entities.Matricula;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.repositories.AlunoRepository;
import com.cefet.ensina_mais.repositories.MatriculaRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MatriculaService {

    static Long nMatriculas = 100l;
    
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired 
    private AlunoRepository alunoRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired
    private NotaRepository notaRepository;

    // Buscar todos
    public List<MatriculaDTO> findAll() {
        List<Matricula> listaMatricula = matriculaRepository.findAll();
        return listaMatricula.stream().map(MatriculaDTO::new).toList();
    }

    // Buscar por ID
    public MatriculaDTO findById(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com ID: " + id));
        return new MatriculaDTO(matricula);
    }

    // Inserir matricula
    public MatriculaDTO insert(MatriculaDTO matriculaDTO) {

        // Verifica se a data não é nulo ou vazia (Campo Obrigatório)
        if (matriculaDTO.getData() == null || !StringUtils.hasText(matriculaDTO.getData().toString()))
            throw new IllegalArgumentException("A data de nascimento do matricula não pode ser vazia.");

        // Verifica se o aluno existe (Campo Obrigatório)
        Aluno aluno = alunoRepository.findById(matriculaDTO.getIdAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o id: " + matriculaDTO.getIdAluno()));

        Matricula matricula = new Matricula();
        matricula.setNumero(gerarNumero(matriculaDTO.getData()));
        matricula.setData(matriculaDTO.getData());
        matricula.setAluno(aluno);
        matricula = matriculaRepository.save(matricula);
        return new MatriculaDTO(matricula);
    }

    // Atualizar matricula ?

    // Remover por ID
    @Transactional
    public void delete(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula não encontrada com ID: " + id);
        }

        List<MatriculaTurma> matriculaTurmas = matriculaTurmaRepository.findByMatriculaId(id);
        for (MatriculaTurma matriculaTurma : matriculaTurmas) {
            List<Nota> notas = notaRepository.findByMatriculaTurmaId(matriculaTurma.getId());
            notaRepository.deleteAll(notas);
            
            matriculaTurmaRepository.delete(matriculaTurma);
        }

        matriculaRepository.deleteById(id);
    }

    private String gerarNumero(Date data) {
        return String.valueOf(data.toLocalDate().getYear() * 10000l + nMatriculas++);
    }
}
