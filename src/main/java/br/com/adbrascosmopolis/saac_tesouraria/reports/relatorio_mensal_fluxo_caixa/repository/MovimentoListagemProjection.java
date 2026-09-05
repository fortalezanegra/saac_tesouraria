package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovimentoListagemProjection {
    Long getMovimentoFinanceiroId();
    LocalDate getDataMovimento();
    String getCategoria();
    String getTipoMovimento(); // vem de Categoria.tipoCategoria (ENTRADA/SAIDA)
    BigDecimal getValor();
    String getHistorico();
    String getResponsavel();
}
