package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.AlunoDTO;
import com.cefet.ensina_mais.dto.AvaliacaoDTO;
import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.dto.ProfessorDTO;
import com.cefet.ensina_mais.dto.TurmaDTO;
import com.cefet.ensina_mais.entities.Aluno;
import com.cefet.ensina_mais.entities.Avaliacao;
import com.cefet.ensina_mais.entities.Matricula;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.NivelAcesso;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.entities.Professor;
import com.cefet.ensina_mais.entities.Turma;
import com.cefet.ensina_mais.entities.Usuario;
import com.cefet.ensina_mais.repositories.AlunoRepository;
import com.cefet.ensina_mais.repositories.AvaliacaoRepository;
import com.cefet.ensina_mais.repositories.MatriculaRepository;
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
    private AlunoRepository alunoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

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

    public ProfessorDTO findByUsuarioId(Long usuarioId) {
        Professor professor = professorRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID de usuário: " + usuarioId));
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

    // Buscar turmas de um professor
    public List<TurmaDTO> findTurmasByProfessorId(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        List<Turma> turmas = turmaRepository.findByProfessorId(professorId);
        return turmas.stream().map(TurmaDTO::new).toList();
    }

    // Buscar alunos de um professor (através das turmas e matrículas)
    public List<AlunoDTO> findAlunosByProfessorId(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        
        List<Turma> turmas = turmaRepository.findByProfessorId(professorId);
        List<Aluno> alunos = turmas.stream()
            .flatMap(turma -> matriculaTurmaRepository.findByTurmaId(turma.getId()).stream())
            .map(matriculaTurma -> matriculaTurma.getMatricula().getAluno())
            .distinct()
            .toList();
        
        return alunos.stream().map(AlunoDTO::new).toList();
    }

    // Buscar notas dos alunos de um professor
    public List<NotaDTO> findNotasByProfessorId(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        
        List<Turma> turmas = turmaRepository.findByProfessorId(professorId);
        List<Nota> notas = turmas.stream()
            .flatMap(turma -> matriculaTurmaRepository.findByTurmaId(turma.getId()).stream())
            .flatMap(matriculaTurma -> notaRepository.findByMatriculaTurmaId(matriculaTurma.getId()).stream())
            .toList();
        
        return notas.stream().map(NotaDTO::new).toList();
    }

    // Buscar avaliações de um professor
    public List<AvaliacaoDTO> findAvaliacoesByProfessorId(Long professorId) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        
        List<Turma> turmas = turmaRepository.findByProfessorId(professorId);
        List<Avaliacao> avaliacoes = turmas.stream()
            .flatMap(turma -> avaliacaoRepository.findByTurmaId(turma.getId()).stream())
            .toList();
        
        return avaliacoes.stream().map(AvaliacaoDTO::new).toList();
    }

    // Atualizar nota (só professores podem alterar notas)
    public NotaDTO updateNota(Long professorId, Long notaId, NotaDTO notaDTO) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        
        Nota nota = notaRepository.findById(notaId)
            .orElseThrow(() -> new EntityNotFoundException("Nota não encontrada com ID: " + notaId));
        
        // Verificar se a nota pertence a uma turma do professor
        Long turmaId = nota.getMatriculaTurma().getTurma().getId();
        Turma turma = turmaRepository.findById(turmaId)
            .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
        
        if (!turma.getProfessor().getId().equals(professorId)) {
            throw new IllegalArgumentException("Professor não tem permissão para alterar esta nota");
        }
        
        if (notaDTO.getNota() != null) {
            nota.setNota(notaDTO.getNota());
        }
        
        nota = notaRepository.save(nota);
        return new NotaDTO(nota);
    }

    // Criar avaliação
    public AvaliacaoDTO createAvaliacao(Long professorId, AvaliacaoDTO avaliacaoDTO) {
        if (!professorRepository.existsById(professorId)) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + professorId);
        }
        
        // Verificar se a turma pertence ao professor
        Turma turma = turmaRepository.findById(avaliacaoDTO.getIdTurma())
            .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada com ID: " + avaliacaoDTO.getIdTurma()));
        
        if (!turma.getProfessor().getId().equals(professorId)) {
            throw new IllegalArgumentException("Professor não tem permissão para criar avaliação nesta turma");
        }
        
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricao(avaliacaoDTO.getDescricao());
        avaliacao.setData(avaliacaoDTO.getData());
        avaliacao.setNotaMaxima(avaliacaoDTO.getNotaMaxima());
        avaliacao.setTurma(turma);
        
        avaliacao = avaliacaoRepository.save(avaliacao);
        return new AvaliacaoDTO(avaliacao);
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
