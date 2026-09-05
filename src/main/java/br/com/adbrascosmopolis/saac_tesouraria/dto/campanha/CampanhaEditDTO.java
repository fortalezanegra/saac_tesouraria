package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampanhaEditDTO{

    private Long campanhaId;
    private Integer centroCustoId;
    private String descricao;
    private BigDecimal metaValor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer situacaoId;

    public static CampanhaEditDTO fromCampanha(Campanha c){

        CampanhaEditDTO dto = new CampanhaEditDTO();

        dto.campanhaId = c.getCampanhaId();
        dto.centroCustoId =  c.getCentroCusto() != null ? c.getCentroCusto().getCentroCustoId() : null;
        dto.descricao =  c.getDescricao();
        dto.metaValor = c.getMetavalor();
        dto.dataInicio = c.getDataInicio();
        dto.dataFim = c.getDataFim();
        dto.situacaoId =   c.getSituacao() != null ? c.getSituacao().getSituacaoId() : null;

        return dto;
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getMetaValor() {
        return metaValor;
    }

    public void setMetaValor(BigDecimal metaValor) {
        this.metaValor = metaValor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
