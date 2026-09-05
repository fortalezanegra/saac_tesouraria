package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository;

import java.math.BigDecimal;

public interface TotaisCaixaProjection {

    BigDecimal getTotalEntradas();
    BigDecimal getTotalSaidas();
    BigDecimal getSaldoEnviado();
}
