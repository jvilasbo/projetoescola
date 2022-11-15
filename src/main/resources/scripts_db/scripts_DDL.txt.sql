CREATE DATABASE escola;

use escola;

CREATE TABLE ALUNO (
matricula CHAR(8) NOT NULL PRIMARY KEY,
nome Varchar(200) NOT NULL,
idade INT,
genero CHAR(1),
nif CHAR(9) UNIQUE NOT NULL,
escolaridade VARCHAR(15)
);


CREATE TABLE DISCIPLINA (
id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
titulo Varchar(50) NOT NULL,
area Varchar(40) NOT NULL
);

CREATE TABLE PROFESSOR (
nif CHAR(9) NOT NULL PRIMARY KEY,
nome Varchar(200) NOT NULL,
idade INT,
genero CHAR(1),
tempo_ensino INT,
salario DECIMAL(9,2),
disciplina_id INT NOT NULL,
FOREIGN KEY(disciplina_id) REFERENCES DISCIPLINA (id)
);


CREATE TABLE AULA (
id int AUTO_INCREMENT PRIMARY KEY,
data_aula Date,
duracao DECIMAL(3,1),
valor DECIMAL(9,2),
professor_nif CHAR(9),
aluno_matricula CHAR(8),
FOREIGN KEY(professor_nif) REFERENCES PROFESSOR (nif),
FOREIGN KEY(aluno_matricula) REFERENCES ALUNO (matricula)
);
