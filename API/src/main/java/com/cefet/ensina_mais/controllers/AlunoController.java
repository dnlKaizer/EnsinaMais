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
import com.cefet.ensina_mais.dto.DisciplinaDTO;
import com.cefet.ensina_mais.dto.MatriculaDTO;
import com.cefet.ensina_mais.dto.MatriculaTurmaDTO;
import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.dto.ProfessorDTO;
import com.cefet.ensina_mais.dto.TurmaDTO;
import com.cefet.ensina_mais.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Long id) {
        AlunoDTO alunoDTO = alunoService.findById(id);
        return ResponseEntity.ok(alunoDTO);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<AlunoDTO> findByUsuarioId(@PathVariable Long usuarioId) {
        AlunoDTO alunoDTO = alunoService.findByUsuarioId(usuarioId);
        return ResponseEntity.ok(alunoDTO);
    }

    @GetMapping("/usuario/login/{usuarioLogin}")
    public ResponseEntity<AlunoDTO> findByUsuarioLogin(@PathVariable String usuarioLogin) {
        AlunoDTO alunoDTO = alunoService.findByUsuarioLogin(usuarioLogin);
        return ResponseEntity.ok(alunoDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<AlunoDTO> alunoDTOs = alunoService.findAll();
        return ResponseEntity.ok(alunoDTOs);
    }

    // Endpoint para buscar matrículas de um aluno
    @GetMapping("/{id}/matriculas")
    public ResponseEntity<List<MatriculaDTO>> findMatriculasByAlunoId(@PathVariable Long id) {
        List<MatriculaDTO> matriculas = alunoService.findMatriculasByAlunoId(id);
        return ResponseEntity.ok(matriculas);
    }

    // Endpoint para buscar matrículas-turmas de um aluno
    @GetMapping("/{id}/matriculas-turmas")
    public ResponseEntity<List<MatriculaTurmaDTO>> findMatriculaTurmasByAlunoId(@PathVariable Long id) {
        List<MatriculaTurmaDTO> matriculaTurmas = alunoService.findMatriculaTurmasByAlunoId(id);
        return ResponseEntity.ok(matriculaTurmas);
    }

    // Endpoint para buscar notas de um aluno
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaDTO>> findNotasByAlunoId(@PathVariable Long id) {
        List<NotaDTO> notas = alunoService.findNotasByAlunoId(id);
        return ResponseEntity.ok(notas);
    }

    // Endpoint para buscar matrículas-turmas de uma matrícula específica
    @GetMapping("/{alunoId}/matriculas/{matriculaId}/matriculas-turmas")
    public ResponseEntity<List<MatriculaTurmaDTO>> findMatriculaTurmasByMatriculaId(@PathVariable Long alunoId, @PathVariable Long matriculaId) {
        List<MatriculaTurmaDTO> matriculaTurmas = alunoService.findMatriculaTurmasByMatriculaId(matriculaId);
        return ResponseEntity.ok(matriculaTurmas);
    }

    // Endpoint para buscar notas de uma matrícula-turma específica
    @GetMapping("/{alunoId}/matriculas-turmas/{matriculaTurmaId}/notas")
    public ResponseEntity<List<NotaDTO>> findNotasByMatriculaTurmaId(@PathVariable Long alunoId, @PathVariable Long matriculaTurmaId) {
        List<NotaDTO> notas = alunoService.findNotasByMatriculaTurmaId(matriculaTurmaId);
        return ResponseEntity.ok(notas);
    }

    // Endpoint para buscar disciplina por matrícula-turma ID
    @GetMapping("/{alunoId}/matriculas-turmas/{matriculaTurmaId}/disciplina")
    public ResponseEntity<DisciplinaDTO> findDisciplinaByMatriculaTurmaId(@PathVariable Long alunoId, @PathVariable Long matriculaTurmaId) {
        DisciplinaDTO disciplina = alunoService.findDisciplinaByMatriculaTurmaId(matriculaTurmaId);
        return ResponseEntity.ok(disciplina);
    }

    // Endpoint para buscar professor por matrícula-turma ID
    @GetMapping("/{alunoId}/matriculas-turmas/{matriculaTurmaId}/professor")
    public ResponseEntity<ProfessorDTO> findProfessorByMatriculaTurmaId(@PathVariable Long alunoId, @PathVariable Long matriculaTurmaId) {
        ProfessorDTO professor = alunoService.findProfessorByMatriculaTurmaId(matriculaTurmaId);
        return ResponseEntity.ok(professor);
    }

    // Endpoint para buscar avaliações por matrícula-turma ID
    @GetMapping("/{alunoId}/matriculas-turmas/{matriculaTurmaId}/avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> findAvaliacoesByMatriculaTurmaId(@PathVariable Long alunoId, @PathVariable Long matriculaTurmaId) {
        List<AvaliacaoDTO> avaliacoes = alunoService.findAvaliacoesByMatriculaTurmaId(matriculaTurmaId);
        return ResponseEntity.ok(avaliacoes);
    }

    // Endpoint para buscar turma por matrícula-turma ID
    @GetMapping("/{alunoId}/matriculas-turmas/{matriculaTurmaId}/turma")
    public ResponseEntity<TurmaDTO> findTurmaByMatriculaTurmaId(@PathVariable Long alunoId, @PathVariable Long matriculaTurmaId) {
        TurmaDTO turma = alunoService.findTurmaByMatriculaTurmaId(matriculaTurmaId);
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> insert(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO novoAluno = alunoService.insert(alunoDTO);
        return ResponseEntity.status(201).body(novoAluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoAtualizado = alunoService.update(id, alunoDTO);
        return ResponseEntity.ok(alunoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
