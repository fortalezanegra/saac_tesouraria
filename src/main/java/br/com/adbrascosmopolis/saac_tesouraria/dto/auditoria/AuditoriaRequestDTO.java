package br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Pessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;

import java.time.LocalDateTime;

/**
 * DTO recebido nas requisições de criação/alteração.
 * Não contém o ID da auditoria (gerado pelo banco) e contém apenas os IDs
 * dos relacionamentos, facilitando o binding no controller.
 */
public class AuditoriaRequestDTO {

    private Long livroCaixaId;
    private Long auditorId;
    private LocalDateTime dataInicio;
    private String observacoes;
    private Deliberacao deliberacao;
    private LocalDateTime dataFim;
    private Integer situacaoId;
    private Long modifiedBy;   // opcional – quem está fazendo a mudança

    public AuditoriaRequestDTO() {}

    public AuditoriaRequestDTO(Long livroCaixaId,
                               Long auditorId,
                               java.time.LocalDateTime dataInicio,
                               String observacoes,
                               br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao deliberacao,
                               java.time.LocalDateTime dataFim,
                               Integer situacaoId,
                               Long modifiedBy) {
        this.livroCaixaId = livroCaixaId;
        this.auditorId = auditorId;
        this.dataInicio = dataInicio;
        this.observacoes = observacoes;
        this.deliberacao = deliberacao;
        this.dataFim = dataFim;
        this.situacaoId = situacaoId;
        this.modifiedBy = modifiedBy;
    }

    /** Converte o DTO para a entidade JPA. */
    public Auditoria mapToAuditoria() {
        Auditoria auditoria = new Auditoria();

        // Relacionamentos (apenas IDs)
        if (livroCaixaId != null) {
            LivroCaixa lc = new LivroCaixa();
            lc.setLivroCaixaId(livroCaixaId);
            auditoria.setLivroCaixa(lc);
        }
        if (auditorId != null) {
            Pessoa p = new Pessoa();
            p.setPessoaId(auditorId);
            auditoria.setAuditor(p);
        }
        if (situacaoId != null) {
            Situacao s = new Situacao();
            s.setSituacaoId(situacaoId);
            auditoria.setSituacao(s);
        }

        auditoria.setDataInicio(this.dataInicio);
        auditoria.setObservacoes(this.observacoes);
        auditoria.setDeliberacao(this.deliberacao);
        auditoria.setDataFim(this.dataFim);
        auditoria.setModifiedBy(this.modifiedBy);

        return auditoria;
    }

    /* ----- Getters & Setters ----- */

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public java.time.LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(java.time.LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao getDeliberacao() {
        return deliberacao;
    }

    public void setDeliberacao(br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao deliberacao) {
        this.deliberacao = deliberacao;
    }

    public java.time.LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(java.time.LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
