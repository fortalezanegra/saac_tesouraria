# MEMÓRIA DE PROJETO — Sistema SAAC (ADBras Cosmópolis)
> Arquivo de continuidade de contexto. Cole este conteúdo no início de uma nova sessão para retomar o projeto exatamente de onde parou.

## 1. Identificação do Projeto

- **Nome:** SAAC — Sistema Administrativo ADBras Cosmópolis
- **Domínio:** Administração eclesiástica (igreja)
- **Fase:** Início de produção
- **Usuário:** João (Cosmópolis, SP, Brasil)

## 2. Arquitetura Geral

- **Padrão:** Microserviços — cada módulo é uma API **totalmente independente**, com **banco de dados próprio**.
- **Backend:** Spring Boot
- **Frontend:** Angular
- **Banco de dados:** MySQL (migração futura planejada para PostgreSQL)
- **Estratégia:** Desenvolvimento módulo a módulo, seguindo prioridades das necessidades da igreja.

### Módulos existentes

| Módulo | Status | Responsabilidade |
|---|---|---|
| Secretaria | Ativo | Fonte da verdade de: Pessoa, Congregação, Cargo Ministerial, Tipo Vínculo |
| Tesouraria | Em desenvolvimento (foco atual) | Módulo financeiro |

Dados de Pessoa/Congregação/Cargo/Vínculo são **espelhados** no módulo Tesouraria via sincronização por endpoints do módulo Secretaria (não são cadastrados/alterados diretamente na Tesouraria).

## 3. Módulo Tesouraria — Domínio Financeiro

### 3.1 Entidades principais

- **Centro de Custo** — representa departamento/congregação/filial. Obrigatoriamente possui ≥1 Conta Financeira do tipo Caixa Físico. Somente Tesouraria-Matriz pode ter contas do tipo Conta Corrente e Conta Poupança.
- **Tesouraria-Matriz** — centro de custo especial, custodiante centralizado de todos os recursos.
- **Conta Financeira** — pertence a 1 único Centro de Custo. Tipos: Caixa Físico, Conta Corrente, Conta Poupança. Saldo = Total Entradas − Total Saídas (não segmentado por competência). Permite saldo negativo (exceção operacional, responsabilidade do Centro de Custo/Matriz).
- **Livro Caixa** — consolida movimentações por competência (mês/ano) de um Centro de Custo. 1 Livro Caixa por Centro de Custo por competência. Competência armazenada como LocalDate (dia 1 do mês), exibida como mês/ano.
- **Auditoria** — vinculada ao Livro Caixa durante o fechamento.
- **Movimentação Financeira** — evento de crédito/débito; não faz transferências diretamente.
- **Movimentação Detalhe** — dados complementares (ex: contribuinte de uma contribuição).
- **Transferência Financeira** — única forma de mover recursos entre contas/livros caixa.
- **Movimento Reserva** — gerado no fechamento; calcula reservas por congregação.
- Demais entidades: Situacao, Banco, Categoria, Favorecido, Conta a Pagar, Campanha, Importação de Extrato, Extrato Bancário, Conciliação Bancária.

### 3.2 Regra-chave: Conta Financeira vs Livro Caixa

Em Movimentação Financeira comum, Conta Financeira e Livro Caixa PODEM pertencer a Centros de Custo diferentes (ex: PIX recebido na conta da Matriz, mas Livro Caixa é da filial que gerou a receita — centralização financeira).

Em Transferência Financeira, DEVE haver coincidência exata entre Centro de Custo da Conta e do Livro Caixa (origem com origem, destino com destino). Violação lança ContaFinanceiraELivroCaixaIncompativeisException.

### 3.3 Situações do Livro Caixa

Fluxo: ABERTO → AGUARDANDO_AUDITORIA → EM_AUDITORIA → AGUARDANDO_FECHAMENTO → FECHAMENTO_EM_PROCESSO → FECHADO. Caso a auditoria seja reprovada, o Livro Caixa vai para REABERTO e retorna ao fluxo após correções.

Filiais/congregações só podem pré-fechar (chegar até AGUARDANDO_FECHAMENTO); o fechamento definitivo (FECHADO) é exclusivo da Tesouraria-Matriz, após aprovação da auditoria.

### 3.4 Situações da Auditoria

Fluxo: PENDENTE → EM_ANALISE → CONCLUIDA. Deliberação final: APROVADO ou REPROVADO.

### 3.5 Situações da Transferência Financeira

Fluxo: EFETIVADA → (pode ser) ESTORNADA. O estorno não altera a transferência original; gera novas movimentações inversas; mantém referência à transferência original.

## 4. Fluxo Completo de Fechamento Mensal (11 etapas de negócio)

1. Solicitação de auditoria — Livro Caixa muda para AGUARDANDO_AUDITORIA; bloqueia lançamentos; gera relatório PDF preliminar (2 vias, assinado, enviado à Matriz com valores físicos).
2. Início da auditoria — tesoureiro/superintendente inicia; Livro Caixa muda para EM_AUDITORIA; cria registro de Auditoria (todos campos null exceto livroCaixaId e situacao=PENDENTE).
3. Execução da auditoria — validação de lançamentos, documentos, conciliação bancária, saldos, etc.
4. Deliberação: se APROVADO, Auditoria fica CONCLUIDA e Livro Caixa vai para AGUARDANDO_FECHAMENTO. Se REPROVADO, Auditoria fica CONCLUIDA (com observações obrigatórias) e Livro Caixa vai para REABERTO.
5. Correção de inconsistências (se REABERTO) — permite novos lançamentos, retorna ao fluxo via nova solicitação de auditoria.
6. Fechamento financeiro — Livro Caixa muda para FECHAMENTO_EM_PROCESSO (trava operacional anti-concorrência/duplicidade).
7. Transferência para Matriz — Saldo a enviar = Saldo final - Valores eletrônicos.
8. Encerramento da competência — Livro Caixa muda para FECHADO, registra data, bloqueia alterações futuras.
9. Abertura da competência seguinte — cria novo Livro Caixa automaticamente por Centro de Custo, situação ABERTO.
10. Reservas financeiras — gera MovimentoReserva; Saldo disponível Matriz = Saldo total - Total reservado.
11. Relatórios complementares — Livro Caixa oficial, consolidado, dizimistas por congregação, reservas.

## 5. Arquitetura de Serviços (camada de negócio)

Estrutura de pastas planejada:

services/livro_caixa/ contendo LivroCaixaFechamentoService (orquestrador principal com o método fecharCompetencia()), LivroCaixaAuditoriaService (métodos solicitarAuditoria() e iniciarAuditoria()) e LivroCaixaValidacaoService.

services/auditoria/ contendo AuditoriaService (métodos assumirAuditoria() e concluirAuditoria()) e AuditoriaValidacaoService.

services/transferencia_financeira/ contendo TransferenciaFinanceiraService e TransferenciaFinanceiraValidacaoService (esta última já possui código implementado, detalhado na seção 7 deste documento).

services/reserva_financeira/ contendo ReservaFinanceiraService (método gerarReservas()).

services/relatorio/ contendo RelatorioFechamentoService (método gerarRelatorios()).

Métodos obrigatoriamente anotados com @Transactional: solicitarAuditoria, iniciarAuditoria, assumirAuditoria, concluirAuditoria, fecharCompetencia, executarTransferencias, abrirCompetenciaSeguinte, gerarReservas, gerarRelatorios.

Estratégia de Rollback: em caso de falha durante o fechamento, todas as transferências devem ser revertidas, nenhum Livro Caixa pode permanecer parcialmente fechado, nenhum novo Livro Caixa pode ser criado, nenhuma reserva financeira pode ser gerada, e a transação deve ser cancelada automaticamente usando @Transactional(rollbackFor = Exception.class).

Fluxo técnico completo do processo: solicitarAuditoria() leva a iniciarAuditoria(), que leva a assumirAuditoria(), que leva a concluirAuditoria(). Se aprovado, o Livro Caixa fica AGUARDANDO_FECHAMENTO e então é chamado fecharCompetencia(), que coloca o Livro Caixa em FECHAMENTO_EM_PROCESSO, executa executarTransferencias(), finaliza com o Livro Caixa em FECHADO, chama abrirCompetenciaSeguinte(), depois gerarReservas() e por fim gerarRelatorios().

Princípios arquiteturais definidos: o fechamento mensal deve ser executado exclusivamente pelo backend, nenhuma regra de negócio deve ficar no frontend, o método fecharCompetencia() deve ser o ponto central e orquestrador do fechamento, todas as validações devem ser executadas antes de qualquer alteração de estado, o status FECHAMENTO_EM_PROCESSO funciona como trava operacional, e o processo deve ser idempotente, impedindo execuções duplicadas.

## 6. FechamentoMensalValidacaoService — especificado, ainda não implementado

Este serviço é responsável exclusivamente por validar as transições de estado do fluxo de auditoria e fechamento. Não persiste dados, não cria entidades, não usa @Transactional e não realiza operações financeiras. Toda a orquestração pertence ao FechamentoMensalService.

Métodos previstos:

validarSolicitacaoAuditoria(LivroCaixa) — o Livro Caixa deve possuir situação ABERTO ou REABERTO; lança LivroCaixaNaoEncontradoException ou LivroCaixaNaoPodeSolicitarAuditoriaException.

validarInicializacaoAuditoria(LivroCaixa) — o Livro Caixa deve possuir situação AGUARDANDO_AUDITORIA; lança LivroCaixaNaoPodeIniciarAuditoriaException.

validarAuditoriaExistente(LivroCaixa) — não pode existir Auditoria com situação PENDENTE ou EM_ANALISE para o mesmo Livro Caixa; lança AuditoriaJaExisteException.

validarAuditoriaParaAssumir(Auditoria) — a auditoria deve existir e possuir situação PENDENTE; lança AuditoriaNaoEncontradaException ou AuditoriaNaoPodeSerAssumidaException.

validarAuditoriaParaConclusao(Auditoria) — a auditoria deve existir e possuir situação EM_ANALISE; lança AuditoriaNaoEncontradaException ou AuditoriaJaConcluidaException.

validarLivroCaixaParaFechamento(LivroCaixa) — o Livro Caixa deve possuir situação AGUARDANDO_FECHAMENTO; lança LivroCaixaNaoAguardaFechamentoException.

validarLivroCaixaFechado(LivroCaixa) — o Livro Caixa não pode possuir situação FECHADO; lança LivroCaixaJaFechadoException.

validarLivroCaixaReaberto(LivroCaixa, Auditoria) — só pode reabrir quando a Auditoria estiver CONCLUIDA e a deliberação for REPROVADO; lança LivroCaixaNaoPodeSerReabertoException.

## 7. TransferenciaFinanceiraValidacaoService — CÓDIGO JÁ IMPLEMENTADO

Localização do arquivo: /src/main/java/br/com/adbrascosmopolis/saac_tesouraria/services/transferencia_financeira/TransferenciaFinanceiraValidacaoService.java

Status: estrutura completa e já implementada (não é apenas esqueleto). Contém dois métodos públicos orquestradores e sete métodos privados de validação específica.

O método validarTransferencia(request, contaOrigem, contaDestino, livroOrigem, livroDestino, categoria) chama em ordem: validarValor, validarContasFinanceiras, validarLivrosCaixa, validarCentroCusto, validarCategoria e validarSaldo.

O método validarEstorno(transferencia, livroOrigem, livroDestino) chama em ordem: validarSituacaoTransferencia e validarLivrosParaEstorno.

Detalhamento dos métodos privados já implementados:

validarContasFinanceiras(origem, destino) — verifica se origem e destino existem, se são diferentes entre si e se ambas estão com situação ATIVO.

validarLivrosCaixa(origem, destino) — verifica se origem e destino existem, se são diferentes entre si (por ID) e se ambos possuem situação ABERTO.

validarCentroCusto(contaOrigem, contaDestino, livroOrigem, livroDestino) — verifica se o Centro de Custo da conta de origem é igual ao Centro de Custo do livro de origem, e se o Centro de Custo da conta de destino é igual ao Centro de Custo do livro de destino; caso contrário lança ContaFinanceiraELivroCaixaIncompativeisException com mensagem detalhando as descrições dos centros de custo envolvidos.

validarValor(valor) — valor não pode ser nulo e deve ser maior que zero, senão lança ValorTransferenciaInvalidoException.

validarSaldo(contaOrigem, valor) — calcula o saldo disponível através do ContaFinanceiraSaldoService.calcularSaldo() e verifica se o valor solicitado é menor ou igual ao saldo disponível, senão lança SaldoInsuficienteException.

validarCategoria(categoria) — a categoria deve existir, possuir descrição igual a "Transferencia Interna" (case insensitive) e tipoCategoria igual a TРАNSFERENCIA, senão lança CategoriaInvalidaException.

validarSituacaoTransferencia(transferencia) — a transferência deve existir, possuir situação definida e essa situação deve ser EFETIVADA, senão lança TransferenciaNaoPodeSerEstornadaException.

validarLivrosParaEstorno(origem, destino) — origem e destino devem existir e ambos devem possuir situação ABERTO, senão lança LivroCaixaFechadoException.

Ordem de implementação definida para as próximas etapas deste serviço: primeiro validarValor, depois validarContasFinanceiras, depois validarLivrosCaixa, depois validarCentroCusto, depois validarCategoria, depois validarSaldo, depois validarSituacaoTransferencia, e por último validarLivrosParaEstorno. Todos esses métodos já foram implementados conforme código atual.

Próximas implementações gerais planejadas para o módulo de transferência financeira, em ordem: criar exceções específicas do domínio, implementar validação de contas financeiras, implementar validação de Livros Caixa, implementar validação de compatibilidade entre Conta Financeira e Livro Caixa, implementar validação de valor, implementar validação de categoria, implementar validação de saldo, e implementar validações de estorno.

## 8. Padrão de Exceções Personalizadas

Estrutura de pacotes de exceções:

/exceptions/common/ contém a classe base BusinessException, abstrata, estendendo RuntimeException, com atributo codigo e construtor protegido recebendo codigo e mensagem. Todas as exceções específicas do sistema devem herdar desta classe.

/exceptions/handler/ contém o GlobalExceptionHandler, anotado com @RestControllerAdvice, responsável por capturar exceções de negócio, convertê-las em respostas HTTP, padronizar mensagens da API, evitar duplicação de tratamento nos Controllers e centralizar o comportamento dos erros.

/exceptions/response/ contém o ApiErrorResponse, objeto padronizado de retorno de erro, com campos codigo, mensagem e dataHora. Exemplo de resposta: código SALDO_INSUFICIENTE, mensagem "A conta financeira de origem não possui saldo suficiente.", dataHora no formato ISO.

/exceptions/transferencia_financeira/ e demais pastas por domínio (/exceptions/banco/, etc.) contêm as exceções específicas de cada módulo, evitando que regras diferentes sejam misturadas.

Regras de implementação a seguir: Controllers não devem lançar exceções de negócio; Services devem delegar validações para classes específicas quando aplicável; exceções devem representar uma regra de negócio clara; mensagens devem ser orientadas ao usuário ou consumidor da API; cada módulo deve possuir seu próprio pacote de exceções; não criar exceções genéricas para situações específicas; evitar reutilizar exceções entre domínios diferentes; o nome da exceção deve representar claramente a regra violada.

Fluxo de tratamento padrão: Controller chama Service, que aplica a Regra de negócio, que lança uma Exception personalizada, capturada pelo GlobalExceptionHandler, que retorna um ApiErrorResponse ao Cliente.

Exceções já existentes no domínio transferencia_financeira: ContaFinanceiraNaoEncontradaException, ContaFinanceiraInvalidaException, ContaFinanceiraELivroCaixaIncompativeisException, LivroCaixaNaoEncontradoException, LivroCaixaFechadoException, LivroCaixaInvalidoException, SaldoInsuficienteException, ValorTransferenciaInvalidoException, CategoriaInvalidaException, TransferenciaJaEstornadaException, TransferenciaNaoPodeSerEstornadaException.

Erros de negócio (situações previstas pelas regras do sistema, como saldo insuficiente, conta inexistente, livro caixa encerrado, transferência já estornada) devem gerar respostas controladas para o usuário. Erros inesperados (falhas técnicas como erro de banco de dados, NullPointerException, falha de comunicação externa, erros de infraestrutura) devem receber tratamento diferenciado, com geração de logs para análise técnica.

## 9. Padrão de Mapeamento de Objetos (sem classes Mapper dedicadas)

O sistema SAAC não utiliza classes Mapper separadas. Toda a responsabilidade de conversão entre entidades e DTOs é implementada nas próprias classes DTO, usando métodos estáticos e de instância. Isso centraliza as regras de conversão, reduz a quantidade de classes auxiliares, facilita a manutenção e aproxima a transformação dos dados do contexto de uso.

RequestDTO é responsável por receber dados enviados pela API, realizar validações básicas e converter os dados para a entidade de domínio, através do método de instância mapToEntity().

EditDTO é responsável por receber dados destinados à atualização e aplicar apenas alterações permitidas, através do método estático fromEntity(entity).

ResponseDTO é responsável por converter entidades em objetos de resposta e formatar dados para exibição, através do método estático fromEntity(entity).

DropdownDTO é responsável por retornar apenas os campos necessários para componentes de seleção, geralmente através de um construtor recebendo id e descricao.

Exemplos de uso: TransferenciaFinanceiraRequestDTO.mapToEntity(); TransferenciaFinanceiraEditDTO.fromEntity(entity); TransferenciaFinanceiraResponseDTO.fromEntity(entity).

## 10. Convenções de nomenclatura observadas no código já escrito

Package raiz do projeto: br.com.adbrascosmopolis.saac_tesouraria.

Constantes de situação usadas por comparação de String via getDescricao(): SituacaoConstantes, SituacaoLivrosConstantes, SituacaoTransferenciaConstantes.

Enum TipoCategoria, com valor TRANSFERENCIA usado para validar categorias de transferência interna.

Uso da anotação @RequiredArgsConstructor do Lombok em classes de Service, embora na implementação atual do TransferenciaFinanceiraValidacaoService o construtor tenha sido escrito manualmente recebendo ContaFinanceiraSaldoService.

## 11. Ponto de retomada do projeto

Estávamos trabalhando no fluxo de fechamento mensal de caixa do módulo Tesouraria. A TransferenciaFinanceiraValidacaoService já está com código implementado e funcional. Os demais serviços do fechamento mensal (LivroCaixaAuditoriaService, AuditoriaService, FechamentoMensalValidacaoService, LivroCaixaFechamentoService, ReservaFinanceiraService, RelatorioFechamentoService) estão apenas especificados em documentação, sem implementação de código ainda.

Próximos passos sugeridos para continuar o desenvolvimento: implementar o FechamentoMensalValidacaoService com o corpo real dos métodos já especificados na seção 6 deste documento; implementar o LivroCaixaAuditoriaService com os métodos solicitarAuditoria() e iniciarAuditoria(); implementar o AuditoriaService com os métodos assumirAuditoria() e concluirAuditoria(); implementar o LivroCaixaFechamentoService com o método orquestrador fecharCompetencia(); implementar o ReservaFinanceiraService com o método gerarReservas(); implementar o RelatorioFechamentoService com o método gerarRelatorios(); e criar as exceções específicas de domínio para LivroCaixa e Auditoria (por exemplo LivroCaixaNaoEncontradoException, LivroCaixaNaoPodeSolicitarAuditoriaException, LivroCaixaNaoPodeIniciarAuditoriaException, AuditoriaJaExisteException, AuditoriaNaoEncontradaException, AuditoriaNaoPodeSerAssumidaException, AuditoriaJaConcluidaException, LivroCaixaNaoAguardaFechamentoException, LivroCaixaJaFechadoException, LivroCaixaNaoPodeSerReabertoException), seguindo o mesmo padrão arquitetural já usado no domínio transferencia_financeira.

## 12. Instrução de uso deste arquivo

Ao iniciar uma nova conversa sobre este projeto, copie e cole o conteúdo integral deste arquivo markdown como primeira mensagem, e então solicite a continuidade a partir da seção 11 (Ponto de retomada do projeto).
