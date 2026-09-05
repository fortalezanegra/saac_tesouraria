package br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCentro;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.Congregacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class CentroCustoRequestDTO {

    private Integer congregacaoId;
    private TipoCentro tipoCentro;
    private String descricao;
    private Integer situacaoId;

    public CentroCustoRequestDTO(
            Integer congregacaoId,
            TipoCentro tipoCentro,
            String descricao,
            Integer situacaoId
    ){
        this.congregacaoId = congregacaoId;
        this.tipoCentro = tipoCentro;
        this.descricao = descricao;
        this.situacaoId = situacaoId;
    }

    public CentroCusto mapToCentroCusto(){
        CentroCusto centroCusto = new CentroCusto();

        centroCusto.setTipoCentro(this.tipoCentro);
        centroCusto.setDescricao(this.descricao);

        //Relacionamentos
        if(this.congregacaoId != null){
            Congregacao congregacao = new Congregacao();
            congregacao.setCongregacaoId(this.congregacaoId);
            centroCusto.setCongregacao(congregacao);
        }

        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            centroCusto.setSituacao(situacao);
        }

        return centroCusto;
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
