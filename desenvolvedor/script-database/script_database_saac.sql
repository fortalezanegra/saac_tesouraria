/*####################################################################################################################
#########################    S C R I P T   D A T A B A S E   S A A C   S E C R E T A R I A   #########################
####################################################################################################################*/

/* Removendo banco se existir */
DROP DATABASE IF EXISTS saac_dev;

/* Criando Banco de Dados */
CREATE DATABASE saac_dev 
  DEFAULT CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

/* Selecionando Banco */
USE saac_dev;

/*========================================================*/
/*                     TABELA SITUACAO                    */
/*========================================================*/
CREATE TABLE Situacao (
  situacaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  contexto VARCHAR(50) NOT NULL,
  ativo BOOL DEFAULT 1,
  ordem INT DEFAULT 0,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE(descricao, contexto)
) ENGINE=InnoDB;

/*========================================================*/
/*                       TABELA PAIS                      */
/*========================================================*/
CREATE TABLE Pais (
  paisId INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  sigla VARCHAR(10) DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                      TABELA ESTADO                     */
/*========================================================*/
CREATE TABLE Estado (
  estadoId INT PRIMARY KEY AUTO_INCREMENT,
  paisId INT DEFAULT NULL,
  nome VARCHAR(50) NOT NULL,
  sigla VARCHAR(10) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (paisId, nome),

  CONSTRAINT FK_ESTADO_PAIS FOREIGN KEY (paisId) 
  REFERENCES Pais(paisId) ON DELETE CASCADE

) ENGINE=InnoDB;

/*========================================================*/
/*                     TABELA CIDADE                      */
/*========================================================*/
CREATE TABLE Cidade (
  cidadeId INT PRIMARY KEY AUTO_INCREMENT,
  estadoId INT DEFAULT NULL,
  nome VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_CIDADE_ESTADO FOREIGN KEY (estadoId) 
    REFERENCES Estado(estadoId) ON DELETE CASCADE
) ENGINE=InnoDB;

/*========================================================*/
/*                      TABELA SETOR                      */
/*========================================================*/
CREATE TABLE Setor (
  setorId INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  sigla VARCHAR(10) DEFAULT NULL,
  descricao VARCHAR(100) DEFAULT NULL,
  cidadeId INT DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (cidadeId, nome),

  CONSTRAINT FK_SETOR_CIDADE FOREIGN KEY (cidadeId) 
    REFERENCES Cidade(cidadeId) ON DELETE SET NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                   TABELA CONGREGACAO                   */
/*========================================================*/
CREATE TABLE Congregacao (
  congregacaoId INT PRIMARY KEY AUTO_INCREMENT,
  setorId INT DEFAULT NULL,
  nome VARCHAR(100) NOT NULL,
  sigla VARCHAR(50) DEFAULT NULL,
  endereco VARCHAR(255),
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (setorId, nome, sigla),

  CONSTRAINT FK_CONGREGACAO_SETOR FOREIGN KEY (setorId)
    REFERENCES Setor(setorId) ON DELETE SET NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                 TABELA CARGO MINISTERIAL               */
/*========================================================*/
CREATE TABLE CargoMinisterial (
  cargoMinisterialId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao)

) ENGINE=InnoDB;

/*========================================================*/
/*                        TABELA SEXO                     */
/*========================================================*/
CREATE TABLE Sexo (
  sexoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(10) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao)

) ENGINE=InnoDB;

/*========================================================*/
/*                    TABELA ESTADO CIVIL                 */
/*========================================================*/
CREATE TABLE EstadoCivil (
  estadoCivilId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao)

) ENGINE=InnoDB;

/*========================================================*/
/*                     TABELA FORMACAO                    */
/*========================================================*/
CREATE TABLE Formacao (
  formacaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao)

) ENGINE=InnoDB;

/*========================================================*/
/*                     TABELA PROFISSAO                   */
/*========================================================*/
CREATE TABLE Profissao (
  profissaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL

) ENGINE=InnoDB;

/*========================================================*/
/*                       TABELA MEMBRO                    */
/*========================================================*/
CREATE TABLE Membro (
  membroId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoId INT DEFAULT NULL,
  cargoMinisterialId INT DEFAULT NULL,
  dataBatismo DATE DEFAULT NULL,
  nome VARCHAR(100) NOT NULL,
  sexoId INT DEFAULT NULL,
  dataNascimento DATE DEFAULT NULL,
  telefone VARCHAR(20),
  email VARCHAR(50),
  rg VARCHAR(15) DEFAULT NULL,
  cpf VARCHAR(15) DEFAULT NULL,
  estadoCivilId INT DEFAULT NULL,
  conjuge VARCHAR(100),
  certidao VARCHAR(100),
  tipoSanguineo VARCHAR(10),
  pai VARCHAR(100),
  mae VARCHAR(100),
  formacaoId INT DEFAULT NULL,
  profissaoId INT DEFAULT NULL,
  foto VARCHAR(300),
  situacaoId INT DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  
  CONSTRAINT FK_MEMBRO_CONGREGACAO FOREIGN KEY (congregacaoId)
    REFERENCES Congregacao(congregacaoId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_CARGO_MINISTERIAL FOREIGN KEY (cargoMinisterialId)
    REFERENCES CargoMinisterial(cargoMinisterialId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_SEXO FOREIGN KEY (sexoId)
    REFERENCES Sexo(sexoId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_ESTADO_CIVIL FOREIGN KEY (estadoCivilId)
    REFERENCES EstadoCivil(estadoCivilId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_FORMACAO FOREIGN KEY (formacaoId)
    REFERENCES Formacao(formacaoId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_PROFISSAO FOREIGN KEY (profissaoId)
    REFERENCES Profissao(profissaoId) ON DELETE SET NULL,
    
  CONSTRAINT FK_MEMBRO_SITUACAO FOREIGN KEY (situacaoId)
    REFERENCES Situacao(situacaoId) ON DELETE SET NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                      TABELA ENDERECO                   */
/*========================================================*/
CREATE TABLE Endereco (
  enderecoId INT PRIMARY KEY AUTO_INCREMENT,
  membroId INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  numero INT,
  complemento VARCHAR(255),
  bairro VARCHAR(255),
  cep VARCHAR(50),
  cidadeId INT DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  CONSTRAINT FK_ENDERECO_MEMBRO FOREIGN KEY (membroId)
    REFERENCES Membro(membroId) ON DELETE CASCADE,
  CONSTRAINT FK_ENDERECO_CIDADE FOREIGN KEY (cidadeId)
    REFERENCES Cidade(cidadeId) ON DELETE SET NULL
) ENGINE=InnoDB;

/**###################################################################*/
/*########################## I N S E R T S ###########################*/
/*####################################################################*/

/*Tabela Situacao*/
INSERT INTO Situacao (descricao, contexto, ativo, ordem) VALUES
-- MEMBRO
('Ativo',                          'MEMBRO', 1, 1),
('Inativo',                        'MEMBRO', 1, 2),
('Falecido',                       'MEMBRO', 1, 3),
('Aguardando Batismo',             'MEMBRO', 1, 4),
('Aguardando Reuniao Ministerial', 'MEMBRO', 1, 5),

-- REUNIAO_EVENTO
('Pendente',  'REUNIAO_EVENTO', 1, 1),
('Concluido', 'REUNIAO_EVENTO', 1, 2),
('Cancelado', 'REUNIAO_EVENTO', 1, 3),

-- ATA_REUNIAO
('Aguardando Redação',    'ATA_REUNIAO', 1, 1),
('Em Analise',            'ATA_REUNIAO', 1, 2),
('Aguardando Aprovação',  'ATA_REUNIAO', 1, 3),
('Aprovada',              'ATA_REUNIAO', 1, 4),
('Consolidada',           'ATA_REUNIAO', 1, 5),

-- BATISMO_EVENTO
('Pendente',  'BATISMO_EVENTO', 1, 1),
('Concluido', 'BATISMO_EVENTO', 1, 2),
('Cancelado', 'BATISMO_EVENTO', 1, 3),

-- BATISMO_CANDIDATO
('Pendente',       'BATISMO_CANDIDATO', 1, 1),
('Concluido',      'BATISMO_CANDIDATO', 1, 2),
('Cancelado',      'BATISMO_CANDIDATO', 1, 3),
('Nao Compareceu', 'BATISMO_CANDIDATO', 1, 4),

-- SOLICITACAO_RECEBIMENTO_BATISMO
('Aguardando Reuniao', 'SOLICITACAO_RECEBIMENTO_BATISMO', 1, 1),
('Aprovada',           'SOLICITACAO_RECEBIMENTO_BATISMO', 1, 2),
('Reprovada',          'SOLICITACAO_RECEBIMENTO_BATISMO', 1, 3),
('Cancelada',          'SOLICITACAO_RECEBIMENTO_BATISMO', 1, 4),
('Consolidada',        'SOLICITACAO_RECEBIMENTO_BATISMO', 1, 5),

-- SOLICITACAO_UNIAO
('Aguardando Reuniao', 'SOLICITACAO_UNIAO', 1, 1),
('Aprovada',           'SOLICITACAO_UNIAO', 1, 2),
('Reprovada',          'SOLICITACAO_UNIAO', 1, 3),
('Cancelada',          'SOLICITACAO_UNIAO', 1, 4),
('Consolidada',        'SOLICITACAO_UNIAO', 1, 5),

-- SOLICITACAO_TRANSFERENCIA
('Aguardando Reuniao', 'SOLICITACAO_TRANSFERENCIA', 1, 1),
('Aprovada',           'SOLICITACAO_TRANSFERENCIA', 1, 2),
('Reprovada',          'SOLICITACAO_TRANSFERENCIA', 1, 3),
('Cancelada',          'SOLICITACAO_TRANSFERENCIA', 1, 4),
('Consolidada',        'SOLICITACAO_TRANSFERENCIA', 1, 5),

-- SOLICITACAO_DESLIGAMENTO
('Aguardando Reuniao', 'SOLICITACAO_DESLIGAMENTO', 1, 1),
('Aprovada',           'SOLICITACAO_DESLIGAMENTO', 1, 2),
('Reprovada',          'SOLICITACAO_DESLIGAMENTO', 1, 3),
('Cancelada',          'SOLICITACAO_DESLIGAMENTO', 1, 4),
('Consolidada',        'SOLICITACAO_DESLIGAMENTO', 1, 5),

-- EVENTO
('Pendente',  'EVENTO', 1, 1),
('Concluido', 'EVENTO', 1, 2),
('Cancelado', 'EVENTO', 1, 3),

-- FINANCEIRO
('Aberto',                'FINANCEIRO', 1, 1),
('Reaberto',              'FINANCEIRO', 1, 2),
('Aguardando Fechamento', 'FINANCEIRO', 1, 3),
('Pre-Fechamento',        'FINANCEIRO', 1, 4),
('Fechado',               'FINANCEIRO', 1, 5),
('Bloqueado',             'FINANCEIRO', 1, 6),

-- DOCUMENTO
('Aguardando Envio',     'DOCUMENTO', 1, 1),
('Enviado',              'DOCUMENTO', 1, 2),
('Aguardando Revisao',   'DOCUMENTO', 1, 3),
('Revisado',             'DOCUMENTO', 1, 4),
('Aguardando Aprovacao', 'DOCUMENTO', 1, 5),
('Aprovado',             'DOCUMENTO', 1, 6),
('Aguardando Liberacao', 'DOCUMENTO', 1, 7),
('Liberado',             'DOCUMENTO', 1, 8);

/*Tabela Setor*/
INSERT INTO Setor(Nome, Sigla, Descricao, CidadeId) VALUES
('Setor 1', 'ADBC-SET01', 'Grupo de Congregações', null),
('Setor 2', 'ADBC-SET02', 'Grupo de Congregações', null),
('Setor 3', 'ADBC-SET03', 'Grupo de Congregações', null),
('Setor 4', 'ADBC-SET04', 'Grupo de Congregações', null),
('Setor 5', 'ADBC-SET05', 'Grupo de Congregações', null),
('Setor 6', 'ADBC-SET06', 'Grupo de Congregações', null);

/*Tabela Congregacao*/
INSERT INTO Congregacao(setorId, nome, sigla, endereco)VALUES
((SELECT setorId FROM Setor WHERE nome ='Setor 1'), 'Catedral da Fonte', 'CAF', 'Rua Moacir do Amaral, 767 Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 1'), 'Rosamélia', 'RSA', 'Rua Walter Zacarias, 33 Rosamélia Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 1'), 'Laranjeiras', 'LRJ', 'Rua Camilo Stucki, 202 Recanto das Laranjeiras Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 1'), 'Colibri', 'CLB', 'Rua Frederico Decresci, 339 Colibri Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 1'), 'Novo Cosmopolis', 'NCM', 'Rua Paulo de Azevedo Filho, 397 Novo Cosmopolis Cosmopolis-SP'),

((SELECT setorId FROM Setor WHERE nome ='Setor 2'), 'Parque Esther 1', 'PQE1', 'Rua Paulo Leivas Macalão, 101 Parque Esther 1 Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 2'), 'Parque Esther 2', 'PQR2', 'Rua Antonio Bordin, 145 Pq. Esther 2 Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 2'), 'Bader José AUN', 'BDR', 'Rua Adolpho Neumann, 02 Bader José AUN Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 2'), 'Jd. Eldorado', 'ELD', 'Rua Alberto Stuki, 328 Jd. Eldorado Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 2'), 'Souza Queiroz', 'SZQ', 'Rua ? Souza Queiroz Cosmopolis-SP'),

((SELECT setorId FROM Setor WHERE nome ='Setor 3'), 'Andorinhas', 'AND', 'Rua Luiz V. de Camões, 550 Andorinhas Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 3'), 'Jd. de Lourdes', 'JDL', 'Rua José Luis Bernardo da Silva,82 Cosmopolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 3'), 'Nosso Teto', 'NST', 'Rua Bortolo Caron, 37 Cosmopolis-SP'),

((SELECT setorId FROM Setor WHERE nome ='Setor 4'), 'Uirapuru', 'URP', 'Rua Tomaz Antonio Gonzaga, 371 Uirapuru Cosmopolis-SP'),

((SELECT setorId FROM Setor WHERE nome ='Setor 5'), 'Groot', 'GRT', 'Rua ? Holambra-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 5'), 'Imigrantes', 'IMT', 'Rua ? Holambra-SP'),

((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Americo de Campos', 'AMC', 'Rua ? Americo de Campos-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Ouroeste', 'OUR', 'Rua ? Ouroeste-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Riolândia', 'RIL', 'Rua ? Riolândia-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Santa Albertina', 'SAL', 'Rua ? SantaAlbertina-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Mesópolis', 'MSP', 'Rua ? Mesópolis-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Populina', 'PPL', 'Rua ? Populina-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Nipoã', 'NPA', 'Rua ? Nipoã-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Monte Aprazivel', 'MAP', 'Rua ? Monte Aprazivel-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Cosmorama', 'CMR', 'Rua ? Cosmorama-SP'),
((SELECT setorId FROM Setor WHERE nome ='Setor 6'), 'Cardoso', 'CRD', 'Rua ? Cardoso-SP');

/*Tabela EstadoCivil*/
INSERT INTO EstadoCivil(Descricao) VALUES
('Solteiro(a)'),
('Casado(a)'),
('Divorciado(a)'),
('Viúvo(a)');

/*Tabela CargoMinisterial */
INSERT INTO CargoMinisterial(Descricao) VALUES
('Congregado(a)'),
('Membro'),
('Auxiliar de Trabalho'),
('Diacono'),
('Diaconisa'),
('Missionária'),
('Presbitero'),
('Evangelista'),
('Pastor(a)'),
('Bispo(a)'),
('Contribuinte'),
('Candidato(a) Batismo'),
('Candidato(a) União');

/*Tabela Sexo*/
INSERT INTO Sexo(Descricao) VALUES
('Masculino'),
('Feminino');

/*Tabela Formacao*/
INSERT INTO Formacao(Descricao) VALUES
('Ensino Fundamental Incompleto'),
('Ensino Fundamental Completo'),
('Ensino Medio Incompleto'),
('Ensino Medio Completo'),
('Ensino Superior Incompleto'),
('Ensino Superior Completo'),
('Pós Graduado'),
('Mestrado'),
('Doutorado');

/*Tabela Membro*/
INSERT INTO Membro(congregacaoId, cargoMinisterialId, dataBatismo, nome, sexoId, dataNascimento, telefone, email, rg, cpf,
estadoCivilId, conjuge, certidao, tipoSanguineo, pai, mae, formacaoId, profissaoId, foto, situacaoId) VALUES
(7, 12, null, 'João Gonçalves Pena Junior', 1, '1976-03-10', '(19) 9 7108-8224', 'jdev1976@gmail.com', null, null, 2, 
'Sheila Bandeira da Costa Gonçalves Pena', null, null, 'João Gonçalves Pena', 'Valdelice Souza Pena', 5, 3, 'sem-foto.jpg' ,4),

(1, 5, null, 'Valdir', 1, '1957-08-05', '(19) 9 9969-9522', 'valdir@gmail.com', null, null, 2,
'Esposa', null, null, 'Pai', 'Mãe', 6, 3, 'sem-foto.jpg', 1),

(7, 2, null , 'Sheila Bandeira da Costa Gonçalves Pena', 2, '1977-08-05', '(19) 9 9969-9522', 'sheilabco05@hotmail.com', null, null, 2,
'João Gonçalves Pena Junior', null, null, 'Carlos Frederico Bandeira da Costa', 'Vera Lucia Rizzi', 3, 7, 'sem-foto.jpg', 1),

(1, 2, null, 'Raquel', 2, '1980-09-10', '(19) 9 9876-5432', 'sheila@gmail.com', null, null, 2,
'Esposo', null, null, 'Pai', 'Mãe', 6, 3, 'sem-foto.jpg', 1);


/*#############################################################################*/
/*############## S C R I P T   R E U N I A O   M I N I S T E R I A L ##########*/
/*#############################################################################*/

/*========================================================*/
/*                  TABELA TIPO REUNIAO                   */
/*========================================================*/
CREATE TABLE TipoReuniao(
  tipoReuniaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) UNIQUE NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*               TABELA REUNIAO MINISTEIRAL               */
/*========================================================*/
CREATE TABLE ReuniaoMinisterial(
  reuniaoMinisterialId INT PRIMARY KEY AUTO_INCREMENT,
  tipoReuniaoId INT NOT NULL,
  congregacaoId INT NOT NULL,
  dataReuniao DATE NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_REUNIAO_MINISTERIAL_X_TIPO_REUNIAO
  FOREIGN KEY(tipoReuniaoId) REFERENCES TipoReuniao(tipoReuniaoId),

  CONSTRAINT FK_REUNIAO_MINISTERIAL_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_REUNIAO_MINISTERIAL_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;


/*========================================================*/
/*                   TABELA ATA REUNIAO                   */
/*========================================================*/
CREATE TABLE AtaReuniao(
  ataReuniaoId INT PRIMARY KEY AUTO_INCREMENT,
  reuniaoMinisterialId INT NOT NULL,
  urlAta VARCHAR(255) DEFAULT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_ATA_REUNIAO_X_REUNIAO_MINISTERIAL
  FOREIGN KEY(reuniaoMinisterialId) REFERENCES ReuniaoMinisterial(reuniaoMinisterialId),

  CONSTRAINT FK_ATA_REUNIAO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                       TIPO SOLICITACAO                     */
/*========================================================*/
CREATE TABLE TipoSolicitacao (
  tipoSolicitacaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) UNIQUE NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL

) ENGINE=InnoDB;

/*========================================================*/
/*                     DECISAO MINISTERIAL                */
/*========================================================*/
CREATE TABLE DecisaoMinisterial (
  decisaoId INT PRIMARY KEY AUTO_INCREMENT,
  reuniaoMinisterialId INT NOT NULL,
  tipoSolicitacaoId INT NOT NULL,

  solicitacaoId INT NOT NULL COMMENT
'FK polimorfica: tabela definida por tipoSolicitacaoId',

  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (tipoSolicitacaoId, solicitacaoId),

  CONSTRAINT FK_DECISAO_X_REUNIAO_MINISTERIAL
  FOREIGN KEY (reuniaoMinisterialId) REFERENCES ReuniaoMinisterial(reuniaoMinisterialId),

  CONSTRAINT FK_DECISAO_X_TIPO_SOLICITACAO
  FOREIGN KEY (tipoSolicitacaoId) REFERENCES TipoSolicitacao(tipoSolicitacaoId),

  CONSTRAINT FK_DECISAO_X_SITUACAO
  FOREIGN KEY (situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                      TABELA BATISMO                    */
/*========================================================*/
CREATE TABLE Batismo (
  batismoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoId INT NOT NULL,
  dataBatismo DATE NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_BATISMO_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_BATISMO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                TABELA CANDIDATO BATISMO                */
/*========================================================*/
CREATE TABLE CandidatoBatismo (
  candidatoBatismoId INT PRIMARY KEY AUTO_INCREMENT,
  batismoId INT NOT NULL,
  congregacaoId INT NOT NULL,
  membroId INT NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE(batismoId, membroId),

  CONSTRAINT FK_CANDIDATO_BATISMO_X_BATISMO
  FOREIGN KEY(batismoId) REFERENCES Batismo(batismoId),

  CONSTRAINT FK_CANDIDATO_BATISMO_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_CANDIDATO_BATISMO_X_MEMBRO
  FOREIGN KEY(membroId) REFERENCES Membro(membroId),
  
  CONSTRAINT FK_CANDIDATO_BATISMO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*      TABELA SOLICITACAO RECEBIMENTO BATISMO            */
/*========================================================*/
CREATE TABLE SolicitacaoRecebimentoBatismo (
  solicitacaoRecebimentoBatismoId INT PRIMARY KEY AUTO_INCREMENT,
  batismoId INT NOT NULL,
  membroId INT NOT NULL,
  historico TEXT,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE(batismoId, membroId),

  CONSTRAINT FK_SOLIC_RECEBIMENTO_X_BATISMO
  FOREIGN KEY (batismoId) REFERENCES Batismo(batismoId),

  CONSTRAINT FK_SOLIC_RECEBIMENTO_X_MEMBRO
  FOREIGN KEY (membroId) REFERENCES Membro(membroId),

  CONSTRAINT FK_SOLIC_RECEBIMENTO_X_SITUACAO
  FOREIGN KEY (situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                TABELA SOLICITACAO UNIAO                */
/*========================================================*/
CREATE TABLE SolicitacaoUniao (
  solicitacaoUniaoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoSolicitanteId INT NOT NULL,
  igrejaOrigem VARCHAR(100) NOT NULL,
  cargoIgrejaOrigemId INT DEFAULT NULL,
  cargoSolicitadoId INT NOT NULL,
  membroId INT NOT NULL,
  historico TEXT,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_SOLIC_UNIAO_CONGREGACAO
  FOREIGN KEY (congregacaoSolicitanteId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_SOLIC_UNIAO_MEMBRO
  FOREIGN KEY (membroId) REFERENCES Membro(membroId),

  CONSTRAINT FK_SOLIC_UNIAO_CARGO_ORIGEM
  FOREIGN KEY (cargoIgrejaOrigemId) REFERENCES CargoMinisterial(cargoMinisterialId),

  CONSTRAINT FK_SOLIC_UNIAO_CARGO_SOLICITADO
  FOREIGN KEY (cargoSolicitadoId) REFERENCES CargoMinisterial(cargoMinisterialId),

  CONSTRAINT FK_SOLIC_UNIAO_SITUACAO
  FOREIGN KEY (situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*           TABELA SOLICITACAO TRASFERENCIA              */
/*========================================================*/
CREATE TABLE SolicitacaoTransferencia (
  solicitacaoTransferenciaId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoSolicitanteId INT NOT NULL,
  congregacaoOrigemId INT NOT NULL,
  congregacaoDestinoId INT NOT NULL,
  membroId INT NOT NULL,
  historico TEXT,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_SOLIC_TRANSF_CONGREGACAO_SOLICITANTE
  FOREIGN KEY (congregacaoSolicitanteId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_SOLIC_TRANSF_CONGREGACAO_ORIGEM
  FOREIGN KEY (congregacaoOrigemId) REFERENCES Congregacao(congregacaoId),    

  CONSTRAINT FK_SOLIC_TRANSF_CONGREGACAO_DESTINO
  FOREIGN KEY (congregacaoDestinoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_SOLIC_TRANSF_MEMBRO
  FOREIGN KEY (membroId) REFERENCES Membro(membroId),

  CONSTRAINT FK_SOLIC_TRANSF_SITUACAO
  FOREIGN KEY (situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;


/*========================================================*/
/*               TABELA MOTIVO DESLIGAMENTO               */
/*========================================================*/
CREATE TABLE MotivoDesligamento (
  motivoDesligamentoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) UNIQUE NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*           TABELA SOLICITACAO DESLIGAMENTO              */
/*========================================================*/
CREATE TABLE SolicitacaoDesligamento (
  solicitacaoDesligamentoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoSolicitanteId INT NOT NULL,
  motivoDesligamentoId INT NOT NULL,
  membroId INT NOT NULL,
  historico TEXT,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_SOLIC_DESLIG_CONGREGACAO
  FOREIGN KEY (congregacaoSolicitanteId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_SOLIC_DESLIG_MEMBRO
  FOREIGN KEY (membroId) REFERENCES Membro(membroId),

  CONSTRAINT FK_SOLIC_DESLIG_MOTIVO
  FOREIGN KEY (motivoDesligamentoId) REFERENCES MotivoDesligamento(motivoDesligamentoId),

  CONSTRAINT FK_SOLIC_DESLIG_SITUACAO
  FOREIGN KEY (situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                     TABELA HISTORICO                   */
/*========================================================*/
CREATE TABLE Historico (
  historicoId INT PRIMARY KEY AUTO_INCREMENT,
  membroId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_HISTORICO_MEMBRO
  FOREIGN KEY (membroId) REFERENCES Membro(membroId) ON DELETE CASCADE

) ENGINE=InnoDB;

/*========================================================*/
/*                  TIPO HISTORICO                        */
/*========================================================*/
CREATE TABLE TipoHistorico (
  tipoHistoricoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL UNIQUE,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                  ITEM HISTORICO                        */
/*========================================================*/
CREATE TABLE ItemHistorico (
  itemHistoricoId INT PRIMARY KEY AUTO_INCREMENT,
  historicoId INT NOT NULL,
  tipoHistoricoId INT NOT NULL,
  descricao TEXT NOT NULL,
  dataHistorico DATETIME DEFAULT CURRENT_TIMESTAMP,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_ITEM_HISTORICO_HISTORICO
  FOREIGN KEY (historicoId) REFERENCES Historico(historicoId) ON DELETE CASCADE,

  CONSTRAINT FK_ITEM_HISTORICO_TIPO
  FOREIGN KEY (tipoHistoricoId) REFERENCES TipoHistorico(tipoHistoricoId) ON DELETE CASCADE

) ENGINE=InnoDB;

/**###################################################################*/
/*########################## I N S E R T S ###########################*/
/*####################################################################*/

/* TIPO REUNIAO */
INSERT INTO TipoReuniao(descricao) VALUES
('Reunião de Dirigentes'),
('Reunião de Obreiros');

/* REUNIAO MINISTERIAL */
INSERT INTO ReuniaoMinisterial (
    tipoReuniaoId,
    congregacaoId,
    dataReuniao,
    situacaoId
)
SELECT
    1,
    1,
    '2026-01-05',
    s.situacaoId
FROM Situacao s
WHERE s.contexto = 'REUNIAO_EVENTO'
  AND s.descricao = 'Pendente';

/* BATISMO */
INSERT INTO Batismo(congregacaoId, dataBatismo, situacaoId)
SELECT 1, '2026-01-10', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'BATISMO_EVENTO'
AND s.descricao  = 'Pendente';

/* CANDIDATO BATISMO */
INSERT INTO CandidatoBatismo (
  batismoId,
  congregacaoId,
  membroId,
  situacaoId
)
SELECT
  b.batismoId,
  m.congregacaoId,
  m.membroId,
  s.situacaoId
FROM Batismo b
JOIN Membro m ON m.membroId = 1
JOIN Situacao s 
  ON s.contexto = 'BATISMO_CANDIDATO'
 AND s.descricao = 'Pendente'
WHERE b.dataBatismo = '2026-01-10';

/* TIPO HISTORICO */
INSERT INTO TipoHistorico(descricao) VALUES
('Conversão'),
('Batismo'),
('União'),
('Transferência'),
('Desligamento'),
('Administrativo'),
('Ministerial'),
('Disciplinar'),
('Confidencial');

/*#############################################################################*/
/*### S C R I P T   R E L A T O R I O   G E R A L   C O N G R E G A Ç Õ E S ###*/
/*#############################################################################*/

/*========================================================*/
/*                 TABELA TIPO EVENTO                     */
/*========================================================*/
CREATE TABLE TipoEvento (
  tipoEventoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL, 
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                      TABELA EVENTO                     */
/*========================================================*/
CREATE TABLE Evento (
  eventoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoId INT NOT NULL,
  tipoEventoId INT NOT NULL,
  descricao VARCHAR(255),
  participantes INT, 
  dataEvento DATE NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_EVENTO_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_EVENTO_X_TIPO_EVENTO
  FOREIGN KEY(tipoEventoId) REFERENCES TipoEvento(tipoEventoId),

  CONSTRAINT FK_EVENTO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;


/*========================================================*/
/*                      OCORRENCIA EVENTO                 */
/*========================================================*/
CREATE TABLE OcorrenciaEvento (
  ocorrenciaEventoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL, 
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                    TABELA ITEM EVENTO                  */
/*========================================================*/
CREATE TABLE ItemEvento (
  itemEventoId INT PRIMARY KEY AUTO_INCREMENT,
  eventoId INT NOT NULL,
  ocorrenciaEventoId INT DEFAULT NULL,
  quantidade INT DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_ITEM_EVENTO_X_EVENTO
  FOREIGN KEY(eventoId) REFERENCES Evento(eventoId),

  CONSTRAINT FK_ITEM_EVENTO_X_OCORRENCIA_EVENTO
  FOREIGN KEY(ocorrenciaEventoId) REFERENCES OcorrenciaEvento(ocorrenciaEventoId)

) ENGINE=InnoDB;

/**###################################################################*/
/*########################## I N S E R T S ###########################*/
/*####################################################################*/

/* TIPO EVENTO */
INSERT INTO TipoEvento(descricao)
VALUES
('Culto da Familia'),
('Culto em Casa'),
('Culto Ar Livre'),
('Evangelismo'),
('Escola Biblica Dominical');

/* EVENTO */
INSERT INTO Evento(congregacaoId, tipoEventoId, descricao, participantes, dataEvento, situacaoId)
SELECT
    (SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'),
    (SELECT tipoEventoId FROM TipoEvento WHERE descricao = 'Culto da Familia'),
    'Culto com a Familia', 40, '2026-01-04', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'EVENTO'
  AND s.descricao = 'Pendente';

INSERT INTO Evento(congregacaoId, tipoEventoId, descricao, participantes, dataEvento, situacaoId)
SELECT
    (SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'),
    (SELECT tipoEventoId FROM TipoEvento WHERE descricao = 'Culto em Casa'),
    'Culto Casa Irmã Tereza', 40, '2026-01-09', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'EVENTO'
  AND s.descricao = 'Pendente';

INSERT INTO Evento(congregacaoId, tipoEventoId, descricao, participantes, dataEvento, situacaoId)
SELECT
    (SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'),
    (SELECT tipoEventoId FROM TipoEvento WHERE descricao = 'Culto Ar Livre'),
    'Culto Quadra Esportes Pq. Esther 2', NULL, '2026-01-10', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'EVENTO'
  AND s.descricao = 'Pendente';

INSERT INTO Evento(congregacaoId, tipoEventoId, descricao, participantes, dataEvento, situacaoId)
SELECT
    (SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'),
    (SELECT tipoEventoId FROM TipoEvento WHERE descricao = 'Evangelismo'),
    'Evangelismo no Bairro Pq. Esther 2', NULL, '2026-01-11', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'EVENTO'
  AND s.descricao = 'Pendente';

INSERT INTO Evento(congregacaoId, tipoEventoId, descricao, participantes, dataEvento, situacaoId)
SELECT
    (SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'),
    (SELECT tipoEventoId FROM TipoEvento WHERE descricao = 'Escola Biblica Dominical'),
    'EBD PQE2', NULL, '2026-01-11', s.situacaoId
FROM Situacao s
WHERE s.contexto = 'EVENTO'
  AND s.descricao = 'Pendente';

/* OCORRENCIA EVENTO */
INSERT INTO OcorrenciaEvento(descricao)
VALUES
('Conversao'),
('Reconciliacao'),  
('Batismo Espirito Santo');

/* ITEM EVENTO */
INSERT INTO ItemEvento(eventoId, ocorrenciaEventoId, quantidade)
VALUES
(
    (SELECT eventoId FROM Evento WHERE descricao = 'Culto com a Familia'),
    (SELECT ocorrenciaEventoId FROM OcorrenciaEvento WHERE descricao = 'Conversao'),
    2
),
(
    (SELECT eventoId FROM Evento WHERE descricao = 'Culto Casa Irmã Tereza'),
    (SELECT ocorrenciaEventoId FROM OcorrenciaEvento WHERE descricao = 'Batismo Espirito Santo'),
    4
),
(
    (SELECT eventoId FROM Evento WHERE descricao = 'Culto Quadra Esportes Pq. Esther 2'),
    (SELECT ocorrenciaEventoId FROM OcorrenciaEvento WHERE descricao = 'Conversao'),
    5
),
(
    (SELECT eventoId FROM Evento WHERE descricao = 'Culto Quadra Esportes Pq. Esther 2'),
    (SELECT ocorrenciaEventoId FROM OcorrenciaEvento WHERE descricao = 'Reconciliacao'),
    3
),
(
    (SELECT eventoId FROM Evento WHERE descricao = 'Evangelismo no Bairro Pq. Esther 2'),
    (SELECT ocorrenciaEventoId FROM OcorrenciaEvento WHERE descricao = 'Conversao'),
    2
);


/*####################################################################################################################
#########################    S C R I P T   D A T A B A S E   S A A C   T E S O U R A R I A   #########################
####################################################################################################################*/

/*========================================================*/
/*                     CENTRO DE CUSTO                    */
/*========================================================*/
DROP TABLE IF EXISTS CentroCusto;
CREATE TABLE CentroCusto (
  centroCustoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoId INT DEFAULT NULL,
  tipoCentro ENUM('Filial', 'Matriz') NOT NULL,
  descricao VARCHAR(100) UNIQUE NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_CENTRO_CUSTO_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                       LIVRO CAIXA                      */
/*========================================================*/
DROP TABLE IF EXISTS LivroCaixa;
CREATE TABLE LivroCaixa (
  livroCaixaId INT PRIMARY KEY AUTO_INCREMENT,
  centroCustoId INT NOT NULL,
  periodo VARCHAR(7) NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE(centroCustoId, periodo),

CONSTRAINT FK_LIVRO_CAIXA_X_CENTRO_CUSTO
FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

CONSTRAINT FK_LIVRO_CAIXA_X_SITUACAO
FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                    TIPO MOVIMENTACAO                   */
/*========================================================*/
DROP TABLE IF EXISTS TipoMovimentacao;
CREATE TABLE TipoMovimentacao (
  tipoMovimentacaoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao)

) ENGINE=InnoDB;

/*========================================================*/
/*                        CATEGORIA                       */
/*========================================================*/
DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria (
  categoriaId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(255) NOT NULL,
  tipoMovimentacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,
  UNIQUE (descricao, tipoMovimentacaoId),


  CONSTRAINT FK_CATEGORIA_X_TIPO_MOVIMENTACAO
  FOREIGN KEY(tipoMovimentacaoId) REFERENCES TipoMovimentacao(tipoMovimentacaoId)

) ENGINE=InnoDB;


/*========================================================*/
/*                        CAMPANHA                        */
/*========================================================*/
DROP TABLE IF EXISTS Campanha;
CREATE TABLE Campanha (
  campanhaId INT PRIMARY KEY AUTO_INCREMENT,
  centroCustoId INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  metaValor DECIMAL(20,2) NOT NULL,
  dataInicio DATE NOT NULL,
  dataFim DATE DEFAULT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_CAMPANHA_X_CENTRO_CUSTO
  FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

  CONSTRAINT FK_CAMPANHA_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;


/*========================================================*/
/*                      TIPO PAGAMENTO                    */
/*========================================================*/
DROP TABLE IF EXISTS TipoPagamento;
CREATE TABLE TipoPagamento (
  tipoPagamentoId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL
) ENGINE=InnoDB;

/*========================================================*/
/*                        LANCAMENTO                      */
/*========================================================*/
DROP TABLE IF EXISTS Lancamento;
CREATE TABLE Lancamento (
  lancamentoId INT PRIMARY KEY AUTO_INCREMENT,
  livroCaixaId INT NOT NULL,
  centroCustoId INT NOT NULL,
  categoriaId INT NOT NULL,
  campanhaId INT DEFAULT NULL,
  descricao VARCHAR(255) DEFAULT NULL,
  valor DECIMAL(20,2) NOT NULL,
  tipoPagamentoId INT NOT NULL,
  membroId INT DEFAULT NULL,
  dataDeposito DATETIME NOT NULL,
  urlComprovante VARCHAR(255) DEFAULT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy INT DEFAULT NULL,

  CONSTRAINT FK_LANCAMENTO_X_LIVRO_CAIXA
  FOREIGN KEY(livroCaixaId) REFERENCES LivroCaixa(livroCaixaId),

  CONSTRAINT FK_LANCAMENTO_X_CENTRO_CUSTO
  FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

  CONSTRAINT FK_LANCAMENTO_X_CATEGORIA
  FOREIGN KEY(categoriaId) REFERENCES Categoria(categoriaId),

  CONSTRAINT FK_LANCAMENTO_X_CAMPANHA
  FOREIGN KEY(campanhaId) REFERENCES Campanha(campanhaId),

  CONSTRAINT FK_LANCAMENTO_X_TIPO_PAGAMENTO
  FOREIGN KEY(tipoPagamentoId) REFERENCES TipoPagamento(tipoPagamentoId),

  CONSTRAINT FK_LANCAMENTO_X_MEMBRO
  FOREIGN KEY(membroId) REFERENCES Membro(membroId)

) ENGINE=InnoDB;


/* INSERTS */

/* Centro de Custo */
INSERT INTO CentroCusto (congregacaoId, tipoCentro, descricao) VALUES
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Administração - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Secretaria - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Tesouraria - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Escola Biblica Dominical - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Missões - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'CIBE - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'UMEC - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Koinonia - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Kids - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Fundo de Reserva - Matriz'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Catedral da Fonte'), 'Matriz', 'Fundo de Campanha - Matriz'),

((SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'), 'Filial', 'Administração - Parque Esther 2'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'), 'Filial', 'Secretaria - Parque Esther 2'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'), 'Filial', 'Tesouraria - Parque Esther 2'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'), 'Filial', 'Fundo de Reserva - Parque Esther 2'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Parque Esther 2'), 'Filial', 'Fundo de Campanha - Parque Esther 2'),

((SELECT congregacaoId FROM Congregacao WHERE nome = 'Imigrantes'), 'Filial', 'Administração - Imigrantes'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Imigrantes'), 'Filial', 'Secretaria - Imigrantes'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Imigrantes'), 'Filial', 'Tesouraria - Imigrantes'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Imigrantes'), 'Filial', 'Fundo de Reserva - Imigrantes'),
((SELECT congregacaoId FROM Congregacao WHERE nome = 'Imigrantes'), 'Filial', 'Fundo de Campanha - Imigrantes');


/* TipoMovimentacao */
INSERT INTO TipoMovimentacao(descricao) VALUES
('Entrada'),
('Saida'),
('Transferencia');

/* Categoria */
INSERT INTO Categoria (descricao, TipoMovimentacaoId)
VALUES
('Contribuição',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Coleta',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Dizimo',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Oferta',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Oferta Missionaria',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Campanha',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Entrada')
),
('Pagamento',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Saida')
),
('Transferencia',
  (SELECT tipoMovimentacaoId FROM TipoMovimentacao WHERE descricao = 'Transferencia')
);




/* Campanha */
INSERT INTO Campanha(centroCustoId, descricao, metaValor, dataInicio, dataFim, situacaoId)
SELECT 
    c.centroCustoId,
    'Pagamento Potência Som Parque Esther 2',
    5000.00,
    '2025-01-01',
    '2025-10-01',
    s.situacaoId
FROM CentroCusto c
JOIN Situacao s ON s.contexto = 'FINANCEIRO' AND s.descricao = 'Fechado'
WHERE c.descricao = 'Fundo de Campanha - Parque Esther 2';

INSERT INTO Campanha(centroCustoId, descricao, metaValor, dataInicio, dataFim, situacaoId)
SELECT 
    c.centroCustoId,
    'Pagamento Potência Som Parque Esther 2',
    5000.00,
    '2025-01-01',
    NULL,
    s.situacaoId
FROM CentroCusto c
JOIN Situacao s ON s.contexto = 'FINANCEIRO' AND s.descricao = 'Aberto'
WHERE c.descricao = 'Fundo de Campanha - Parque Esther 2';

/* Tipo Pagamento */
INSERT INTO TipoPagamento(descricao) VALUES
('Dinheiro'),
('Cartao'),
('PIX'),
('Transferencia Bancaria'),
('Boleto');

/* Livro Caixa */
INSERT INTO LivroCaixa(centroCustoId, periodo, situacaoId)
SELECT 
    c.centroCustoId,
    '2025-12',
    s.situacaoId
FROM CentroCusto c
JOIN Situacao s ON s.contexto = 'FINANCEIRO' AND s.descricao = 'Aberto'
WHERE c.descricao = 'Tesouraria - Parque Esther 2';

