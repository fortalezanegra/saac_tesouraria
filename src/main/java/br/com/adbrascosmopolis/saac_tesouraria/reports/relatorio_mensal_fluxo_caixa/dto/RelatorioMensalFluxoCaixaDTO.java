package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RelatorioMensalFluxoCaixaDTO {

    private final String nomeCongregacao;
    private final String competencia; // ex: "07/2026"
    private final LocalDateTime dataEmissao;

    private final BigDecimal totalEntradas;
    private final BigDecimal totalSaidas;
    private final BigDecimal saldoFinal;
    private final BigDecimal saldoEnviado;
    private final BigDecimal saldoAEnviar;

    private final List<ItensRelatorioMensalFluxoCaixaDTO> itens;

    public RelatorioMensalFluxoCaixaDTO(
            String nomeCongregacao,
            String competencia,
            LocalDateTime dataEmissao,
            BigDecimal totalEntradas,
            BigDecimal totalSaidas,
            BigDecimal saldoFinal,
            BigDecimal saldoEnviado,
            BigDecimal saldoAEnviar,
            List<ItensRelatorioMensalFluxoCaixaDTO> itens
    ) {
        this.nomeCongregacao = nomeCongregacao;
        this.competencia = competencia;
        this.dataEmissao = dataEmissao;
        this.totalEntradas = totalEntradas;
        this.totalSaidas = totalSaidas;
        this.saldoFinal = saldoFinal;
        this.saldoEnviado = saldoEnviado;
        this.saldoAEnviar = saldoAEnviar;
        this.itens = itens;
    }

    public String getNomeCongregacao() { return nomeCongregacao; }
    public String getCompetencia() { return competencia; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public BigDecimal getTotalEntradas() { return totalEntradas; }
    public BigDecimal getTotalSaidas() { return totalSaidas; }
    public BigDecimal getSaldoFinal() { return saldoFinal; }
    public BigDecimal getSaldoEnviado() { return saldoEnviado; }
    public BigDecimal getSaldoAEnviar() { return saldoAEnviar; }
    public List<ItensRelatorioMensalFluxoCaixaDTO> getItens() { return itens; }
}
