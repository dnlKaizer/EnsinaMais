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

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<AlunoDTO> alunoDTOs = alunoService.findAll();
        return ResponseEntity.ok(alunoDTOs);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO novaPessoa = alunoService.insert(alunoDTO);
        return ResponseEntity.status(201).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO) {
        AlunoDTO pessoaAtualizada = alunoService.update(id, alunoDTO);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
