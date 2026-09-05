package br.com.adbrascosmopolis.saac_tesouraria.models;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ExtratoBancario")
public class ExtratoBancario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extratoBancarioId")
    private Long extratoBancarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaFinanceiraId", referencedColumnName = "contaFinanceiraId")
    private ContaFinanceira contaFinanceira;

    @Column(name = "dataMovimento")
    private LocalDate dataMovimento;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", precision = 18, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    @Column(name = "numeroDocumento")
    private String numeroDocumento;

    @Column( name = "saldoConta", precision = 18, scale = 2 )
    private BigDecimal saldoConta;

    @Column(name = "hashMovimento")
    private String hashMovimento;

    @ManyToOne
    @JoinColumn(name = "importacaoExtratoId", referencedColumnName = "importacaoExtratoId")
    private ImportacaoExtrato importacaoExtrato;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    public Long getExtratoBancarioId() {
        return extratoBancarioId;
    }

    public void setExtratoBancarioId(Long extratoBancarioId) {
        this.extratoBancarioId = extratoBancarioId;
    }

    public ContaFinanceira getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(ContaFinanceira contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getHashMovimento() {
        return hashMovimento;
    }

    public void setHashMovimento(String hashMovimento) {
        this.hashMovimento = hashMovimento;
    }

    public ImportacaoExtrato getImportacaoExtrato() {
        return importacaoExtrato;
    }

    public void setImportacaoExtrato(ImportacaoExtrato importacaoExtrato) {
        this.importacaoExtrato = importacaoExtrato;
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
