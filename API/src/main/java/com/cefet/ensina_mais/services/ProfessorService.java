package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.ProfessorDTO;
import com.cefet.ensina_mais.entities.Avaliacao;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.NivelAcesso;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.entities.Professor;
import com.cefet.ensina_mais.entities.Turma;
import com.cefet.ensina_mais.entities.Usuario;
import com.cefet.ensina_mais.repositories.AvaliacaoRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;
import com.cefet.ensina_mais.repositories.ProfessorRepository;
import com.cefet.ensina_mais.repositories.TurmaRepository;
import com.cefet.ensina_mais.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Buscar todos
    public List<ProfessorDTO> findAll() {
        List<Professor> listaProfessor = professorRepository.findAll();
        return listaProfessor.stream().map(ProfessorDTO::new).toList();
    }

    // Buscar por ID
    public ProfessorDTO findById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + id));
        return new ProfessorDTO(professor);
    }

    // Inserir Professor
    public ProfessorDTO insert(ProfessorDTO professorDTO) {
        // Verifica se o nome não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(professorDTO.getNome()))
            throw new IllegalArgumentException("O nome do professor não pode ser vazio.");

        // Verifica se o cpf não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(professorDTO.getCpf()))
            throw new IllegalArgumentException("O CPF do professor não pode ser vazio.");

        // Verifica se a titulação não é nula ou vazia (Campo Obrigatório)
        if (!StringUtils.hasText(professorDTO.getTitulacao()))
            throw new IllegalArgumentException("A ttulação do professor não pode ser vazia.");

        // Verifica se o cpf já existe (Regra de Negócio -> Cpf único)
        if (professorRepository.existsByCpf(professorDTO.getCpf()))
            throw new IllegalArgumentException("Já existe um professor com o CPF: " + professorDTO.getCpf());

        Usuario usuario = new Usuario();
        usuario.setLogin(professorDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(professorDTO.getNome()));
        usuario.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuario = usuarioRepository.save(usuario);

        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professorDTO.getCpf());
        professor.setEmail(professorDTO.getEmail());
        professor.setTitulacao(professorDTO.getTitulacao());
        professor = professorRepository.save(professor);
        return new ProfessorDTO(professor);
    }

    // Atualizar Professor
    public ProfessorDTO update(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + id));

        // Verifica se o DTO tem o campo CPF e se ele é diferente do cpf no banco
        if (StringUtils.hasText(professorDTO.getCpf()) && !professorDTO.getCpf().equals(professor.getCpf()))
            throw new IllegalArgumentException("Não é permitido alterar o CPF de um professor.");

        // Se o DTO tiver o campo nome, altera o nome
        if (StringUtils.hasText(professorDTO.getNome()))
            professor.setNome(professorDTO.getNome());

        // Se o DTO tiver o campo email, altera o email
        if (StringUtils.hasText(professorDTO.getEmail()))
            professor.setEmail(professorDTO.getEmail());

        // Se o DTO tiver o campo titulação, altera a titulação
        if (StringUtils.hasText(professorDTO.getTitulacao()))
            professor.setTitulacao(professorDTO.getTitulacao());

        professor = professorRepository.save(professor);
        return new ProfessorDTO(professor);
    }

    // Remover por ID
    @Transactional
    public void delete(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + id);
        }
        List<Turma> turmas = turmaRepository.findByProfessorId(id);
        for (Turma turma : turmas) {
            List<MatriculaTurma> matriculas = matriculaTurmaRepository.findByTurmaId(id);
            for (MatriculaTurma matriculaTurma : matriculas) {
                List<Nota> notas = notaRepository.findByMatriculaTurmaId(matriculaTurma.getId());
                notaRepository.deleteAll(notas);

                matriculaTurmaRepository.delete(matriculaTurma);
            }

            List<Avaliacao> avaliacoes = avaliacaoRepository.findByTurmaId(id);
            avaliacaoRepository.deleteAll(avaliacoes);

            turmaRepository.deleteById(turma.getId());
        }

        professorRepository.deleteById(id);
    }
}
