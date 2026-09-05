package br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCentro;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;

public class CentroCustoEditDTO{

    private Integer centroCustoId;
    private Integer congregacaoId;
    private TipoCentro tipoCentro;
    private String descricao;
    private Integer situacaoId;

    public static CentroCustoEditDTO fromCentroCusto(CentroCusto c){

        CentroCustoEditDTO dto = new CentroCustoEditDTO();

        dto.centroCustoId = c.getCentroCustoId();
        dto.congregacaoId = c.getCongregacao() != null ? c.getCongregacao().getCongregacaoId() : null;
        dto.tipoCentro = c.getTipoCentro();
        dto.descricao = c.getDescricao();
        dto.situacaoId = c.getSituacao() != null ? c.getSituacao().getSituacaoId() : null;

        return dto;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }

    public TipoCentro getTipoCentro() {
        return tipoCentro;
    }

    public void setTipoCentro(TipoCentro tipoCentro) {
        this.tipoCentro = tipoCentro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
