package com.cefet.ensina_mais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ensina_mais.dto.AlunoDTO;
import com.cefet.ensina_mais.dto.AvaliacaoDTO;
import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.dto.ProfessorDTO;
import com.cefet.ensina_mais.dto.TurmaDTO;
import com.cefet.ensina_mais.services.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable Long id) {
        ProfessorDTO professorDTO = professorService.findById(id);
        return ResponseEntity.ok(professorDTO);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ProfessorDTO> findByUsuarioId(@PathVariable Long usuarioId) {
        ProfessorDTO professorDTO = professorService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(professorDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<ProfessorDTO> professorDTOs = professorService.findAll();
        return ResponseEntity.ok(professorDTOs);
    }

    // Endpoint para buscar turmas de um professor
    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<TurmaDTO>> findTurmasByProfessorId(@PathVariable Long id) {
        List<TurmaDTO> turmas = professorService.findTurmasByProfessorId(id);
        return ResponseEntity.ok(turmas);
    }

    // Endpoint para buscar alunos de um professor
    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<AlunoDTO>> findAlunosByProfessorId(@PathVariable Long id) {
        List<AlunoDTO> alunos = professorService.findAlunosByProfessorId(id);
        return ResponseEntity.ok(alunos);
    }

    // Endpoint para buscar notas dos alunos de um professor
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaDTO>> findNotasByProfessorId(@PathVariable Long id) {
        List<NotaDTO> notas = professorService.findNotasByProfessorId(id);
        return ResponseEntity.ok(notas);
    }

    // Endpoint para buscar avaliações de um professor
    @GetMapping("/{id}/avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> findAvaliacoesByProfessorId(@PathVariable Long id) {
        List<AvaliacaoDTO> avaliacoes = professorService.findAvaliacoesByProfessorId(id);
        return ResponseEntity.ok(avaliacoes);
    }

    // Endpoint para atualizar nota (só professores podem alterar)
    @PutMapping("/{professorId}/notas/{notaId}")
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Long professorId, @PathVariable Long notaId, @RequestBody NotaDTO notaDTO) {
        NotaDTO notaAtualizada = professorService.updateNota(professorId, notaId, notaDTO);
        return ResponseEntity.ok(notaAtualizada);
    }

    // Endpoint para criar avaliação
    @PostMapping("/{professorId}/avaliacoes")
    public ResponseEntity<AvaliacaoDTO> createAvaliacao(@PathVariable Long professorId, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO novaAvaliacao = professorService.createAvaliacao(professorId, avaliacaoDTO);
        return ResponseEntity.status(201).body(novaAvaliacao);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO novaPessoa = professorService.insert(professorDTO);
        return ResponseEntity.status(201).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO pessoaAtualizada = professorService.update(id, professorDTO);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
