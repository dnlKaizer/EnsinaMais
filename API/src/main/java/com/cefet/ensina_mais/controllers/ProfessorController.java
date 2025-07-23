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

import com.cefet.ensina_mais.dto.ProfessorDTO;
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

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<ProfessorDTO> professorDTOs = professorService.findAll();
        return ResponseEntity.ok(professorDTOs);
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
