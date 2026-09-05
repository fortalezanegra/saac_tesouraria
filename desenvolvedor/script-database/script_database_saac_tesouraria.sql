
/*####################################################################################################################
#########################    S C R I P T   D A T A B A S E   S A A C   T E S O U R A R I A   #########################
####################################################################################################################*/


/*############################################### AREA SINCRONISMO COM SECRETARIA ##################################*/

/* Removendo banco se existir */
DROP DATABASE IF EXISTS saac_tesouraria;

/* Criando Banco de Dados */
CREATE DATABASE saac_tesouraria
  DEFAULT CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

/* Selecionando Banco */
USE saac_tesouraria;

/*========================================================*/
/*                 TABELA TIPO VINCULO                    */
/*========================================================*/
CREATE TABLE TipoVinculo(
  tipoVinculoId INT PRIMARY KEY NOT NULL,
  descricao VARCHAR(50) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL

) ENGINE=InnoDB;

/*========================================================*/
/*                   TABELA CONGREGACAO                   */
/*========================================================*/
CREATE TABLE Congregacao (
  congregacaoId INT PRIMARY KEY NOT NULL,
  nome VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL

) ENGINE=InnoDB;

/*========================================================*/
/*                 TABELA CARGO MINISTERIAL               */
/*========================================================*/
CREATE TABLE CargoMinisterial (
  cargoMinisterialId INT PRIMARY KEY NOT NULL,
  descricao VARCHAR(50) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL

) ENGINE=InnoDB;

/*========================================================*/
/*                       TABELA PESSOA                    */
/*========================================================*/
CREATE TABLE Pessoa (
  pessoaId BIGINT PRIMARY KEY NOT NULL,
  tipoVinculoId INT NULL,
  congregacaoId INT NULL,
  cargoMinisterialId INT NULL,
  nome VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,

  CONSTRAINT FK_PESSOA_X_TIPO_VINCULO
  FOREIGN KEY(tipoVinculoId) REFERENCES TipoVinculo(tipoVinculoId),
  
  CONSTRAINT FK_PESSOA_X_CONGREGACAO
  FOREIGN KEY (congregacaoId) REFERENCES Congregacao(congregacaoId),
    
  CONSTRAINT FK_PESSOA_X_CARGO_MINISTERIAL
  FOREIGN KEY(cargoMinisterialId) REFERENCES CargoMinisterial(cargoMinisterialId)

) ENGINE=InnoDB;

/*#################################################### AREA TESOURARIA ##############################################*/

/*========================================================*/
/*                     TABELA SITUAÇÃO                    */
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
  modifiedBy BIGINT DEFAULT NULL,
  UNIQUE(descricao, contexto)

) ENGINE=InnoDB;

/*Insert Tabela Situacao*/
INSERT INTO Situacao (descricao, contexto, ativo, ordem) VALUES

-- FINANCEIRO --
('Aberto',                'LIVRO_CAIXA',   1, 1),
('Aguardando Auditoria',  'LIVRO_CAIXA',   1, 2),
('Em Auditoria',          'LIVRO_CAIXA',   1, 3),
('Aguardando Fechamento', 'LIVRO_CAIXA',   1, 4),
('Fechamento em Processo','LIVRO_CAIXA',   1, 5),
('Fechado',               'LIVRO_CAIXA',   1, 6),
('Reaberto',              'LIVRO_CAIXA',   1, 7),

('Efetivado',             'TRANSFERENCIA', 1, 1),
('Estornado',             'TRANSFERENCIA', 1, 2),

('Aguardando Pagamento',  'CONTA_PAGAR',   1, 1),
('Pago',                  'CONTA_PAGAR',   1, 2),

('Ativo',                 'ENTIDADES',     1, 1),
('Inativo',               'ENTIDADES',     1, 2),

('Pendente',              'AUDITORIA',     1, 1),
('Em Analise',            'AUDITORIA',     1, 2),
('Concluida',             'AUDITORIA',     1, 3);


/*========================================================*/
/*                     CENTRO DE CUSTO                    */
/*========================================================*/
CREATE TABLE CentroCusto (
  centroCustoId INT PRIMARY KEY AUTO_INCREMENT,
  congregacaoId INT NOT NULL,
  tipoCentro ENUM('FILIAL', 'MATRIZ') NOT NULL,
  descricao VARCHAR(100) UNIQUE NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,

  CONSTRAINT FK_CENTRO_CUSTO_X_CONGREGACAO
  FOREIGN KEY(congregacaoId) REFERENCES Congregacao(congregacaoId),

  CONSTRAINT FK_CENTRO_CUSTO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*Insert Tabela Centro Custo*/
INSERT INTO CentroCusto (congregacaoId, tipoCentro, descricao, situacaoId) VALUES
(001, 'MATRIZ', 'Tesouraria Matriz', 1),
(007, 'FILIAL', 'Tesouraria Parque Ether 2', 1);

/*========================================================*/
/*                        CAMPANHA                        */
/*========================================================*/
CREATE TABLE Campanha (
  campanhaId BIGINT PRIMARY KEY AUTO_INCREMENT,
  centroCustoId INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  metaValor DECIMAL(20,2) NOT NULL,
  dataInicio DATE NOT NULL,
  dataFim DATE DEFAULT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,

  CONSTRAINT FK_CAMPANHA_X_CENTRO_CUSTO
  FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

  CONSTRAINT FK_CAMPANHA_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*###################################################################################*/

/*========================================================*/
/*                     TABELA BANCO                       */
/*========================================================*/
CREATE TABLE Banco (
  bancoId INT PRIMARY KEY AUTO_INCREMENT,
  codigoBanco INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,
  UNIQUE(codigoBanco),

  CONSTRAINT FK_BANCO_X_SITUACAO
  FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*Insert Tabela Banco*/
INSERT INTO Banco (codigoBanco, nome, situacaoId) VALUES
(001, 'Bradesco', 1),
(002, 'Caixa Economica Federal', 1);

/*========================================================*/
/*                 TABELA CONTA FINANCEIRA                */
/*========================================================*/
CREATE TABLE ContaFinanceira (
    contaFinanceiraId BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    centroCustoId INT NOT NULL,
    bancoId INT DEFAULT NULL,
    tipoConta ENUM('CAIXA', 'CORRENTE', 'POUPANCA') NOT NULL,
    agencia VARCHAR(20) DEFAULT NULL,
    conta VARCHAR(30) DEFAULT NULL,
    digito VARCHAR(5) DEFAULT NULL,
    saldoInicial DECIMAL(18,2) DEFAULT 0,
    dataAbertura DATE DEFAULT NULL,
    situacaoId INT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    CONSTRAINT FK_CONTA_FINANCEIRA_X_BANCO
    FOREIGN KEY(bancoId) REFERENCES Banco(bancoId),

    CONSTRAINT FK_CONTA_FINANCEIRA_X_CENTRO_CUSTO
    FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

    CONSTRAINT FK_CONTA_FINANCEIRA_X_SITUACAO
    FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*Insert Tabela ContaFinanceira*/
INSERT INTO ContaFinanceira (bancoId, centroCustoId, descricao, tipoConta, agencia, conta, digito, saldoInicial, dataAbertura, situacaoId) VALUES
(null, 1, 'Caixa - Matriz', 1, null, null, null, 0.00, '2026-01-01', 1),
(null, 2, 'Caixa - Pq. Esther 2', 1, null, null, null, 0.00, '2026-01-01', 1),
(1, 1, 'Bradesco - Matriz', 2, 001, 001, 001, 0.00, '2026-01-01', 1),
(2, 1, 'Caixa Economica Federal - Matriz', 2, 001, 001, 001, 0.00, '2026-01-01', 1);

/*========================================================*/
/*               TRANSFERENCIA FINANCEIRA                 */
/*========================================================*/
CREATE TABLE TransferenciaFinanceira (
    transferenciaFinanceiraId BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) DEFAULT NULL,
    contaOrigemId BIGINT NOT NULL,
    contaDestinoId BIGINT NOT NULL,
    valor DECIMAL(18,2) NOT NULL,
    dataTransferencia DATETIME NOT NULL,
    codigoTransacao VARCHAR(100) DEFAULT NULL,
    situacaoId INT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,
    UNIQUE(codigoTransacao),

    INDEX IDX_TRANSFERENCIA_ORIGEM(contaOrigemId),
    INDEX IDX_TRANSFERENCIA_DESTINO(contaDestinoId),
    INDEX IDX_TRANSFERENCIA_DATA(dataTransferencia),
    INDEX IDX_TRANSFERENCIA_SITUACAO(situacaoId),

    CONSTRAINT CHK_TRANSFERENCIA_CONTAS_DIFERENTES
    CHECK (contaOrigemId <> contaDestinoId),

    CONSTRAINT CHK_TRANSFERENCIA_VALOR
    CHECK (valor > 0),

    CONSTRAINT FK_TRANSFERENCIA_X_CONTA_ORIGEM
    FOREIGN KEY(contaOrigemId) REFERENCES ContaFinanceira(contaFinanceiraId),

    CONSTRAINT FK_TRANSFERENCIA_X_CONTA_DESTINO
    FOREIGN KEY(contaDestinoId) REFERENCES ContaFinanceira(contaFinanceiraId),

    CONSTRAINT FK_TRANSFERENCIA_X_SITUACAO
    FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                   TABELA CATEGORIA                     */
/*========================================================*/
CREATE TABLE Categoria (
  categoriaId INT PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) UNIQUE NOT NULL,
  tipoCategoria ENUM('ENTRADA', 'SAIDA') NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,
  unique(descricao, tipoCategoria)

) ENGINE=InnoDB;

/*Insert Tabela Categoria*/
INSERT INTO Categoria (descricao, tipoCategoria) VALUES
('Coleta do Culto', 'ENTRADA'),
('Dizimo', 'ENTRADA'),
('Oferta', 'ENTRADA'),
('Oferta Missionaria', 'ENTRADA'),
('Campanha', 'ENTRADA'),
('Transferencia Interna Recebida', 'ENTRADA'),
('Energia Eletrica', 'SAIDA'),
('Agua e Esgoto', 'SAIDA'),
('Telecomunicações', 'SAIDA'),
('Transporte', 'SAIDA'),
('Manutenção e Conservação', 'SAIDA'),
('Salarios e Ajuda de Custo', 'SAIDA'),
('Contribuições', 'SAIDA'),
('Transferencia Interna Enviada', 'SAIDA');

/*========================================================*/
/*                       LIVRO CAIXA                      */
/*========================================================*/
DROP TABLE IF EXISTS LivroCaixa;
CREATE TABLE LivroCaixa (
  livroCaixaId BIGINT PRIMARY KEY AUTO_INCREMENT,
  centroCustoId INT NOT NULL,
  competencia DATE NOT NULL,
  saldoInicial DECIMAL(18,2) DEFAULT 0,
  saldoFinal DECIMAL(18,2) DEFAULT 0,
  dataFechamento DATETIME DEFAULT NULL,
  situacaoId INT NOT NULL,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  deletedAt DATETIME DEFAULT NULL,
  modifiedBy BIGINT DEFAULT NULL,
  UNIQUE(centroCustoId, competencia),

CONSTRAINT FK_LIVRO_CAIXA_X_CENTRO_CUSTO
FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

CONSTRAINT FK_LIVRO_CAIXA_X_SITUACAO
FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                  MOVIMENTO FINANCEIRO                  */
/*========================================================*/
CREATE TABLE MovimentoFinanceiro (
    movimentoFinanceiroId BIGINT PRIMARY KEY AUTO_INCREMENT,
    livroCaixaId BIGINT NOT NULL,
    contaFinanceiraId BIGINT NOT NULL,
    categoriaId INT NOT NULL,
    valor DECIMAL(18,2) NOT NULL,
    eletronico BOOL DEFAULT NULL,
    dataMovimento DATE NOT NULL,
    historico VARCHAR(255),
    pessoaId BIGINT DEFAULT NULL,
    campanhaId BIGINT DEFAULT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    INDEX IDX_MOVIMENTO_DATA(dataMovimento),
    INDEX IDX_MOVIMENTO_CONTA(contaFinanceiraId),
    INDEX IDX_MOVIMENTO_CATEGORIA(categoriaId),

    CONSTRAINT CHK_MOVIMENTO_VALOR CHECK(valor > 0),

    CONSTRAINT FK_MOVIMENTO_FINANCEIRO_X_LIVRO_CAIXA
    FOREIGN KEY(livroCaixaId) REFERENCES LivroCaixa(livroCaixaId),

    CONSTRAINT FK_MOVIMENTO_FINANCEIRO_X_CONTA_FINANCEIRA
    FOREIGN KEY(contaFinanceiraId) REFERENCES ContaFinanceira(contaFinanceiraId),

    CONSTRAINT FK_MOVIMENTO_FINANCEIRO_X_CATEGORIA
    FOREIGN KEY(categoriaId) REFERENCES Categoria(categoriaId),

    CONSTRAINT FK_MOVIMENTO_FINANCEIRO_X_PESSOA
    FOREIGN KEY(pessoaId) REFERENCES Pessoa(pessoaId),

    CONSTRAINT FK_MOVIMENTO_FINANCEIRO_X_CAMPANHA
    FOREIGN KEY(campanhaId) REFERENCES Campanha(campanhaId)

)ENGINE=InnoDB;

/*========================================================*/
/*                    IMPORTACAO DE EXTRATRO              */
/*========================================================*/
CREATE TABLE ImportacaoExtrato (
    importacaoExtratoId BIGINT PRIMARY KEY AUTO_INCREMENT,
    contaFinanceiraId BIGINT NOT NULL,

    nomeArquivo VARCHAR(255) NOT NULL,
    urlArquivo VARCHAR(500) NOT NULL,
    tipoArquivo ENUM('OFX','CSV','XLSX','PDF') NOT NULL,
    hashArquivo VARCHAR(255) NULL,

    dataImportacao DATETIME NOT NULL,
    quantidadeRegistros INT,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    INDEX IDX_IMPORTACAO_CONTA(contaFinanceiraId),

    CONSTRAINT FK_IMPORTACAO_EXTRATO_X_CONTA_FINANCEIRA
    FOREIGN KEY(contaFinanceiraId)
    REFERENCES ContaFinanceira(contaFinanceiraId)
) ENGINE=InnoDB;

/*========================================================*/
/*                     EXTRATO BANCARIO                   */
/*========================================================*/
CREATE TABLE ExtratoBancario (
    extratoBancarioId BIGINT PRIMARY KEY AUTO_INCREMENT,
    contaFinanceiraId BIGINT NOT NULL,
    dataMovimento DATETIME NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(18,2) NOT NULL,
    tipoMovimento ENUM('ENTRADA', 'SAIDA') NOT NULL,
    numeroDocumento VARCHAR(100) DEFAULT NULL,
    saldoConta DECIMAL(18,2) DEFAULT NULL,
    hashMovimento VARCHAR(255) DEFAULT NULL,
    importacaoExtratoId BIGINT DEFAULT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,
    UNIQUE(hashMovimento),

    INDEX IDX_EXTRATO_CONTA(contaFinanceiraId),
    INDEX IDX_EXTRATO_DATA(dataMovimento),

    CONSTRAINT FK_EXTRATO_X_CONTA_FINANCEIRA
    FOREIGN KEY(contaFinanceiraId) REFERENCES ContaFinanceira(contaFinanceiraId),

    CONSTRAINT FK_EXTRATO_X_IMPORTACAO_EXTRATO
    FOREIGN KEY(importacaoExtratoId) REFERENCES ImportacaoExtrato(importacaoExtratoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                  CONCILIACAO BANCARIA                  */
/*========================================================*/
CREATE TABLE ConciliacaoBancaria (
    conciliacaoBancariaId BIGINT PRIMARY KEY AUTO_INCREMENT,
    movimentoFinanceiroId BIGINT NOT NULL,
    extratoBancarioId BIGINT NOT NULL,
    dataConciliacao DATETIME NOT NULL,
    observacao VARCHAR(255) NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,
    UNIQUE(movimentoFinanceiroId, extratoBancarioId),

    CONSTRAINT FK_CONCILIACAO_X_MOVIMENTO
    FOREIGN KEY(movimentoFinanceiroId) REFERENCES MovimentoFinanceiro(movimentoFinanceiroId),

    CONSTRAINT FK_CONCILIACAO_X_EXTRATO
    FOREIGN KEY(extratoBancarioId) REFERENCES ExtratoBancario(extratoBancarioId)

) ENGINE=InnoDB;

/*========================================================*/
/*                     TABELA FAVORECIDO                  */
/*========================================================*/
CREATE TABLE Favorecido (
    favorecidoId BIGINT PRIMARY KEY AUTO_INCREMENT,

    nome VARCHAR(255) NOT NULL,

    tipoPessoa ENUM('FISICA','JURIDICA') NOT NULL,

    cpfCnpj VARCHAR(20) NULL,

    telefone VARCHAR(20) NULL,

    email VARCHAR(150) NULL,

    observacao VARCHAR(255) NULL,

    situacaoId INT NOT NULL,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    UNIQUE(cpfCnpj),

    CONSTRAINT FK_FAVORECIDO_X_SITUACAO
    FOREIGN KEY (situacaoId)
    REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                    CONTAS A PAGAR                      */
/*========================================================*/
CREATE TABLE ContaPagar (
    contaPagarId BIGINT PRIMARY KEY AUTO_INCREMENT,

    centroCustoId INT NOT NULL,
    categoriaId INT NOT NULL,
    favorecidoId BIGINT NOT NULL,

    descricao VARCHAR(255) NOT NULL,

    valorOriginal DECIMAL(18,2) NOT NULL,
    valorPago DECIMAL(18,2) DEFAULT 0,

    dataEmissao DATE NULL,
    dataVencimento DATE NOT NULL,
    dataPagamento DATE NULL,

    movimentoFinanceiroId BIGINT NULL,
    situacaoId INT NOT NULL,

    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    UNIQUE (movimentoFinanceiroId),

    CONSTRAINT CHK_CONTA_PAGAR_VALOR_ORIGINAL
    CHECK (valorOriginal > 0),

    CONSTRAINT CHK_CONTA_PAGAR_VALOR_PAGO
    CHECK (valorPago >= 0 AND valorPago <= valorOriginal),

    CONSTRAINT FK_CONTA_PAGAR_X_CENTRO_CUSTO
    FOREIGN KEY (centroCustoId)
    REFERENCES CentroCusto(centroCustoId),

    CONSTRAINT FK_CONTA_PAGAR_X_CATEGORIA
    FOREIGN KEY (categoriaId)
    REFERENCES Categoria(categoriaId),

    CONSTRAINT FK_CONTA_PAGAR_X_FAVORECIDO
    FOREIGN KEY (favorecidoId)
    REFERENCES Favorecido(favorecidoId),

    CONSTRAINT FK_CONTA_PAGAR_X_SITUACAO
    FOREIGN KEY (situacaoId)
    REFERENCES Situacao(situacaoId),

    CONSTRAINT FK_CONTA_PAGAR_X_MOVIMENTO_FINANCEIRO
    FOREIGN KEY (movimentoFinanceiroId)
    REFERENCES MovimentoFinanceiro(movimentoFinanceiroId)

) ENGINE=InnoDB;

/*========================================================*/
/*                 TABELA RESERVA FINANCEIRA              */
/*========================================================*/
CREATE TABLE ReservaFinanceira (
    ReservaFinanceiraId BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL,
    centroCustoId INT NOT NULL,
    contaFinanceiraId BIGINT NOT NULL,
    dataAbertura DATE DEFAULT NULL,
    situacaoId INT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,
    unique(descricao, centroCustoId),

    CONSTRAINT FK_RESERVA_FINANCEIRA_X_CENTRO_CUSTO
    FOREIGN KEY(centroCustoId) REFERENCES CentroCusto(centroCustoId),

    CONSTRAINT FK_RESERVA_FINANCEIRA_X_CONTA_FINANCEIRA
    FOREIGN KEY(contaFinanceiraId) REFERENCES ContaFinanceira(contaFinanceiraId),

    CONSTRAINT FK_RESERVA_FINANCEIRA_X_SITUACAO
    FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

) ENGINE=InnoDB;

/*========================================================*/
/*                  MOVIMENTO RESERVA                     */
/*========================================================*/
CREATE TABLE MovimentoReserva (
    movimentoReservaId BIGINT PRIMARY KEY AUTO_INCREMENT,
    reservaFinanceiraId BIGINT NOT NULL,
    movimentoFinanceiroId BIGINT DEFAULT NULL,
    tipoMovimento ENUM('CREDITO', 'DEBITO') NOT NULL,
    valor DECIMAL(18,2) NOT NULL,
    dataMovimento DATETIME NOT NULL,
    historico VARCHAR(255) DEFAULT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    INDEX IDX_MOVIMENTO_RESERVA_DATA(dataMovimento),
    INDEX IDX_MOVIMENTO_RESERVA_RESERVA_FINANCEIRA(reservaFinanceiraId),
    INDEX IDX_MOVIMENTO_MOVIMENTO_FINANCEIRO(movimentoFinanceiroId),

    CONSTRAINT FK_MOVIMENTO_RESERVA_X_RESERVA_FINANCEIRA
    FOREIGN KEY(reservaFinanceiraId) REFERENCES ReservaFinanceira(reservaFinanceiraId),

    CONSTRAINT FK_MOVIMENTO_RESERVA_X_MOVIMENTO_FINANCEIRO
    FOREIGN KEY(movimentoFinanceiroId) REFERENCES MovimentoFinanceiro(movimentoFinanceiroId)

)ENGINE=InnoDB;

/*========================================================*/
/*                      AUDITORIA                         */
/*========================================================*/
CREATE TABLE Auditoria (
    auditoriaId BIGINT PRIMARY KEY AUTO_INCREMENT,
    livroCaixaId BIGINT NOT NULL,
    auditorId BIGINT DEFAULT NULL,
    dataInicio DATETIME DEFAULT NULL,
    observacoes TEXT DEFAULT NULL,
    deliberacao ENUM('APROVADO', 'REPROVADO') DEFAULT NULL,
    dataFim DATETIME DEFAULT NULL,
    situacaoId INT NOT NULL,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deletedAt DATETIME DEFAULT NULL,
    modifiedBy BIGINT DEFAULT NULL,

    INDEX IDX_AUDITORIA_LIVRO_CAIXA(livroCaixaId),

    INDEX IDX_AUDITORIA_AUDITOR(auditorId),

    INDEX IDX_AUDITORIA_SITUACAO(situacaoId),

    CONSTRAINT FK_AUDITORIA_X_LIVRO_CAIXA
    FOREIGN KEY(livroCaixaId) REFERENCES LivroCaixa(livroCaixaId),

    CONSTRAINT FK_AUDITORIA_X_PESSOA
    FOREIGN KEY(auditorId) REFERENCES Pessoa(pessoaId),

    CONSTRAINT FK_AUDITORIA_X_SITUACAO
    FOREIGN KEY(situacaoId) REFERENCES Situacao(situacaoId)

)ENGINE=InnoDB;


/*========================================================*/
/*           REPOSITORIO RELATORIO FLUXO CAIXA            */
/*========================================================*/
CREATE TABLE RepositorioRelatorioFluxoCaixa (
    repositorioId BIGINT PRIMARY KEY AUTO_INCREMENT,
    nomeArquivo VARCHAR(255) NOT NULL,
    urlArquivo VARCHAR(255) NOT NULL
)ENGINE=InnoDB;


