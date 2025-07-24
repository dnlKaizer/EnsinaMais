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

        Usuario usuarioNewton = new Usuario();
        usuarioNewton.setLogin("newton");
        usuarioNewton.setSenha(passwordEncoder.encode("newton"));
        usuarioNewton.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioNewton);

        Usuario usuarioDarwin = new Usuario();
        usuarioDarwin.setLogin("darwin");
        usuarioDarwin.setSenha(passwordEncoder.encode("darwin"));
        usuarioDarwin.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioDarwin);

        Usuario usuarioMarie = new Usuario();
        usuarioMarie.setLogin("marie");
        usuarioMarie.setSenha(passwordEncoder.encode("marie"));
        usuarioMarie.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioMarie);

        Usuario usuarioGalileo = new Usuario();
        usuarioGalileo.setLogin("galileo");
        usuarioGalileo.setSenha(passwordEncoder.encode("galileo"));
        usuarioGalileo.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioGalileo);

        Usuario usuarioKepler = new Usuario();
        usuarioKepler.setLogin("kepler");
        usuarioKepler.setSenha(passwordEncoder.encode("kepler"));
        usuarioKepler.setNivelAcesso(NivelAcesso.PROFESSOR);
        usuarioRepository.save(usuarioKepler);

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

        Usuario usuarioAna = new Usuario();
        usuarioAna.setLogin("ana");
        usuarioAna.setSenha(passwordEncoder.encode("ana"));
        usuarioAna.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioAna);

        Usuario usuarioCarlos = new Usuario();
        usuarioCarlos.setLogin("carlos");
        usuarioCarlos.setSenha(passwordEncoder.encode("carlos"));
        usuarioCarlos.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioCarlos);

        Usuario usuarioMaria = new Usuario();
        usuarioMaria.setLogin("maria");
        usuarioMaria.setSenha(passwordEncoder.encode("maria"));
        usuarioMaria.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioMaria);

        Usuario usuarioJoao = new Usuario();
        usuarioJoao.setLogin("joao");
        usuarioJoao.setSenha(passwordEncoder.encode("joao"));
        usuarioJoao.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioJoao);

        Usuario usuarioLucia = new Usuario();
        usuarioLucia.setLogin("lucia");
        usuarioLucia.setSenha(passwordEncoder.encode("lucia"));
        usuarioLucia.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioLucia);

        Usuario usuarioPedro = new Usuario();
        usuarioPedro.setLogin("pedro");
        usuarioPedro.setSenha(passwordEncoder.encode("pedro"));
        usuarioPedro.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioPedro);

        Usuario usuarioJulia = new Usuario();
        usuarioJulia.setLogin("julia");
        usuarioJulia.setSenha(passwordEncoder.encode("julia"));
        usuarioJulia.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioJulia);

        Usuario usuarioRafael = new Usuario();
        usuarioRafael.setLogin("rafael");
        usuarioRafael.setSenha(passwordEncoder.encode("rafael"));
        usuarioRafael.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioRafael);

        Usuario usuarioLaura = new Usuario();
        usuarioLaura.setLogin("laura");
        usuarioLaura.setSenha(passwordEncoder.encode("laura"));
        usuarioLaura.setNivelAcesso(NivelAcesso.ALUNO);
        usuarioRepository.save(usuarioLaura);

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

        Aluno ana = new Aluno();
        ana.setNome("Ana Silva");
        ana.setCpf("123.456.789-01");
        ana.setEmail("ana@gmail.com");
        ana.setDataNascimento(Date.valueOf("2007-03-15"));
        ana.setUsuario(usuarioAna);
        alunoRepository.save(ana);

        Aluno carlos = new Aluno();
        carlos.setNome("Carlos Santos");
        carlos.setCpf("234.567.890-12");
        carlos.setEmail("carlos@gmail.com");
        carlos.setDataNascimento(Date.valueOf("2008-07-22"));
        carlos.setUsuario(usuarioCarlos);
        alunoRepository.save(carlos);

        Aluno maria = new Aluno();
        maria.setNome("Maria Oliveira");
        maria.setCpf("345.678.901-23");
        maria.setEmail("maria@gmail.com");
        maria.setDataNascimento(Date.valueOf("2007-11-08"));
        maria.setUsuario(usuarioMaria);
        alunoRepository.save(maria);

        Aluno joao = new Aluno();
        joao.setNome("João Costa");
        joao.setCpf("456.789.012-34");
        joao.setEmail("joao@gmail.com");
        joao.setDataNascimento(Date.valueOf("2008-05-18"));
        joao.setUsuario(usuarioJoao);
        alunoRepository.save(joao);

        Aluno lucia = new Aluno();
        lucia.setNome("Lucia Ferreira");
        lucia.setCpf("567.890.123-45");
        lucia.setEmail("lucia@gmail.com");
        lucia.setDataNascimento(Date.valueOf("2007-09-30"));
        lucia.setUsuario(usuarioLucia);
        alunoRepository.save(lucia);

        Aluno pedro = new Aluno();
        pedro.setNome("Pedro Lima");
        pedro.setCpf("678.901.234-56");
        pedro.setEmail("pedro@gmail.com");
        pedro.setDataNascimento(Date.valueOf("2008-02-14"));
        pedro.setUsuario(usuarioPedro);
        alunoRepository.save(pedro);

        Aluno julia = new Aluno();
        julia.setNome("Julia Rocha");
        julia.setCpf("789.012.345-67");
        julia.setEmail("julia@gmail.com");
        julia.setDataNascimento(Date.valueOf("2007-12-25"));
        julia.setUsuario(usuarioJulia);
        alunoRepository.save(julia);

        Aluno rafael = new Aluno();
        rafael.setNome("Rafael Souza");
        rafael.setCpf("890.123.456-78");
        rafael.setEmail("rafael@gmail.com");
        rafael.setDataNascimento(Date.valueOf("2008-08-10"));
        rafael.setUsuario(usuarioRafael);
        alunoRepository.save(rafael);

        Aluno laura = new Aluno();
        laura.setNome("Laura Alves");
        laura.setCpf("901.234.567-89");
        laura.setEmail("laura@gmail.com");
        laura.setDataNascimento(Date.valueOf("2007-06-03"));
        laura.setUsuario(usuarioLaura);
        alunoRepository.save(laura);

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

        Professor newton = new Professor();
        newton.setNome("Isaac Newton");
        newton.setCpf("111.222.333-44");
        newton.setEmail("newton@gmail.com");
        newton.setTitulacao("Doutor");
        newton.setUsuario(usuarioNewton);
        professorRepository.save(newton);

        Professor darwin = new Professor();
        darwin.setNome("Charles Darwin");
        darwin.setCpf("222.333.444-55");
        darwin.setEmail("darwin@gmail.com");
        darwin.setTitulacao("Doutor");
        darwin.setUsuario(usuarioDarwin);
        professorRepository.save(darwin);

        Professor marie = new Professor();
        marie.setNome("Marie Curie");
        marie.setCpf("333.444.555-66");
        marie.setEmail("marie@gmail.com");
        marie.setTitulacao("Doutora");
        marie.setUsuario(usuarioMarie);
        professorRepository.save(marie);

        Professor galileo = new Professor();
        galileo.setNome("Galileo Galilei");
        galileo.setCpf("444.555.666-77");
        galileo.setEmail("galileo@gmail.com");
        galileo.setTitulacao("Mestre");
        galileo.setUsuario(usuarioGalileo);
        professorRepository.save(galileo);

        Professor kepler = new Professor();
        kepler.setNome("Johannes Kepler");
        kepler.setCpf("555.666.777-88");
        kepler.setEmail("kepler@gmail.com");
        kepler.setTitulacao("Doutor");
        kepler.setUsuario(usuarioKepler);
        professorRepository.save(kepler);

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

        Disciplina fisica = new Disciplina();
        fisica.setNome("Física");
        disciplinaRepository.save(fisica);

        Disciplina biologia = new Disciplina();
        biologia.setNome("Biologia");
        disciplinaRepository.save(biologia);

        Disciplina historia = new Disciplina();
        historia.setNome("História");
        disciplinaRepository.save(historia);

        Disciplina geografia = new Disciplina();
        geografia.setNome("Geografia");
        disciplinaRepository.save(geografia);

        Disciplina astronomia = new Disciplina();
        astronomia.setNome("Astronomia");
        disciplinaRepository.save(astronomia);

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

        Matricula matricula4 = new Matricula();
        matricula4.setNumero("20250004");
        matricula4.setData(Date.valueOf("2025-02-05"));
        matricula4.setAluno(ana);
        matriculaRepository.save(matricula4);

        Matricula matricula5 = new Matricula();
        matricula5.setNumero("20250005");
        matricula5.setData(Date.valueOf("2025-02-10"));
        matricula5.setAluno(carlos);
        matriculaRepository.save(matricula5);

        Matricula matricula6 = new Matricula();
        matricula6.setNumero("20250006");
        matricula6.setData(Date.valueOf("2025-02-15"));
        matricula6.setAluno(maria);
        matriculaRepository.save(matricula6);

        Matricula matricula7 = new Matricula();
        matricula7.setNumero("20250007");
        matricula7.setData(Date.valueOf("2025-02-20"));
        matricula7.setAluno(joao);
        matriculaRepository.save(matricula7);

        Matricula matricula8 = new Matricula();
        matricula8.setNumero("20250008");
        matricula8.setData(Date.valueOf("2025-02-25"));
        matricula8.setAluno(lucia);
        matriculaRepository.save(matricula8);

        Matricula matricula9 = new Matricula();
        matricula9.setNumero("20250009");
        matricula9.setData(Date.valueOf("2025-03-01"));
        matricula9.setAluno(pedro);
        matriculaRepository.save(matricula9);

        Matricula matricula10 = new Matricula();
        matricula10.setNumero("20250010");
        matricula10.setData(Date.valueOf("2025-03-05"));
        matricula10.setAluno(julia);
        matriculaRepository.save(matricula10);

        Matricula matricula11 = new Matricula();
        matricula11.setNumero("20250011");
        matricula11.setData(Date.valueOf("2025-03-10"));
        matricula11.setAluno(rafael);
        matriculaRepository.save(matricula11);

        Matricula matricula12 = new Matricula();
        matricula12.setNumero("20250012");
        matricula12.setData(Date.valueOf("2025-03-15"));
        matricula12.setAluno(laura);
        matriculaRepository.save(matricula12);

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

        Turma turma4 = new Turma();
        turma4.setSemestre("2025.1");
        turma4.setVagas(35);
        turma4.setProfessor(newton);
        turma4.setDisciplina(fisica);
        turmaRepository.save(turma4);

        Turma turma5 = new Turma();
        turma5.setSemestre("2025.1");
        turma5.setVagas(30);
        turma5.setProfessor(darwin);
        turma5.setDisciplina(biologia);
        turmaRepository.save(turma5);

        Turma turma6 = new Turma();
        turma6.setSemestre("2025.1");
        turma6.setVagas(40);
        turma6.setProfessor(marie);
        turma6.setDisciplina(historia);
        turmaRepository.save(turma6);

        Turma turma7 = new Turma();
        turma7.setSemestre("2025.1");
        turma7.setVagas(28);
        turma7.setProfessor(galileo);
        turma7.setDisciplina(geografia);
        turmaRepository.save(turma7);

        Turma turma8 = new Turma();
        turma8.setSemestre("2025.1");
        turma8.setVagas(20);
        turma8.setProfessor(kepler);
        turma8.setDisciplina(astronomia);
        turmaRepository.save(turma8);

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

        Avaliacao avaliacao4 = new Avaliacao();
        avaliacao4.setData(Date.valueOf("2025-02-20"));
        avaliacao4.setDescricao("Prova de Química");
        avaliacao4.setNotaMaxima(50.0);
        avaliacao4.setTurma(turma3);
        avaliacaoRepository.save(avaliacao4);

        Avaliacao avaliacao5 = new Avaliacao();
        avaliacao5.setData(Date.valueOf("2025-03-01"));
        avaliacao5.setDescricao("Teste de Física");
        avaliacao5.setNotaMaxima(30.0);
        avaliacao5.setTurma(turma4);
        avaliacaoRepository.save(avaliacao5);

        // 8. Criar MatriculaTurma (relaciona matrículas com turmas)
        MatriculaTurma mt1 = new MatriculaTurma();
        mt1.setSituacao(SituacaoMatricula.EM_ANDAMENTO); // 2 = Em Andamento
        mt1.setNotaFinal(null);
        mt1.setMatricula(matricula1);
        mt1.setTurma(turma1);
        matriculaTurmaRepository.save(mt1);

        MatriculaTurma mt2 = new MatriculaTurma();
        mt2.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt2.setNotaFinal(null);
        mt2.setMatricula(matricula2);
        mt2.setTurma(turma2);
        matriculaTurmaRepository.save(mt2);

        MatriculaTurma mt3 = new MatriculaTurma();
        mt3.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt3.setNotaFinal(null);
        mt3.setMatricula(matricula3);
        mt3.setTurma(turma3);
        matriculaTurmaRepository.save(mt3);

        // Mais matrículas em turmas para os novos alunos
        MatriculaTurma mt4 = new MatriculaTurma();
        mt4.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt4.setNotaFinal(null);
        mt4.setMatricula(matricula4);
        mt4.setTurma(turma1);
        matriculaTurmaRepository.save(mt4);

        MatriculaTurma mt5 = new MatriculaTurma();
        mt5.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt5.setNotaFinal(null);
        mt5.setMatricula(matricula5);
        mt5.setTurma(turma2);
        matriculaTurmaRepository.save(mt5);

        MatriculaTurma mt6 = new MatriculaTurma();
        mt6.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt6.setNotaFinal(null);
        mt6.setMatricula(matricula6);
        mt6.setTurma(turma4);
        matriculaTurmaRepository.save(mt6);

        MatriculaTurma mt7 = new MatriculaTurma();
        mt7.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt7.setNotaFinal(null);
        mt7.setMatricula(matricula7);
        mt7.setTurma(turma5);
        matriculaTurmaRepository.save(mt7);

        MatriculaTurma mt8 = new MatriculaTurma();
        mt8.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt8.setNotaFinal(null);
        mt8.setMatricula(matricula8);
        mt8.setTurma(turma6);
        matriculaTurmaRepository.save(mt8);

        MatriculaTurma mt9 = new MatriculaTurma();
        mt9.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt9.setNotaFinal(null);
        mt9.setMatricula(matricula9);
        mt9.setTurma(turma7);
        matriculaTurmaRepository.save(mt9);

        MatriculaTurma mt10 = new MatriculaTurma();
        mt10.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt10.setNotaFinal(null);
        mt10.setMatricula(matricula10);
        mt10.setTurma(turma8);
        matriculaTurmaRepository.save(mt10);

        MatriculaTurma mt11 = new MatriculaTurma();
        mt11.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt11.setNotaFinal(null);
        mt11.setMatricula(matricula11);
        mt11.setTurma(turma3);
        matriculaTurmaRepository.save(mt11);

        MatriculaTurma mt12 = new MatriculaTurma();
        mt12.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt12.setNotaFinal(null);
        mt12.setMatricula(matricula12);
        mt12.setTurma(turma1);
        matriculaTurmaRepository.save(mt12);

        // Alguns alunos em múltiplas turmas
        MatriculaTurma mt13 = new MatriculaTurma();
        mt13.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt13.setNotaFinal(null);
        mt13.setMatricula(matricula1);
        mt13.setTurma(turma2);
        matriculaTurmaRepository.save(mt13);

        MatriculaTurma mt14 = new MatriculaTurma();
        mt14.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt14.setNotaFinal(null);
        mt14.setMatricula(matricula4);
        mt14.setTurma(turma5);
        matriculaTurmaRepository.save(mt14);

        MatriculaTurma mt15 = new MatriculaTurma();
        mt15.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt15.setNotaFinal(null);
        mt15.setMatricula(matricula7);
        mt15.setTurma(turma8);
        matriculaTurmaRepository.save(mt15);

        // Adicionar mais turmas para cada aluno (mínimo 3 turmas por aluno)
        
        // Geraldo (matricula1) - já tem turma1 e turma2, adicionar turma3
        MatriculaTurma mt16 = new MatriculaTurma();
        mt16.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt16.setNotaFinal(null);
        mt16.setMatricula(matricula1);
        mt16.setTurma(turma3);
        matriculaTurmaRepository.save(mt16);

        // Robson (matricula2) - já tem turma2, adicionar turma4 e turma6
        MatriculaTurma mt17 = new MatriculaTurma();
        mt17.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt17.setNotaFinal(null);
        mt17.setMatricula(matricula2);
        mt17.setTurma(turma4);
        matriculaTurmaRepository.save(mt17);

        MatriculaTurma mt18 = new MatriculaTurma();
        mt18.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt18.setNotaFinal(null);
        mt18.setMatricula(matricula2);
        mt18.setTurma(turma6);
        matriculaTurmaRepository.save(mt18);

        // Manual (matricula3) - já tem turma3, adicionar turma5 e turma7
        MatriculaTurma mt19 = new MatriculaTurma();
        mt19.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt19.setNotaFinal(null);
        mt19.setMatricula(matricula3);
        mt19.setTurma(turma5);
        matriculaTurmaRepository.save(mt19);

        MatriculaTurma mt20 = new MatriculaTurma();
        mt20.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt20.setNotaFinal(null);
        mt20.setMatricula(matricula3);
        mt20.setTurma(turma7);
        matriculaTurmaRepository.save(mt20);

        // Ana (matricula4) - já tem turma1 e turma5, adicionar turma8
        MatriculaTurma mt21 = new MatriculaTurma();
        mt21.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt21.setNotaFinal(null);
        mt21.setMatricula(matricula4);
        mt21.setTurma(turma8);
        matriculaTurmaRepository.save(mt21);

        // Carlos (matricula5) - já tem turma2, adicionar turma3 e turma4
        MatriculaTurma mt22 = new MatriculaTurma();
        mt22.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt22.setNotaFinal(null);
        mt22.setMatricula(matricula5);
        mt22.setTurma(turma3);
        matriculaTurmaRepository.save(mt22);

        MatriculaTurma mt23 = new MatriculaTurma();
        mt23.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt23.setNotaFinal(null);
        mt23.setMatricula(matricula5);
        mt23.setTurma(turma4);
        matriculaTurmaRepository.save(mt23);

        // Maria (matricula6) - já tem turma4, adicionar turma1 e turma7
        MatriculaTurma mt24 = new MatriculaTurma();
        mt24.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt24.setNotaFinal(null);
        mt24.setMatricula(matricula6);
        mt24.setTurma(turma1);
        matriculaTurmaRepository.save(mt24);

        MatriculaTurma mt25 = new MatriculaTurma();
        mt25.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt25.setNotaFinal(null);
        mt25.setMatricula(matricula6);
        mt25.setTurma(turma7);
        matriculaTurmaRepository.save(mt25);

        // João (matricula7) - já tem turma5 e turma8, adicionar turma2
        MatriculaTurma mt26 = new MatriculaTurma();
        mt26.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt26.setNotaFinal(null);
        mt26.setMatricula(matricula7);
        mt26.setTurma(turma2);
        matriculaTurmaRepository.save(mt26);

        // Lucia (matricula8) - já tem turma6, adicionar turma3 e turma8
        MatriculaTurma mt27 = new MatriculaTurma();
        mt27.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt27.setNotaFinal(null);
        mt27.setMatricula(matricula8);
        mt27.setTurma(turma3);
        matriculaTurmaRepository.save(mt27);

        MatriculaTurma mt28 = new MatriculaTurma();
        mt28.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt28.setNotaFinal(null);
        mt28.setMatricula(matricula8);
        mt28.setTurma(turma8);
        matriculaTurmaRepository.save(mt28);

        // Pedro (matricula9) - já tem turma7, adicionar turma1 e turma4
        MatriculaTurma mt29 = new MatriculaTurma();
        mt29.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt29.setNotaFinal(null);
        mt29.setMatricula(matricula9);
        mt29.setTurma(turma1);
        matriculaTurmaRepository.save(mt29);

        MatriculaTurma mt30 = new MatriculaTurma();
        mt30.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt30.setNotaFinal(null);
        mt30.setMatricula(matricula9);
        mt30.setTurma(turma4);
        matriculaTurmaRepository.save(mt30);

        // Julia (matricula10) - já tem turma8, adicionar turma2 e turma5
        MatriculaTurma mt31 = new MatriculaTurma();
        mt31.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt31.setNotaFinal(null);
        mt31.setMatricula(matricula10);
        mt31.setTurma(turma2);
        matriculaTurmaRepository.save(mt31);

        MatriculaTurma mt32 = new MatriculaTurma();
        mt32.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt32.setNotaFinal(null);
        mt32.setMatricula(matricula10);
        mt32.setTurma(turma5);
        matriculaTurmaRepository.save(mt32);

        // Rafael (matricula11) - já tem turma3, adicionar turma6 e turma1
        MatriculaTurma mt33 = new MatriculaTurma();
        mt33.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt33.setNotaFinal(null);
        mt33.setMatricula(matricula11);
        mt33.setTurma(turma6);
        matriculaTurmaRepository.save(mt33);

        MatriculaTurma mt34 = new MatriculaTurma();
        mt34.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt34.setNotaFinal(null);
        mt34.setMatricula(matricula11);
        mt34.setTurma(turma1);
        matriculaTurmaRepository.save(mt34);

        // Laura (matricula12) - já tem turma1, adicionar turma4 e turma7
        MatriculaTurma mt35 = new MatriculaTurma();
        mt35.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt35.setNotaFinal(null);
        mt35.setMatricula(matricula12);
        mt35.setTurma(turma4);
        matriculaTurmaRepository.save(mt35);

        MatriculaTurma mt36 = new MatriculaTurma();
        mt36.setSituacao(SituacaoMatricula.EM_ANDAMENTO);
        mt36.setNotaFinal(null);
        mt36.setMatricula(matricula12);
        mt36.setTurma(turma7);
        matriculaTurmaRepository.save(mt36);

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

        // Notas adicionais para as novas avaliações
        Nota nota4 = new Nota();
        nota4.setNota(42.0);
        nota4.setAvaliacao(avaliacao4);
        nota4.setMatriculaTurma(mt3);
        notaRepository.save(nota4);

        Nota nota5 = new Nota();
        nota5.setNota(28.0);
        nota5.setAvaliacao(avaliacao5);
        nota5.setMatriculaTurma(mt6);
        notaRepository.save(nota5);

        Nota nota6 = new Nota();
        nota6.setNota(22.0);
        nota6.setAvaliacao(avaliacao1);
        nota6.setMatriculaTurma(mt4);
        notaRepository.save(nota6);

        Nota nota7 = new Nota();
        nota7.setNota(35.0);
        nota7.setAvaliacao(avaliacao3);
        nota7.setMatriculaTurma(mt5);
        notaRepository.save(nota7);

        Nota nota8 = new Nota();
        nota8.setNota(30.0);
        nota8.setAvaliacao(avaliacao2);
        nota8.setMatriculaTurma(mt12);
        notaRepository.save(nota8);
    }
}
