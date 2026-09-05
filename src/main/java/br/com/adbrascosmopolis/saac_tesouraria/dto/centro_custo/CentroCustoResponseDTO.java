package br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCentro;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;

public class CentroCustoResponseDTO {

    private Integer centroCustoId;
    private String congregacao;
    private TipoCentro tipoCentro;
    private String descricao;
    private String situacao;

    public CentroCustoResponseDTO(
            Integer centroCustoId,
            String congregacao,
            TipoCentro tipoCentro,
            String descricao,
            String situacao
    ){
        this.centroCustoId = centroCustoId;
        this.congregacao = congregacao;
        this.tipoCentro = tipoCentro;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public static CentroCustoResponseDTO fromCentroCusto(CentroCusto c){

        if(c == null){
            return null;
        }

        return new CentroCustoResponseDTO(
                c.getCentroCustoId(),
                c.getCongregacao() != null ? c.getCongregacao().getNome() : null,
                c.getTipoCentro(),
                c.getDescricao(),
                c.getSituacao() != null ? c.getSituacao().getDescricao() : null
        );
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String congregacao) {
        this.congregacao = congregacao;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
