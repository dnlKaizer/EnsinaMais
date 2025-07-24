package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.DisciplinaDTO;
import com.cefet.ensina_mais.entities.Avaliacao;
import com.cefet.ensina_mais.entities.Disciplina;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.entities.Turma;
import com.cefet.ensina_mais.repositories.AvaliacaoRepository;
import com.cefet.ensina_mais.repositories.DisciplinaRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;
import com.cefet.ensina_mais.repositories.TurmaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private NotaRepository notaRepository;

    // Buscar todas
    public List<DisciplinaDTO> findAll() {
        List<Disciplina> listaDisciplina = disciplinaRepository.findAll();
        return listaDisciplina.stream().map(DisciplinaDTO::new).toList();
    }

    // Buscar por ID
    public DisciplinaDTO findById(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com ID: " + id));
        return new DisciplinaDTO(disciplina);
    }

    // Inserir Disciplina
    public DisciplinaDTO insert(DisciplinaDTO disciplinaDTO) {
        // Verifica se o nome não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(disciplinaDTO.getNome()))
            throw new IllegalArgumentException("O nome da disciplina não pode ser vazio.");

        // Verifica se já existe uma disciplina com o mesmo nome (Regra de Negócio -> Nome único)
        if (disciplinaRepository.existsByNome(disciplinaDTO.getNome()))
            throw new IllegalArgumentException("Já existe uma disciplina com o nome: " + disciplinaDTO.getNome());

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(disciplinaDTO.getNome());
        disciplina = disciplinaRepository.save(disciplina);
        return new DisciplinaDTO(disciplina);
    }

    // Atualizar Disciplina
    public DisciplinaDTO update(Long id, DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disciplina não encontrada com ID: " + id));
        
        // Verifica se já existe uma disciplina com o mesmo nome (Regra de Negócio -> Nome único)
        if (disciplinaRepository.existsByNome(disciplinaDTO.getNome()))
            throw new IllegalArgumentException("Já existe uma disciplina com o nome: " + disciplinaDTO.getNome());
        
        // Se o DTO tiver o campo nome, altera a disciplina
        if (StringUtils.hasText(disciplinaDTO.getNome()))
            disciplina.setNome(disciplinaDTO.getNome());
        
        disciplina = disciplinaRepository.save(disciplina);
        return new DisciplinaDTO(disciplina);
    }

    // Deletar Disciplina
    @Transactional
    public void delete(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new EntityNotFoundException("Disciplina não encontrada com ID: " + id);
        }
        List<Turma> turmas = turmaRepository.findByDisciplinaId(id);
        for (Turma turma : turmas) {
            List<MatriculaTurma> matriculas = matriculaTurmaRepository.findByTurmaId(turma.getId());

            for (MatriculaTurma matriculaTurma : matriculas) {
                List<Nota> notas = notaRepository.findByMatriculaTurmaId(matriculaTurma.getId());
                notaRepository.deleteAll(notas);

                matriculaTurmaRepository.delete(matriculaTurma);
            }

            List<Avaliacao> avaliacoes = avaliacaoRepository.findByTurmaId(turma.getId());
            avaliacaoRepository.deleteAll(avaliacoes);

            turmaRepository.deleteById(turma.getId());
        }

        disciplinaRepository.deleteById(id);
    }

}
