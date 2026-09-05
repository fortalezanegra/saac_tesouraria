package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItensRelatorioMensalFluxoCaixaDTO {

    private final LocalDate data;
    private final String categoria;
    private final BigDecimal entrada;
    private final BigDecimal saida;
    private final BigDecimal saldo;
    private final String historico;
    private final String responsavel;

    public ItensRelatorioMensalFluxoCaixaDTO(
            LocalDate data,
            String categoria,
            BigDecimal entrada,
            BigDecimal saida,
            BigDecimal saldo,
            String historico,
            String responsavel
    ) {
        this.data = data;
        this.categoria = (categoria != null && !categoria.isBlank()) ? categoria : "-";
        this.entrada = entrada;
        this.saida = saida;
        this.saldo = saldo;
        this.historico = (historico != null && !historico.isBlank()) ? historico : "-";
        this.responsavel = (responsavel != null && !responsavel.isBlank()) ? responsavel : "-";
    }

    public LocalDate getData() { return data; }
    public String getCategoria() { return categoria; }
    public BigDecimal getEntrada() { return entrada; }
    public BigDecimal getSaida() { return saida; }
    public BigDecimal getSaldo() { return saldo; }
    public String getHistorico() { return historico; }
    public String getResponsavel() { return responsavel; }
}
