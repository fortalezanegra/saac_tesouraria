package br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditoriaResponseDTO {

    private Long auditoriaId;
    private String livroCaixa;
    private LocalDate competencia;
    private String auditor;
    private LocalDateTime dataInicio;
    private String observacoes;
    private Deliberacao deliberacao;
    private java.time.LocalDateTime dataFim;
    private String situacao;

    public AuditoriaResponseDTO(
        Long auditoriaId,
        String livroCaixa,
        LocalDate competencia,
        String auditor,
        LocalDateTime dataInicio,
        String observacoes,
        Deliberacao deliberacao,
        LocalDateTime dataFim,
        String situacao
        ){
        this.auditoriaId = auditoriaId;
        this.livroCaixa  = livroCaixa;
        this.competencia = competencia;
        this.auditor   = auditor;
        this.dataInicio  = dataInicio;
        this.observacoes = observacoes;
        this.deliberacao = deliberacao;
        this.dataFim     = dataFim;
        this.situacao    = situacao;
    }

    public static AuditoriaResponseDTO fromAuditoria(Auditoria a) {
        if (a == null) { return null; }

        return new AuditoriaResponseDTO(
                a.getAuditoriaId(),
                a.getLivroCaixa() != null ? a.getLivroCaixa().getCentroCusto().getDescricao() : null,
                a.getLivroCaixa() != null ? a.getLivroCaixa().getCompetencia() : null,
                a.getAuditor() != null ? a.getAuditor().getNome() : null,
                a.getDataInicio(),
                a.getObservacoes(),
                a.getDeliberacao(),
                a.getDataFim(),
                a.getSituacao() != null ? a.getSituacao().getDescricao() : null
        );
    }

    public Long getAuditoriaId() {
        return auditoriaId;
    }

    public void setAuditoriaId(Long auditoriaId) {
        this.auditoriaId = auditoriaId;
    }

    public String getLivroCaixa() {
        return livroCaixa;
    }

    public void setLivroCaixa(String livroCaixa) {
        this.livroCaixa = livroCaixa;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
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

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
