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

import com.cefet.ensina_mais.dto.TurmaDTO;
import com.cefet.ensina_mais.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> findById(@PathVariable Long id) {
        TurmaDTO turmaDTO = turmaService.findById(id);
        return ResponseEntity.ok(turmaDTO);
    }

    @GetMapping
    public ResponseEntity<List<TurmaDTO>> findAll() {
        List<TurmaDTO> turmaDTOs = turmaService.findAll();
        return ResponseEntity.ok(turmaDTOs);
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO) {
        TurmaDTO novaTurma = turmaService.insert(turmaDTO);
        return ResponseEntity.status(201).body(novaTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO) {
        TurmaDTO turmaAtualizada = turmaService.update(id, turmaDTO);
        return ResponseEntity.ok(turmaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
