package br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria;

/**
 * DTO usado nos componentes “dropdown”/“select”.
 * Contém apenas o identificador e um campo “label” para exibição.
 */
public class AuditoriaDropdownDTO {

    private Long auditoriaId;
    private String observacoes;   // será exibido como label

    public AuditoriaDropdownDTO() {}

    public AuditoriaDropdownDTO(Long auditoriaId, String observacoes) {
        this.auditoriaId = auditoriaId;
        this.observacoes = observacoes;
    }

    public static AuditoriaDropdownDTO fromAuditoria(
            br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria a) {

        if (a == null) {
            return null;
        }
        return new AuditoriaDropdownDTO(a.getAuditoriaId(), a.getObservacoes());
    }

    public Long getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
