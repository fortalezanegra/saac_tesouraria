package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.preparar;

public class PrepararAuditoriaRequestDTO {

    private Long auditoriaId;
    private Long auditorId;

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
}
