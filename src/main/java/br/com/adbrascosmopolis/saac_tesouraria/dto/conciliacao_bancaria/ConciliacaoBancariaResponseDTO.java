package br.com.adbrascosmopolis.saac_tesouraria.dto.conciliacao_bancaria;

import br.com.adbrascosmopolis.saac_tesouraria.models.ConciliacaoBancaria;

import java.time.LocalDate;

public class ConciliacaoBancariaResponseDTO {

    private Long conciliacaoBancariaId;
    private Long movimentoFinanceiroId;
    private Long extratoBancarioId;
    private LocalDate dataConciliacao;
    private String observacao;

    public ConciliacaoBancariaResponseDTO(
            Long conciliacaoBancariaId,
            Long movimentoFinanceiroId,
            Long extratoBancarioId,
            LocalDate dataConciliacao,
            String observacao
    ){
        this.conciliacaoBancariaId = conciliacaoBancariaId;
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.extratoBancarioId = extratoBancarioId;
        this.dataConciliacao = dataConciliacao;
        this.observacao = observacao;
    }

    public static ConciliacaoBancariaResponseDTO fromConciliacaoBancaria(ConciliacaoBancaria c){
        if(c == null){
            return null;
        }

        return new ConciliacaoBancariaResponseDTO(
                c.getConciliacaoBancariaId(),
                c.getMovimentoFinanceiro() != null ? c.getMovimentoFinanceiro().getMovimentoFinanceiroId() : null,
                c.getExtratoBancario() != null ? c.getExtratoBancario().getExtratoBancarioId() : null,
                c.getDataConciliacao(),
                c.getObservacao()
        );
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
