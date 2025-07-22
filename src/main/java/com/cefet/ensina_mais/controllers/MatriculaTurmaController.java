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

import com.cefet.ensina_mais.dto.MatriculaTurmaDTO;
import com.cefet.ensina_mais.services.MatriculaTurmaService;

@RestController
@RequestMapping("/matriculas-turmas")
public class MatriculaTurmaController {
    @Autowired
    private MatriculaTurmaService matriculaTurmaService;

    @GetMapping("{id}")
    public ResponseEntity<MatriculaTurmaDTO> findById(@PathVariable Long id) {
        MatriculaTurmaDTO matriculaTurmaDTO = matriculaTurmaService.findById(id);
        return ResponseEntity.ok(matriculaTurmaDTO);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaTurmaDTO>> findAll() {
        List<MatriculaTurmaDTO> matriculaTurmaDTOs = matriculaTurmaService.findAll();
        return ResponseEntity.ok(matriculaTurmaDTOs);
    }

    @PostMapping
    public ResponseEntity<MatriculaTurmaDTO> create(@RequestBody MatriculaTurmaDTO matriculaTurmaDTO) {
        MatriculaTurmaDTO created = matriculaTurmaService.insert(matriculaTurmaDTO);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaTurmaDTO> update(@PathVariable Long id, @RequestBody MatriculaTurmaDTO matriculaTurmaDTO) {
        MatriculaTurmaDTO matriculaTurmaAtualizada = matriculaTurmaService.update(id, matriculaTurmaDTO);
        return ResponseEntity.ok(matriculaTurmaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaTurmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
