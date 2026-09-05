package br.com.adbrascosmopolis.saac_tesouraria.dto.conciliacao_bancaria;

import br.com.adbrascosmopolis.saac_tesouraria.models.ConciliacaoBancaria;
import br.com.adbrascosmopolis.saac_tesouraria.models.ExtratoBancario;
import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;

import java.time.LocalDate;

public class ConciliacaoBancariaRequestDTO {

    private Long movimentoFinanceiroId;
    private Long extratoBancarioId;
    private LocalDate dataConciliacao;
    private String observacao;

    public ConciliacaoBancariaRequestDTO(
            Long movimentoFinanceiroId,
            Long extratoBancarioId,
            LocalDate dataConciliacao,
            String observacao
    ){
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.extratoBancarioId = extratoBancarioId;
        this.dataConciliacao = dataConciliacao;
        this.observacao = observacao;
    }

    public ConciliacaoBancaria mapToConciliacaoBancarioa(ConciliacaoBancaria c){
        ConciliacaoBancaria conciliacao = new ConciliacaoBancaria();

        conciliacao.setDataConciliacao(this.dataConciliacao);
        conciliacao.setObservacao(this.observacao);

        //Relacionamentos
        if(this.movimentoFinanceiroId != null){
            MovimentoFinanceiro movimento = new MovimentoFinanceiro();
            movimento.setMovimentoFinanceiroId(this.movimentoFinanceiroId);
            conciliacao.setMovimentoFinanceiro(movimento);
        }

        if(this.extratoBancarioId != null){
            ExtratoBancario extrato = new ExtratoBancario();
            extrato.setExtratoBancarioId(this.extratoBancarioId);
            conciliacao.setExtratoBancario(extrato);
        }

        return conciliacao;
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
