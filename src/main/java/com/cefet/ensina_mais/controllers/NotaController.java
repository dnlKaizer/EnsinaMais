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

import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.services.NotaService;

@RestController
@RequestMapping("/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id) {
        NotaDTO notaDTO = notaService.findById(id);
        return ResponseEntity.ok(notaDTO);
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<NotaDTO> notaDTOs = notaService.findAll();
        return ResponseEntity.ok(notaDTOs);
    }

    @PostMapping
    public ResponseEntity<NotaDTO> create(@RequestBody NotaDTO notaDTO) {
        NotaDTO createdNota = notaService.insert(notaDTO);
        return ResponseEntity.status(201).body(createdNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> update(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        NotaDTO notaAtualizada = notaService.update(id, notaDTO);
        return ResponseEntity.ok(notaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
