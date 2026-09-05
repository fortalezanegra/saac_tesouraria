package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "RepositorioRelatorioFluxoCaixa")
public class RepositorioRelatorioFluxoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repositorioId", nullable = false)
    private Long repositorioId;

    @Column(name = "nomeArquivo", nullable = false)
    private String nomeArquivo;

    @Column(name = "urlArquivo", nullable = false)
    private String urlArquivo;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "deletedAt")
    private LocalDate deletedAt;

    @Column(name = "modifiedBy")
    private Long modifiedBy;

    public Long getRepositorioId() {
        return repositorioId;
    }

    public void setRepositorioId(Long repositorioId) {
        this.repositorioId = repositorioId;
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

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
