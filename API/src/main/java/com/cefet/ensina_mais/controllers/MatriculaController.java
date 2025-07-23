package com.cefet.ensina_mais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.ensina_mais.dto.MatriculaDTO;
import com.cefet.ensina_mais.services.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll() {
        List<MatriculaDTO> matriculaDTO = matriculaService.findAll();
        return ResponseEntity.ok(matriculaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable Long id) {
        MatriculaDTO matriculaDTO = matriculaService.findById(id);
        return ResponseEntity.ok(matriculaDTO);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> insert(@RequestBody MatriculaDTO matriculaDTO) {
        MatriculaDTO novaMatricula = matriculaService.insert(matriculaDTO);
        return ResponseEntity.status(201).body(novaMatricula);
    }

    // Atualizar matrícula (PUT) ?

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
