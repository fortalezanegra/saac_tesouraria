package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.encerramento;

public class EncerramentoAuditoriaRequestDTO {

    private Long auditoriaId;

    public EncerramentoAuditoriaRequestDTO() {}

    public EncerramentoAuditoriaRequestDTO(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public Long getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }
}
