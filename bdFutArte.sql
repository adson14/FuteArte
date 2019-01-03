create database bdfutarte;

use bdFutArte;

create table Funcionario (
	cod_Func INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (30) NOT NULL,
    idade INT NOT NULL,
    rg VARCHAR (30) NOT NULL,
    cpf VARCHAR (30) NOT NULL,
	endereco VARCHAR (60) NOT NULL,
    cargo VARCHAR (15) NOT NULL
);


create table Turma (
	cod_Turma INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (30) NOT NULL,
    FUNCIONARIO_cod_Func INT,
    FOREIGN KEY (FUNCIONARIO_cod_Func) REFERENCES Funcionario (cod_Func)
);


create table Aluno (
	cod_Aluno INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (30) NOT NULL,
    idade INT NOT NULL,
    rg VARCHAR (30) NOT NULL,
    cpf VARCHAR (30) NOT NULL,
    endereco VARCHAR (60) NOT NULL,
    statusMedico VARCHAR (10) NOT NULL,
    FUNCIONARIO_cod_Func INT,
    FOREIGN KEY (FUNCIONARIO_cod_Func) REFERENCES Funcionario (cod_Func),
    TURMA_cod_Turma INT,
    FOREIGN KEY (TURMA_cod_Turma) REFERENCES Turma (cod_Turma) 
);

create table Material (
	cod_Material INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR (30) NOT NULL,
    FUNCIONARIO_cod_Func INT,
    FOREIGN KEY (FUNCIONARIO_cod_Func) REFERENCES Funcionario (cod_Func)
);


create table Mensalidade (
	cod_Mensalidade INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	valor DECIMAL NOT NULL,
    dataEmisao VARCHAR (12) NOT NULL,
    dataVencimento VARCHAR (12) NOT NULL,
    descricao VARCHAR (50) NOT NULL,
    codigoBarras VARCHAR (15) NOT NULL,
	ALUNO_cod_Aluno INT,
    FUNCIONARIO_cod_Func INT,
	FOREIGN KEY (ALUNO_cod_Aluno) REFERENCES Aluno (cod_Aluno),
    FOREIGN KEY (FUNCIONARIO_cod_Func) REFERENCES Funcionario (cod_Func)
);


create table Jogo (
	cod_Jogo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dia VARCHAR (15),
    hora VARCHAR (12),
    FUNCIONARIO_cod_Func INT,
    FOREIGN KEY (FUNCIONARIO_cod_Func) REFERENCES Funcionario (cod_Func)
);


create table Presenca (
	cod_Presenca INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	statusPresenca VARCHAR (8) NOT NULL,
	TURMA_cod_Turma INT,
    FOREIGN KEY (TURMA_cod_Turma) REFERENCES Turma (cod_Turma),
    JOGO_cod_Jogo INT,
    FOREIGN KEY (JOGO_cod_Jogo) REFERENCES Jogo (cod_Jogo) 
);




