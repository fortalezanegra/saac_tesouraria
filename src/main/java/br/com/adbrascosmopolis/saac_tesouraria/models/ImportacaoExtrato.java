package br.com.adbrascosmopolis.saac_tesouraria.models;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoArquivo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ImportacaoExtrato")
public class ImportacaoExtrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importacaoExtratoId")
    private Long importacaoExtratoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contaFinanceiraId", referencedColumnName = "contaFinanceiraId")
    private ContaFinanceira contaFinanceira;

    @Column(name = "nomeArquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "urlArquivo")
    private String urlArquivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoArquivo", nullable = false, length = 20)
    private TipoArquivo tipoArquivo;

    @Column(name = "hashArquivo")
    private String hashArquivo;

    @Column(name = "dataImportacao")
    private LocalDate dataImportacao;

    @Column(name = "quantidadeRegistros")
    private Integer quantidadeRegistros;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    public Long getImportacaoExtratoId() {
        return importacaoExtratoId;
    }

    public void setImportacaoExtratoId(Long importacaoExtratoId) {
        this.importacaoExtratoId = importacaoExtratoId;
    }

    public ContaFinanceira getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(ContaFinanceira contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getHashArquivo() {
        return hashArquivo;
    }

    public void setHashArquivo(String hashArquivo) {
        this.hashArquivo = hashArquivo;
    }

    public LocalDate getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDate dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public Integer getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(Integer quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
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
