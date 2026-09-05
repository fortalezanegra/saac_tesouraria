package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.ItensRelatorioMensalFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.RelatorioMensalFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.RepositorioRelatorioFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.ResumoRelatorioMensalFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.MovimentoListagemProjection;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.RelatorioMensalMovimentoFinancieroRepository;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.TotaisCaixaProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioMensalFluxoCaixaService {

    private static final Logger log = LoggerFactory.getLogger(RelatorioMensalFluxoCaixaService.class);

    private final RelatorioMensalMovimentoFinancieroRepository movimentoFinanceiroRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final GeradorPdfRelatorioMensalFluxoCaixaService relatorioFechamentoService;
    private final ArmazenamentoRelatorioService armazenamentoRelatorioService;
    private final RepositorioRelatorioFluxoCaixaService repositorioRelatorioFluxoCaixaService;

    public RelatorioMensalFluxoCaixaService(
            RelatorioMensalMovimentoFinancieroRepository movimentoFinanceiroRepository,
            LivroCaixaRepository livroCaixaRepository,
            GeradorPdfRelatorioMensalFluxoCaixaService relatorioFechamentoService,
            ArmazenamentoRelatorioService armazenamentoRelatorioService,
            RepositorioRelatorioFluxoCaixaService repositorioRelatorioFluxoCaixaService
    ){
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.relatorioFechamentoService = relatorioFechamentoService;
        this.armazenamentoRelatorioService = armazenamentoRelatorioService;
        this.repositorioRelatorioFluxoCaixaService = repositorioRelatorioFluxoCaixaService;
    }

    @Transactional(readOnly = true)
    public ResumoRelatorioMensalFluxoCaixaDTO calcularResumo(Long livroCaixaId) {

        buscarLivroCaixa(livroCaixaId);

        TotaisCaixaProjection r = movimentoFinanceiroRepository
                .buscarResumoFechamento(livroCaixaId);

        BigDecimal totalEntradas = r.getTotalEntradas();
        BigDecimal totalSaidas   = r.getTotalSaidas();
        BigDecimal saldoEnviado  = r.getSaldoEnviado();

        BigDecimal saldoFinal   = totalEntradas.subtract(totalSaidas);
        BigDecimal saldoAEnviar = saldoFinal.subtract(saldoEnviado);

        return new ResumoRelatorioMensalFluxoCaixaDTO(
                totalEntradas,
                totalSaidas,
                saldoFinal,
                saldoEnviado,
                saldoAEnviar
        );
    }

    /**
     * Gera o PDF, salva em disco e persiste a referência no banco.
     *
     * Estratégia de compensação: se a persistência no banco falhar
     * APÓS o arquivo já ter sido salvo em disco, o arquivo físico
     * é removido para evitar inconsistência (arquivo órfão sem
     * referência no banco).
     */
    public byte[] gerarRelatorioFechamentoPdf(Long livroCaixaId) {

        RelatorioMensalFluxoCaixaDTO relatorio = montarRelatorio(livroCaixaId);
        LivroCaixa livro = buscarLivroCaixa(livroCaixaId);

        byte[] pdf = relatorioFechamentoService.gerarPdf(relatorio);

        // 1) Salva o PDF em disco (fora da transação de banco)
        String caminhoCompleto = armazenamentoRelatorioService.salvarPdf(
                pdf,
                livro.getCentroCusto().getDescricao(),
                relatorio.getCompetencia(),
                livroCaixaId
        );

        String nomeArquivo = extrairNomeArquivo(caminhoCompleto);

        // 2) Tenta persistir a referência no banco
        try {
            persistirReferencia(nomeArquivo, caminhoCompleto);
        } catch (Exception e) {
            log.error("Falha ao persistir referência do relatório no banco. " +
                    "Executando rollback do arquivo físico: {}", caminhoCompleto, e);

            removerArquivoFisico(caminhoCompleto);

            throw new RuntimeException(
                    "Erro ao salvar a referência do relatório no banco de dados. " +
                            "O arquivo gerado foi removido do disco para evitar inconsistência.", e
            );
        }

        log.info("Relatório de fluxo de caixa gerado e salvo com sucesso em: {}", caminhoCompleto);

        return pdf;
    }

    /**
     * Persiste a referência do arquivo em uma transação isolada.
     * Isso garante que, se falhar, o rollback de banco não afete
     * outras operações e podemos capturar a exceção para acionar
     * a compensação do arquivo físico.
     */
    @Transactional
    protected void persistirReferencia(String nomeArquivo, String caminhoCompleto) {
        RepositorioRelatorioFluxoCaixaDTO referencia = new RepositorioRelatorioFluxoCaixaDTO(
                null,
                nomeArquivo,
                caminhoCompleto
        );

        repositorioRelatorioFluxoCaixaService.create(referencia);
    }

    /**
     * Remove o arquivo físico do disco (compensação em caso de
     * falha na persistência do banco).
     */
    private void removerArquivoFisico(String caminhoCompleto) {
        try {
            Path path = Paths.get(caminhoCompleto);
            boolean removido = Files.deleteIfExists(path);

            if (removido) {
                log.warn("Arquivo removido com sucesso durante rollback: {}", caminhoCompleto);
            } else {
                log.warn("Arquivo não encontrado durante tentativa de rollback: {}", caminhoCompleto);
            }

        } catch (IOException ioException) {
            log.error("ERRO CRÍTICO: falha ao remover arquivo físico durante rollback: {}. " +
                    "Arquivo órfão pode ter ficado em disco!", caminhoCompleto, ioException);
        }
    }

    private RelatorioMensalFluxoCaixaDTO montarRelatorio(Long livroCaixaId) {

        LivroCaixa livro = buscarLivroCaixa(livroCaixaId);

        TotaisCaixaProjection resumo = movimentoFinanceiroRepository
                .buscarResumoFechamento(livroCaixaId);

        BigDecimal totalEntradas = resumo.getTotalEntradas();
        BigDecimal totalSaidas   = resumo.getTotalSaidas();
        BigDecimal saldoEnviado  = resumo.getSaldoEnviado();
        BigDecimal saldoFinal    = totalEntradas.subtract(totalSaidas);
        BigDecimal saldoAEnviar  = saldoFinal.subtract(saldoEnviado);

        List<MovimentoListagemProjection> movimentos = movimentoFinanceiroRepository
                .buscarMovimentosParaRelatorio(livroCaixaId);

        BigDecimal saldoAcumulado = livro.getSaldoInicial() != null
                ? livro.getSaldoInicial()
                : BigDecimal.ZERO;

        List<ItensRelatorioMensalFluxoCaixaDTO> itens = new ArrayList<>();

        for (MovimentoListagemProjection m : movimentos) {

            BigDecimal entrada = BigDecimal.ZERO;
            BigDecimal saida   = BigDecimal.ZERO;

            // tipo agora vem de Categoria.tipoCategoria (ENTRADA/SAIDA)
            if ("ENTRADA".equals(m.getTipoMovimento())) {
                entrada = m.getValor();
                saldoAcumulado = saldoAcumulado.add(entrada);
            } else {
                saida = m.getValor();
                saldoAcumulado = saldoAcumulado.subtract(saida);
            }

            itens.add(new ItensRelatorioMensalFluxoCaixaDTO(
                    m.getDataMovimento(),
                    m.getCategoria(),
                    entrada,
                    saida,
                    saldoAcumulado,
                    m.getHistorico(),
                    m.getResponsavel()
            ));
        }

        // competencia agora é do tipo DATE (LocalDate) na entidade LivroCaixa
        String competenciaFormatada = livro.getCompetencia()
                .format(DateTimeFormatter.ofPattern("MM/yyyy"));

        return new RelatorioMensalFluxoCaixaDTO(
                livro.getCentroCusto().getDescricao(),
                competenciaFormatada,
                LocalDateTime.now(),
                totalEntradas,
                totalSaidas,
                saldoFinal,
                saldoEnviado,
                saldoAEnviar,
                itens
        );
    }

    private String extrairNomeArquivo(String caminhoCompleto) {
        return Paths.get(caminhoCompleto).getFileName().toString();
    }

    private LivroCaixa buscarLivroCaixa(Long livroCaixaId) {
        return livroCaixaRepository.findById(livroCaixaId)
                .orElseThrow(() -> new LivroCaixaNaoEncontradoException(
                        "Livro Caixa não encontrado: " + livroCaixaId
                ));
    }
}
