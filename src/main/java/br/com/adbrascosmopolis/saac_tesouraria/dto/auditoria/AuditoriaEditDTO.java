package br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;

/**
 * DTO usado nas telas de edição.
 * Possui o ID da auditoria e todos os campos editáveis, inclusive os IDs dos relacionamentos.
 */
public class AuditoriaEditDTO {

    private Long auditoriaId;
    private Long auditorId;
    private String observacoes;
    private Deliberacao deliberacao;

    public AuditoriaEditDTO() {}

    /** Converte a entidade para o DTO de edição. */
    public static AuditoriaEditDTO fromAuditoria(Auditoria a) {
        if (a == null) {
            return null;
        }
        AuditoriaEditDTO dto = new AuditoriaEditDTO();
        dto.auditoriaId = a.getAuditoriaId();
        dto.auditorId = a.getAuditor() != null ? a.getAuditor().getPessoaId() : null;
        dto.observacoes = a.getObservacoes();
        dto.deliberacao = a.getDeliberacao();
        return dto;
    }

    /* ----- Getters & Setters ----- */

    public Long getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Deliberacao getDeliberacao() {
        return deliberacao;
    }

    public void setDeliberacao(Deliberacao deliberacao) {
        this.deliberacao = deliberacao;
    }
}
