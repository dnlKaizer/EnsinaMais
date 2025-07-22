-- Alunos
INSERT INTO tb_aluno(nome, cpf, email, data_nascimento) VALUES ('Geraldo', '793.613.590-10', 'geraldo@gmail.com', '2008-09-14');
INSERT INTO tb_aluno(nome, cpf, email, data_nascimento) VALUES ('Robson', '444.852.110-96', 'robson@gmail.com', '2009-01-12');
INSERT INTO tb_aluno(nome, cpf, email, data_nascimento) VALUES ('Manual', '935.469.520-57', 'manual@gmail.com', '2008-12-10');

-- Professores
INSERT INTO tb_professor(nome, cpf, email, titulacao) VALUES ('Tesla', '206.366.250-95', 'tesla@gmail.com', 'Mestre');
INSERT INTO tb_professor(nome, cpf, email, titulacao) VALUES ('Einstein', '292.060.350-70', 'einstein@gmail.com', 'Doutor');
INSERT INTO tb_professor(nome, cpf, email, titulacao) VALUES ('Euler', '680.718.410-72', 'euler@gmail.com', 'Doutor');

-- Disciplinas
INSERT INTO tb_disciplina(nome) VALUES ('Geometria');
INSERT INTO tb_disciplina(nome) VALUES ('Álgebra');
INSERT INTO tb_disciplina(nome) VALUES ('Química');

-- Matrículas
INSERT INTO tb_matricula(numero, data, aluno_id) VALUES (20250001, '2025-01-12', 1);
INSERT INTO tb_matricula(numero, data, aluno_id) VALUES (20250002, '2025-01-21', 2);
INSERT INTO tb_matricula(numero, data, aluno_id) VALUES (20250003, '2025-02-01', 3);

-- Turmas
INSERT INTO tb_turma(semestre, vagas, professor_id, disciplina_id) VALUES ('2025.1', 40, 1, 1);
INSERT INTO tb_turma(semestre, vagas, professor_id, disciplina_id) VALUES ('2025.1', 60, 2, 2);
INSERT INTO tb_turma(semestre, vagas, professor_id, disciplina_id) VALUES ('2025.1', 25, 3, 3);

-- Avaliações
INSERT INTO tb_avaliacao(data, descricao, valor, turma_id) VALUES ('2025-01-31', 'Avaliação 1', 30, 1)
INSERT INTO tb_avaliacao(data, descricao, valor, turma_id) VALUES ('2025-03-10', 'Avaliação 2', 35, 1)
INSERT INTO tb_avaliacao(data, descricao, valor, turma_id) VALUES ('2025-02-12', 'Avaliação 1', 40, 2)

-- MatriculaTurma (relaciona matrículas com turmas)
-- Situação: 0=Reprovado, 1=Aprovado, 2=Em Andamento
INSERT INTO tb_matricula_turma(situacao, nota_final, matricula_id, turma_id) VALUES (2, NULL, 1, 1);
INSERT INTO tb_matricula_turma(situacao, nota_final, matricula_id, turma_id) VALUES (0, 55, 2, 2);
INSERT INTO tb_matricula_turma(situacao, nota_final, matricula_id, turma_id) VALUES (1, 85, 3, 3);

-- Notas (relaciona avaliações com matrículas em turmas)
INSERT INTO tb_nota(nota, avaliacao_id, matricula_turma_id) VALUES (25, 1, 1);
INSERT INTO tb_nota(nota, avaliacao_id, matricula_turma_id) VALUES (20, 2, 1);
INSERT INTO tb_nota(nota, avaliacao_id, matricula_turma_id) VALUES (15, 3, 2);

