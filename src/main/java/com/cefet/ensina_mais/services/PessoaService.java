package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.PessoaDTO;
import com.cefet.ensina_mais.entities.Pessoa;
import com.cefet.ensina_mais.repositories.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Buscar todos
    public List<PessoaDTO> findAll() {
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();
    }

    // Buscar por ID
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));
        return new PessoaDTO(pessoa);
    }

    // Inserir Pessoa
    public PessoaDTO insert(PessoaDTO pessoaDTO) {
        // Verifica se o nome não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(pessoaDTO.getNome()))
            throw new IllegalArgumentException("O nome da pessoa não pode ser vazio.");

        // Verifica se o cpf não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(pessoaDTO.getCpf()))
            throw new IllegalArgumentException("O CPF da pessoa não pode ser vazio.");

        // Verifica se o cpf já existe (Regra de Negócio -> Cpf único)
        if (pessoaRepository.existsByCpf(pessoaDTO.getCpf()))
            throw new IllegalArgumentException("Já existe uma pessoa com o CPF: " + pessoaDTO.getCpf());

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoa);
    }

    // Atualizar Pessoa
    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com ID: " + id));

        // Verifica se o DTO tem o campo CPF e se ele é diferente do cpf no banco
        if (StringUtils.hasText(pessoaDTO.getCpf()) && !pessoaDTO.getCpf().equals(pessoa.getCpf()))
            throw new IllegalArgumentException("Não é permitido alterar o CPF de uma pessoa.");

        // Se o DTO tiver o campo nome, altera a pessoa
        if (StringUtils.hasText(pessoaDTO.getNome()))
            pessoa.setNome(pessoaDTO.getNome());

        // Se o DTO tiver o campo email, altera o email
        if (StringUtils.hasText(pessoaDTO.getEmail()))
            pessoa.setEmail(pessoaDTO.getEmail());

        pessoa = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoa);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrada com ID: " + id);
        }
        pessoaRepository.deleteById(id);
    }

}
