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

import com.cefet.ensina_mais.dto.DisciplinaDTO;
import com.cefet.ensina_mais.services.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll() {
        List<DisciplinaDTO> disciplinasDTO = disciplinaService.findAll();
        return ResponseEntity.ok(disciplinasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> findById(@PathVariable Long id) {
        DisciplinaDTO disciplinaDTO = disciplinaService.findById(id);
        return ResponseEntity.ok(disciplinaDTO);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> insert(@RequestBody DisciplinaDTO disciplina) {
        DisciplinaDTO novaDisciplina = disciplinaService.insert(disciplina);
        return ResponseEntity.status(201).body(novaDisciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> update(@PathVariable Long id, @RequestBody DisciplinaDTO disciplina) {
        DisciplinaDTO disciplinaAtualizada = disciplinaService.update(id, disciplina);
        return ResponseEntity.ok(disciplinaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        disciplinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
