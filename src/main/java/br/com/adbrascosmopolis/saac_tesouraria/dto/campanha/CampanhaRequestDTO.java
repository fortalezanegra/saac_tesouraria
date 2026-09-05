package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampanhaRequestDTO {

    private Integer centroCustoId;
    private String descricao;
    private BigDecimal metaValor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer situacaoId;

    public CampanhaRequestDTO(
            Integer centroCustoId,
            String descricao,
            BigDecimal metaValor,
            LocalDate dataInicio,
            LocalDate dataFim,
            Integer situacaoId
    ){
        this.centroCustoId = centroCustoId;
        this.descricao = descricao;
        this.metaValor = metaValor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.situacaoId = situacaoId;
    }

    public Campanha mapToCampanha(){
        Campanha campanha = new Campanha();

        campanha.setDescricao(this.descricao);
        campanha.setMetavalor(this.metaValor);
        campanha.setDataInicio(this.dataInicio);
        campanha.setDataFim(this.dataFim);

        //Relacionamentos
        if(this.centroCustoId != null){
            CentroCusto centroCusto = new CentroCusto();
            centroCusto.setCentroCustoId(this.centroCustoId);
            campanha.setCentroCusto(centroCusto);
        }

        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            campanha.setSituacao(situacao);
        }

        return campanha;
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
