package com.cefet.ensina_mais.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cefet.ensina_mais.entities.*;
import com.cefet.ensina_mais.repositories.*;

import java.sql.Date;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private MatriculaTurmaRepository matriculaTurmaRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se já existem dados
        if (usuarioRepository.count() == 0) {
            carregarDados();
            System.out.println("Dados de teste carregados com sucesso!");
        } else {
            System.out.println("Dados já existem no banco de dados.");
        }
    }

    private void carregarDados() {
        // 1. Criar Usuários
        Usuario admin = new Usuario();
        admin.setLogin("admin");
        admin.setSenha(passwordEncoder.encode("admin"));
        admin.setNivelAcesso(NivelAcesso.ADMIN);
        usuarioRepository.save(admin);

        Usuario usuarioTesla = new Usuario();
        usuarioTesla.setLogin("tesla");
        usuarioTesla.setSenha(passwordEncoder.encode("tesla"));
        usuarioTesla.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioTesla);

        Usuario usuarioEuler = new Usuario();
        usuarioEuler.setLogin("euler");
        usuarioEuler.setSenha(passwordEncoder.encode("euler"));
        usuarioEuler.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioEuler);

        Usuario usuarioEinstein = new Usuario();
        usuarioEinstein.setLogin("einstein");
        usuarioEinstein.setSenha(passwordEncoder.encode("einstein"));
        usuarioEinstein.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioEinstein);

        Usuario usuarioGeraldo = new Usuario();
        usuarioGeraldo.setLogin("geraldo");
        usuarioGeraldo.setSenha(passwordEncoder.encode("geraldo"));
        usuarioGeraldo.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioGeraldo);

        Usuario usuarioManual = new Usuario();
        usuarioManual.setLogin("manual");
        usuarioManual.setSenha(passwordEncoder.encode("manual"));
        usuarioManual.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioManual);

        Usuario usuarioRobson = new Usuario();
        usuarioRobson.setLogin("robson");
        usuarioRobson.setSenha(passwordEncoder.encode("robson"));
        usuarioRobson.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioRobson);

        // 2. Criar Alunos
        Aluno geraldo = new Aluno();
        geraldo.setNome("Geraldo");
        geraldo.setCpf("793.613.590-10");
        geraldo.setEmail("geraldo@gmail.com");
        geraldo.setDataNascimento(Date.valueOf("2008-09-14"));
        geraldo.setUsuario(usuarioGeraldo);
        alunoRepository.save(geraldo);

        Aluno robson = new Aluno();
        robson.setNome("Robson");
        robson.setCpf("444.852.110-96");
        robson.setEmail("robson@gmail.com");
        robson.setDataNascimento(Date.valueOf("2009-01-12"));
        robson.setUsuario(usuarioRobson);
        alunoRepository.save(robson);

        Aluno manual = new Aluno();
        manual.setNome("Manual");
        manual.setCpf("935.469.520-57");
        manual.setEmail("manual@gmail.com");
        manual.setDataNascimento(Date.valueOf("2008-12-10"));
        manual.setUsuario(usuarioManual);
        alunoRepository.save(manual);

        // 3. Criar Professores
        Professor tesla = new Professor();
        tesla.setNome("Tesla");
        tesla.setCpf("206.366.250-95");
        tesla.setEmail("tesla@gmail.com");
        tesla.setTitulacao("Mestre");
        tesla.setUsuario(usuarioTesla);
        professorRepository.save(tesla);

        Professor einstein = new Professor();
        einstein.setNome("Einstein");
        einstein.setCpf("292.060.350-70");
        einstein.setEmail("einstein@gmail.com");
        einstein.setTitulacao("Doutor");
        einstein.setUsuario(usuarioEinstein);
        professorRepository.save(einstein);

        Professor euler = new Professor();
        euler.setNome("Euler");
        euler.setCpf("680.718.410-72");
        euler.setEmail("euler@gmail.com");
        euler.setTitulacao("Doutor");
        euler.setUsuario(usuarioEuler);
        professorRepository.save(euler);

        // 4. Criar Disciplinas
        Disciplina geometria = new Disciplina();
        geometria.setNome("Geometria");
        disciplinaRepository.save(geometria);

        Disciplina algebra = new Disciplina();
        algebra.setNome("Álgebra");
        disciplinaRepository.save(algebra);

        Disciplina quimica = new Disciplina();
        quimica.setNome("Química");
        disciplinaRepository.save(quimica);

        // 5. Criar Matrículas
        Matricula matricula1 = new Matricula();
        matricula1.setNumero("20250001");
        matricula1.setData(Date.valueOf("2025-01-12"));
        matricula1.setAluno(geraldo);
        matriculaRepository.save(matricula1);

        Matricula matricula2 = new Matricula();
        matricula2.setNumero("20250002");
        matricula2.setData(Date.valueOf("2025-01-21"));
        matricula2.setAluno(robson);
        matriculaRepository.save(matricula2);

        Matricula matricula3 = new Matricula();
        matricula3.setNumero("20250003");
        matricula3.setData(Date.valueOf("2025-02-01"));
        matricula3.setAluno(manual);
        matriculaRepository.save(matricula3);

        // 6. Criar Turmas
        Turma turma1 = new Turma();
        turma1.setSemestre("2025.1");
        turma1.setVagas(40);
        turma1.setProfessor(tesla);
        turma1.setDisciplina(geometria);
        turmaRepository.save(turma1);

        Turma turma2 = new Turma();
        turma2.setSemestre("2025.1");
        turma2.setVagas(60);
        turma2.setProfessor(einstein);
        turma2.setDisciplina(algebra);
        turmaRepository.save(turma2);

        Turma turma3 = new Turma();
        turma3.setSemestre("2025.1");
        turma3.setVagas(25);
        turma3.setProfessor(euler);
        turma3.setDisciplina(quimica);
        turmaRepository.save(turma3);

        // 7. Criar Avaliações
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setData(Date.valueOf("2025-01-31"));
        avaliacao1.setDescricao("Avaliação 1");
        avaliacao1.setNotaMaxima(30.0);
        avaliacao1.setTurma(turma1);
        avaliacaoRepository.save(avaliacao1);

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setData(Date.valueOf("2025-03-10"));
        avaliacao2.setDescricao("Avaliação 2");
        avaliacao2.setNotaMaxima(35.0);
        avaliacao2.setTurma(turma1);
        avaliacaoRepository.save(avaliacao2);

        Avaliacao avaliacao3 = new Avaliacao();
        avaliacao3.setData(Date.valueOf("2025-02-12"));
        avaliacao3.setDescricao("Avaliação 1");
        avaliacao3.setNotaMaxima(40.0);
        avaliacao3.setTurma(turma2);
        avaliacaoRepository.save(avaliacao3);

        // 8. Criar MatriculaTurma (relaciona matrículas com turmas)
        MatriculaTurma mt1 = new MatriculaTurma();
        mt1.setSituacao(SituacaoMatricula.EM_ANDAMENTO); // 2 = Em Andamento
        mt1.setNotaFinal(null);
        mt1.setMatricula(matricula1);
        mt1.setTurma(turma1);
        matriculaTurmaRepository.save(mt1);

        MatriculaTurma mt2 = new MatriculaTurma();
        mt2.setSituacao(SituacaoMatricula.REPROVADO); // 0 = Reprovado
        mt2.setNotaFinal(55.0);
        mt2.setMatricula(matricula2);
        mt2.setTurma(turma2);
        matriculaTurmaRepository.save(mt2);

        MatriculaTurma mt3 = new MatriculaTurma();
        mt3.setSituacao(SituacaoMatricula.APROVADO); // 1 = Aprovado
        mt3.setNotaFinal(85.0);
        mt3.setMatricula(matricula3);
        mt3.setTurma(turma3);
        matriculaTurmaRepository.save(mt3);

        // 9. Criar Notas (relaciona avaliações com matrículas em turmas)
        Nota nota1 = new Nota();
        nota1.setNota(25.0);
        nota1.setAvaliacao(avaliacao1);
        nota1.setMatriculaTurma(mt1);
        notaRepository.save(nota1);

        Nota nota2 = new Nota();
        nota2.setNota(20.0);
        nota2.setAvaliacao(avaliacao2);
        nota2.setMatriculaTurma(mt1);
        notaRepository.save(nota2);

        Nota nota3 = new Nota();
        nota3.setNota(15.0);
        nota3.setAvaliacao(avaliacao3);
        nota3.setMatriculaTurma(mt2);
        notaRepository.save(nota3);
    }
}
