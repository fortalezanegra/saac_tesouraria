package br.com.adbrascosmopolis.saac_tesouraria.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ContaPagar")
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contaPagarId", nullable = false)
    private Long contaPagarId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centroCustoId", referencedColumnName = "centroCustoId")
    private CentroCusto centroCusto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaId", referencedColumnName = "categoriaId")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorecidoId", referencedColumnName = "favorecidoId")
    private Favorecido favorecido;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valorOriginal", precision = 18, scale = 2)
    private BigDecimal valorOriginal;

    @Column(name = "valorPago", precision = 18, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "dataEmissao")
    private LocalDate dataEmissao;

    @Column(name = "dataVencimento")
    private LocalDate dataVencimento;

    @Column(name = "dataPagamento")
    private LocalDate dataPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimentoFinanceiroId", referencedColumnName = "movimentoFinanceiroId")
    private MovimentoFinanceiro movimentoFinanceiro;

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

    public Long getContaPagarId() {
        return contaPagarId;
    }

    public void setContaPagarId(Long contaPagarId) {
        this.contaPagarId = contaPagarId;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Favorecido getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(Favorecido favorecido) {
        this.favorecido = favorecido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public MovimentoFinanceiro getMovimentoFinanceiro() {
        return movimentoFinanceiro;
    }

    public void setMovimentoFinanceiro(MovimentoFinanceiro movimentoFinanceiro) {
        this.movimentoFinanceiro = movimentoFinanceiro;
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
