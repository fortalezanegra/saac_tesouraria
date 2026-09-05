package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LivroCaixaEditDTO {

    private Long livroCaixaId;
    private String centroCusto;
    private LocalDate competencia;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private LocalDateTime dataFechamento;
    private Integer situacaoId;

    public static LivroCaixaEditDTO fromLivroCaixa(LivroCaixa l){

        LivroCaixaEditDTO dto = new LivroCaixaEditDTO();

        dto.livroCaixaId = l.getLivroCaixaId();
        dto.centroCusto = l.getCentroCusto() != null ? l.getCentroCusto().getDescricao() : null;
        dto.competencia = l.getCompetencia();
        dto.saldoInicial = l.getSaldoInicial();
        dto.saldoFinal = l.getSaldoFinal();
        dto.dataFechamento = l.getDataFechamento();
        dto.situacaoId = l.getSituacao() != null ? l.getSituacao().getSituacaoId() : null;

        return dto;
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
