package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.TurmaDTO;
import com.cefet.ensina_mais.entities.Disciplina;
import com.cefet.ensina_mais.entities.Professor;
import com.cefet.ensina_mais.entities.Turma;
import com.cefet.ensina_mais.repositories.AvaliacaoRepository;
import com.cefet.ensina_mais.repositories.DisciplinaRepository;
import com.cefet.ensina_mais.repositories.ProfessorRepository;
import com.cefet.ensina_mais.repositories.TurmaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TurmaService {
    
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired 
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // Buscar todos
    public List<TurmaDTO> findAll() {
        List<Turma> listaTurma = turmaRepository.findAll();
        return listaTurma.stream().map(TurmaDTO::new).toList();
    }

    // Buscar por ID
    public TurmaDTO findById(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + id));
        return new TurmaDTO(turma);
    }

    // Inserir turma
    public TurmaDTO insert(TurmaDTO turmaDTO) {
        // Verifica se o semestre não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(turmaDTO.getSemestre()))
            throw new IllegalArgumentException("O semestre não pode ser vazio.");

        // Verifica se o número de vagas é maior que zero (Campo Obrigatório)
        if (turmaDTO.getVagas() == null)
            throw new IllegalArgumentException("O número de vagas não pode ser vazio.");
        else if(turmaDTO.getVagas() <= 0)
            throw new IllegalArgumentException("O número de vagas não pode ser menor ou igual a zero.");

        // Verifica se o Professor existe por Id (Campo Obrigatório)
        Professor professor = professorRepository.findById(turmaDTO.getIdProfessor())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + turmaDTO.getIdProfessor()));
                
        // Verifica se a Disciplina existe por Id (Campo Obrigatório)
        Disciplina disciplina = disciplinaRepository.findById(turmaDTO.getIdDisciplina())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com ID: " + turmaDTO.getIdDisciplina()));

        Turma turma = new Turma();
        turma.setSemestre(turmaDTO.getSemestre());
        turma.setVagas(turmaDTO.getVagas());
        turma.setProfessor(professor);
        turma.setDisciplina(disciplina);
        turma = turmaRepository.save(turma);
        return new TurmaDTO(turma);
    }

    // Atualizar turma
    public TurmaDTO update(Long id, TurmaDTO turmaDTO) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("turma não encontrado com ID: " + id));
                
        // Atualiza o semestre
        if (StringUtils.hasText(turmaDTO.getSemestre()))
            turma.setSemestre(turmaDTO.getSemestre());
        
        // Atualiza as vagas
        if (turmaDTO.getVagas() != null) {
            if (turmaDTO.getVagas() <= 0) throw new IllegalArgumentException("Número de vagas inválido: " + turmaDTO.getVagas());
            turma.setVagas(turmaDTO.getVagas());
        }
            
        // Atualiza Professor
        if (turmaDTO.getIdProfessor() != null) {
            Professor professor = professorRepository.findById(turmaDTO.getIdProfessor())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + turmaDTO.getIdProfessor()));
                
            turma.setProfessor(professor);
        }
        
        // Verifica se a Disciplina está sendo alterada
        if (turmaDTO.getIdDisciplina() != null) {
            Disciplina disciplina = disciplinaRepository.findById(turmaDTO.getIdDisciplina())
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com ID: " + turmaDTO.getIdDisciplina()));

            if (!disciplina.equals(turma.getDisciplina()))
                throw new IllegalArgumentException("Não é permitido alterar uma Disciplina da Turma.");
        } 

        turma = turmaRepository.save(turma);
        return new TurmaDTO(turma);
    }

    // Remover por ID
    @Transactional
    public void delete(Long id) {
        if (!turmaRepository.existsById(id)) {
            throw new EntityNotFoundException("Turma não encontrado com ID: " + id);
        }
        avaliacaoRepository.deleteByTurmaId(id);
        turmaRepository.deleteById(id);
    }
}
