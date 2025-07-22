package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.entities.Avaliacao;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.repositories.AvaliacaoRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NotaService {
    
    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    // Buscar todos
    public List<NotaDTO> findAll() {
        List<Nota> listaNota = notaRepository.findAll();
        return listaNota.stream().map(NotaDTO::new).toList();
    }

    // Buscar por ID
    public NotaDTO findById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada com ID: " + id));
        return new NotaDTO(nota);
    }

    //Inserir nota
    public NotaDTO insert(NotaDTO notaDTO) {

        // Verifica se a avaliação existe por ID (Campo Obrigatório)
        Avaliacao avaliacao = avaliacaoRepository.findById(notaDTO.getIdAvaliacao())
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + notaDTO.getIdAvaliacao()));

        // Verifica se a matrícula na turma existe por ID (Campo Obrigatório)
        MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(notaDTO.getIdMatriculaTurma())
                .orElseThrow(() -> new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + notaDTO.getIdMatriculaTurma()));

        // VALIDAÇÃO DE CONSISTÊNCIA: Verifica se a avaliação e matrícula são da mesma turma
        if (!avaliacao.getTurma().getId().equals(matriculaTurma.getTurma().getId())) {
            throw new IllegalArgumentException(
                String.format("Inconsistência detectada: A avaliação pertence à turma %d, mas a matrícula está na turma %d. " +
                             "Uma nota só pode ser atribuída se a avaliação e a matrícula forem da mesma turma.",
                             avaliacao.getTurma().getId(), matriculaTurma.getTurma().getId())
            );
        }

        Nota nota = new Nota();
        nota.setNota(notaDTO.getNota());
        nota.setAvaliacao(avaliacao);
        nota.setMatriculaTurma(matriculaTurma);
        nota = notaRepository.save(nota);
        return new NotaDTO(nota);
    }

    // Atualizar nota
    public NotaDTO update(Long id, NotaDTO notaDTO) {
        // Verifica se a nota existe
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada com ID: " + id));

        //Atualiza a nota
        if (notaDTO.getNota() != null) {
            // Verifica se a nota está dentro do intervalo permitido
            if (notaDTO.getNota() < 0 || notaDTO.getNota() > nota.getAvaliacao().getNotaMaxima()) {
                throw new IllegalArgumentException("Nota deve estar entre 0 e " + nota.getAvaliacao().getNotaMaxima() + ".");
            } else {
                nota.setNota(notaDTO.getNota());
            }
        }

        // Verifica se a avaliação é nula ou igual à existente
        if (notaDTO.getIdAvaliacao() != null && !notaDTO.getIdAvaliacao().equals(nota.getAvaliacao().getId())) {
            throw new IllegalArgumentException("Não é permitido atualizar a avaliação de uma nota existente.");
        }

        // Verifica se a MatriculaTurma é nula ou igual à existente
        if (notaDTO.getIdMatriculaTurma() != null && !notaDTO.getIdMatriculaTurma().equals(nota.getMatriculaTurma().getId())) {
            throw new IllegalArgumentException("Não é permitido atualizar a matrícula na turma de uma nota existente.");
        }

        nota = notaRepository.save(nota);
        return new NotaDTO(nota);
    }

    // Remover nota por ID
    public void delete(Long id) {
        if(!notaRepository.existsById(id)) {
            throw new EntityNotFoundException("Nota não encontrada com ID: " + id);
        }
        notaRepository.deleteById(id);
    }
}
