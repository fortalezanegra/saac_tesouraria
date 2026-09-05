package br.com.adbrascosmopolis.saac_tesouraria.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TransferenciaFinanceira")
public class TransferenciaFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="transferenciaFinanceiraId", nullable = false)
    private Long transferenciaFinanceiraId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaOrigemId", referencedColumnName = "contaFinanceiraId")
    private ContaFinanceira contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaDestinoId", referencedColumnName = "contaFinanceiraId")
    private ContaFinanceira contaDestino;

    @Column(name = "valor", nullable = false , precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "dataTransferencia", nullable = false)
    private LocalDateTime dataTransferencia;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "codigoTransacao")
    private String codigoTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacaoId", referencedColumnName = "situacaoId")
    private Situacao situacao;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    public Long getTransferenciaFinanceiraId() {
        return transferenciaFinanceiraId;
    }

    public void setTransferenciaFinanceiraId(Long transferenciaFinanceiraId) {
        this.transferenciaFinanceiraId = transferenciaFinanceiraId;
    }

    public ContaFinanceira getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaFinanceira contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaFinanceira getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaFinanceira contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }

    public void setCodigoTransacao(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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

