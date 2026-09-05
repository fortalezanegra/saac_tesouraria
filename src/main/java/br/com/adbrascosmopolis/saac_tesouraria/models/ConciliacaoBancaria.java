package br.com.adbrascosmopolis.saac_tesouraria.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ConciliacaoBancaria")
public class ConciliacaoBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conciliacaoBancariaId")
    private Long conciliacaoBancariaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimentoFinanceiroId", referencedColumnName = "movimentoFinanceiroId")
    private MovimentoFinanceiro movimentoFinanceiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extratoBancarioId", referencedColumnName = "extratoBancarioId")
    private ExtratoBancario extratoBancario;

    @Column(name = "dataConciliacao")
    private LocalDate dataConciliacao;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    public Long getConciliacaoBancariaId() {
        return conciliacaoBancariaId;
    }

    public void setConciliacaoBancariaId(Long conciliacaoBancariaId) {
        this.conciliacaoBancariaId = conciliacaoBancariaId;
    }

    public MovimentoFinanceiro getMovimentoFinanceiro() {
        return movimentoFinanceiro;
    }

    public void setMovimentoFinanceiro(MovimentoFinanceiro movimentoFinanceiro) {
        this.movimentoFinanceiro = movimentoFinanceiro;
    }

    public ExtratoBancario getExtratoBancario() {
        return extratoBancario;
    }

    public void setExtratoBancario(ExtratoBancario extratoBancario) {
        this.extratoBancario = extratoBancario;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
