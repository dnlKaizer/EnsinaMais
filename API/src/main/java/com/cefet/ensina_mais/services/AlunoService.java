package com.cefet.ensina_mais.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cefet.ensina_mais.dto.AlunoDTO;
import com.cefet.ensina_mais.dto.MatriculaDTO;
import com.cefet.ensina_mais.dto.MatriculaTurmaDTO;
import com.cefet.ensina_mais.dto.NotaDTO;
import com.cefet.ensina_mais.entities.Aluno;
import com.cefet.ensina_mais.entities.Matricula;
import com.cefet.ensina_mais.entities.MatriculaTurma;
import com.cefet.ensina_mais.entities.NivelAcesso;
import com.cefet.ensina_mais.entities.Nota;
import com.cefet.ensina_mais.entities.Usuario;
import com.cefet.ensina_mais.repositories.AlunoRepository;
import com.cefet.ensina_mais.repositories.MatriculaRepository;
import com.cefet.ensina_mais.repositories.MatriculaTurmaRepository;
import com.cefet.ensina_mais.repositories.NotaRepository;
import com.cefet.ensina_mais.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Buscar todos
    public List<AlunoDTO> findAll() {
        List<Aluno> listaAluno = alunoRepository.findAll();
        return listaAluno.stream().map(AlunoDTO::new).toList();
    }

    // Buscar por ID
    public AlunoDTO findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
        return new AlunoDTO(aluno);
    }

    public AlunoDTO findByUsuarioId(Long usuarioId) {
        Aluno aluno = alunoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID de usuário: " + usuarioId));
        return new AlunoDTO(aluno);
    }

    // Inserir Aluno
    public AlunoDTO insert(AlunoDTO alunoDTO) {
        // Verifica se o nome não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(alunoDTO.getNome()))
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");

        // Verifica se o cpf não é nulo ou vazio (Campo Obrigatório)
        if (!StringUtils.hasText(alunoDTO.getCpf()))
            throw new IllegalArgumentException("O CPF do aluno não pode ser vazio.");

        // Verifica se a data não é nulo ou vazia (Campo Obrigatório)
        if (alunoDTO.getDataNascimento() == null || !StringUtils.hasText(alunoDTO.getDataNascimento().toString()))
            throw new IllegalArgumentException("A data de nascimento do aluno não pode ser vazia.");

        // Verifica se o cpf já existe (Regra de Negócio -> Cpf único)
        if (alunoRepository.existsByCpf(alunoDTO.getCpf()))
            throw new IllegalArgumentException("Já existe um aluno com o CPF: " + alunoDTO.getCpf());

        Usuario usuario = new Usuario();
        usuario.setLogin(alunoDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(alunoDTO.getNome()));
        usuario.setNivelAcesso(NivelAcesso.ALUNO);
        usuario = usuarioRepository.save(usuario);

        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setEmail(alunoDTO.getEmail());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setUsuario(usuario);
        aluno = alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    // Atualizar Aluno
    public AlunoDTO update(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        // Verifica se o DTO tem o campo CPF e se ele é diferente do cpf no banco
        if (StringUtils.hasText(alunoDTO.getCpf()) && !alunoDTO.getCpf().equals(aluno.getCpf()))
            throw new IllegalArgumentException("Não é permitido alterar o CPF do aluno.");

        // Se o DTO tiver o campo nome, altera o nome
        if (StringUtils.hasText(alunoDTO.getNome()))
            aluno.setNome(alunoDTO.getNome());

        // Se o DTO tiver o campo email, altera o email
        if (StringUtils.hasText(alunoDTO.getEmail()))
            aluno.setEmail(alunoDTO.getEmail());

        // Se o DTO tiver o campo data, altera a data
        if (StringUtils.hasText(alunoDTO.getDataNascimento().toString()))
            aluno.setDataNascimento(alunoDTO.getDataNascimento());

        aluno = alunoRepository.save(aluno);
        return new AlunoDTO(aluno);
    }

    // Remover por ID
    @Transactional
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + id);
        }
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(id);
        for (Matricula matricula : matriculas) {
            List<MatriculaTurma> matriculaTurmas = matriculaTurmaRepository.findByMatriculaId(matricula.getId());
            for (MatriculaTurma matriculaTurma : matriculaTurmas) {
                List<Nota> notas = notaRepository.findByMatriculaTurmaId(matriculaTurma.getId());
                notaRepository.deleteAll(notas);
                
                matriculaTurmaRepository.delete(matriculaTurma);
            }
            matriculaRepository.deleteById(matricula.getId());
        }
        
        alunoRepository.deleteById(id);
    }

    // Buscar matrículas de um aluno
    public List<MatriculaDTO> findMatriculasByAlunoId(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId);
        }
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);
        return matriculas.stream().map(MatriculaDTO::new).toList();
    }

    // Buscar matrículas-turmas de um aluno (através das matrículas)
    public List<MatriculaTurmaDTO> findMatriculaTurmasByAlunoId(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId);
        }
        
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);
        List<MatriculaTurma> matriculaTurmas = matriculas.stream()
            .flatMap(matricula -> matriculaTurmaRepository.findByMatriculaId(matricula.getId()).stream())
            .toList();
        
        return matriculaTurmas.stream().map(MatriculaTurmaDTO::new).toList();
    }

    // Buscar notas de um aluno (através das matrículas-turmas)
    public List<NotaDTO> findNotasByAlunoId(Long alunoId) {
        if (!alunoRepository.existsById(alunoId)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + alunoId);
        }
        
        List<Matricula> matriculas = matriculaRepository.findByAlunoId(alunoId);
        List<MatriculaTurma> matriculaTurmas = matriculas.stream()
            .flatMap(matricula -> matriculaTurmaRepository.findByMatriculaId(matricula.getId()).stream())
            .toList();
        
        List<Nota> notas = matriculaTurmas.stream()
            .flatMap(matriculaTurma -> notaRepository.findByMatriculaTurmaId(matriculaTurma.getId()).stream())
            .toList();
        
        return notas.stream().map(NotaDTO::new).toList();
    }

    // Buscar matrículas-turmas de uma matrícula específica
    public List<MatriculaTurmaDTO> findMatriculaTurmasByMatriculaId(Long matriculaId) {
        List<MatriculaTurma> matriculaTurmas = matriculaTurmaRepository.findByMatriculaId(matriculaId);
        return matriculaTurmas.stream().map(MatriculaTurmaDTO::new).toList();
    }

    // Buscar notas de uma matrícula-turma específica
    public List<NotaDTO> findNotasByMatriculaTurmaId(Long matriculaTurmaId) {
        List<Nota> notas = notaRepository.findByMatriculaTurmaId(matriculaTurmaId);
        return notas.stream().map(NotaDTO::new).toList();
    }

}
