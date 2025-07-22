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
        // Verifica se a nota é null (Campo Obrigatório)
        if (notaDTO.getNota() == null)
            throw new IllegalArgumentException("A nota não pode ser vazia.");

        // Verifica se a avaliação existe por ID (Campo Obrigatório)
        Avaliacao avaliacao = avaliacaoRepository.findById(notaDTO.getIdAvaliacao())
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + notaDTO.getIdAvaliacao()));

        // Verifica se a matrícula na turma existe por ID (Campo Obrigatório)
        MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(notaDTO.getIdMatriculaTurma())
                .orElseThrow(() -> new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + notaDTO.getIdMatriculaTurma()));

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
            nota.setNota(notaDTO.getNota());
        }

        // Atualiza a avaliação
        if (notaDTO.getIdAvaliacao() != null) {
            Avaliacao avaliacao = avaliacaoRepository.findById(notaDTO.getIdAvaliacao())
                    .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + notaDTO.getIdAvaliacao()));
            nota.setAvaliacao(avaliacao);
        }

        // Atualiza a matrícula na turma
        if (notaDTO.getIdMatriculaTurma() != null) {
            MatriculaTurma matriculaTurma = matriculaTurmaRepository.findById(notaDTO.getIdMatriculaTurma())
                    .orElseThrow(() -> new EntityNotFoundException("Matrícula na turma não encontrada com ID: " + notaDTO.getIdMatriculaTurma()));
            nota.setMatriculaTurma(matriculaTurma);
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
