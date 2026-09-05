package br.com.adbrascosmopolis.saac_tesouraria.models;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditoriaId", nullable = false)
    private Long auditoriaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livroCaixaId", referencedColumnName = "livroCaixaId")
    private LivroCaixa livroCaixa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorId", referencedColumnName = "pessoaId")
    private Pessoa auditor;

    @Column(name = "dataInicio")
    private LocalDateTime dataInicio;

    @Column(name = "observacoes")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliberacao")
    private Deliberacao deliberacao;

    @Column(name = "dataFim")
    private LocalDateTime dataFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situacaoId", referencedColumnName = "situacaoId")
    private Situacao situacao;

    @CreationTimestamp
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;

    @Column(name = "modifiedBy")
    private Long modifiedBy;

    // getters/setters (ajustar tipos de LocalDate -> LocalDateTime)
    public Long getAuditoriaId() { return auditoriaId; }
    public void setAuditoriaId(Long auditoriaId) { this.auditoriaId = auditoriaId; }

    public LivroCaixa getLivroCaixa() { return livroCaixa; }
    public void setLivroCaixa(LivroCaixa livroCaixa) { this.livroCaixa = livroCaixa; }

    public Pessoa getAuditor() { return auditor; }
    public void setAuditor(Pessoa auditor) { this.auditor = auditor; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Deliberacao getDeliberacao() { return deliberacao; }
    public void setDeliberacao(Deliberacao deliberacao) { this.deliberacao = deliberacao; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public Situacao getSituacao() { return situacao; }
    public void setSituacao(Situacao situacao) { this.situacao = situacao; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    public Long getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(Long modifiedBy) { this.modifiedBy = modifiedBy; }
}
