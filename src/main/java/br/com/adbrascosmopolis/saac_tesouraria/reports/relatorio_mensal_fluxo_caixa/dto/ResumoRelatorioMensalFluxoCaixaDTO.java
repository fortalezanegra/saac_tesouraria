package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto;

import java.math.BigDecimal;

public record ResumoRelatorioMensalFluxoCaixaDTO(
        BigDecimal totalEntradas,
        BigDecimal totalSaidas,
        BigDecimal saldoFinal,
        BigDecimal saldoEnviado,
        BigDecimal saldoAEnviar
) {}
