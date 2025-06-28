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