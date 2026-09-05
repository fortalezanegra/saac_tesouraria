package br.com.adbrascosmopolis.saac_tesouraria.dto.conciliacao_bancaria;

import br.com.adbrascosmopolis.saac_tesouraria.models.ConciliacaoBancaria;

import java.time.LocalDate;

public class ConciliacaoBancariaEditDTO {

    private Long conciliacaoBancariaId;
    private Long movimentoFinanceiroId;
    private Long extratoBancarioId;
    private LocalDate dataConciliacao;
    private String observacao;

    public static ConciliacaoBancariaEditDTO fromConciliacaoBancaria(ConciliacaoBancaria c){

        ConciliacaoBancariaEditDTO dto = new ConciliacaoBancariaEditDTO();

        dto.conciliacaoBancariaId = c.getConciliacaoBancariaId();
        dto.movimentoFinanceiroId = c.getMovimentoFinanceiro() != null ? c.getMovimentoFinanceiro().getMovimentoFinanceiroId() : null;
        dto.extratoBancarioId = c.getExtratoBancario() != null ? c.getExtratoBancario().getExtratoBancarioId() : null;
        dto.dataConciliacao = c.getDataConciliacao();
        dto.observacao = c.getObservacao();

        return dto;
    }

    public Long getConciliacaoBancariaId() {
        return conciliacaoBancariaId;
    }

    public void setConciliacaoBancariaId(Long conciliacaoBancariaId) {
        this.conciliacaoBancariaId = conciliacaoBancariaId;
    }

    public Long getMovimentoFinanceiroId() {
        return movimentoFinanceiroId;
    }

    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
    }

    public Long getExtratoBancarioId() {
        return extratoBancarioId;
    }

    public void setExtratoBancarioId(Long extratoBancarioId) {
        this.extratoBancarioId = extratoBancarioId;
    }

    public LocalDate getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(LocalDate dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
