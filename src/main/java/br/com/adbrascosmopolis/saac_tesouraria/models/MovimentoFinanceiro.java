package br.com.adbrascosmopolis.saac_tesouraria.models;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "MovimentoFinanceiro")
public class MovimentoFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimentoFinanceiroId", nullable = false)
    private Long movimentoFinanceiroId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livroCaixaId", referencedColumnName = "livroCaixaId")
    private LivroCaixa livroCaixa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaFinanceiraId", referencedColumnName = "contaFinanceiraId")
    private ContaFinanceira contaFinanceira;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaId", referencedColumnName = "categoriaId")
    private Categoria categoria;

    @Column(name = "valor", precision = 18, scale = 2)
    private BigDecimal valor;

    @Column(name = "eletronico")
    private Boolean eletronico;

    @Column(name = "dataMovimento",  nullable = false)
    private LocalDate dataMovimento;

    @Column(name = "historico")
    private String historico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoaId", referencedColumnName = "pessoaId")
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campanhaId", referencedColumnName = "campanhaId")
    private Campanha campanha;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt; // continua manual (soft delete)

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    public Long getMovimentoFinanceiroId() {
        return movimentoFinanceiroId;
    }

    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
    }

    public LivroCaixa getLivroCaixa() {
        return livroCaixa;
    }

    public void setLivroCaixa(LivroCaixa livroCaixa) {
        this.livroCaixa = livroCaixa;
    }

    public ContaFinanceira getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(ContaFinanceira contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getEletronico() {
        return eletronico;
    }

    public void setEletronico(Boolean eletronico) {
        this.eletronico = eletronico;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
