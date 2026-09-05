package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

import java.math.BigDecimal;

public class ContaFinanceiraSaldoDTO {

    Long contaFinanceiraId;
    String descricao;
    BigDecimal saldoInicial;
    BigDecimal totalEntradas;
    BigDecimal totalSaidas;
    BigDecimal saldoAtual;

    public ContaFinanceiraSaldoDTO(
            Long contaFinanceiraId,
            String descricao,
            BigDecimal saldoInicial,
            BigDecimal totalEntradas,
            BigDecimal totalSaidas,
            BigDecimal saldoAtual
    ){
        this.contaFinanceiraId = contaFinanceiraId;
        this.descricao = descricao;
        this.saldoInicial = saldoInicial;
        this.totalEntradas = totalEntradas;
        this.totalSaidas = totalSaidas;
        this.saldoAtual = saldoAtual;
    }

    public Long getContaFinanceiraId() {
        return contaFinanceiraId;
    }

    public void setContaFinanceiraId(Long contaFinanceiraId) {
        this.contaFinanceiraId = contaFinanceiraId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getTotalEntradas() {
        return totalEntradas;
    }

    public void setTotalEntradas(BigDecimal totalEntradas) {
        this.totalEntradas = totalEntradas;
    }

    public BigDecimal getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(BigDecimal totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }
}
