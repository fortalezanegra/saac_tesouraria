package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CampanhaResponseDTO {

    private Long campanhaId;
    private String centroCusto;
    private String descricao;
    private BigDecimal metaValor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String situacao;

    public CampanhaResponseDTO(
            Long campanhaId,
            String centroCusto,
            String descricao,
            BigDecimal metaValor,
            LocalDate dataInicio,
            LocalDate dataFim,
            String situacao
    ){
        this.campanhaId = campanhaId;
        this.centroCusto= centroCusto;
        this.descricao = descricao;
        this.metaValor = metaValor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.situacao = situacao;
    }

    public static CampanhaResponseDTO fromCampanha(Campanha c){

        if(c == null){
            return null;
        }

        return new CampanhaResponseDTO(
                c.getCampanhaId(),
                c.getCentroCusto() != null ? c.getCentroCusto().getDescricao() : null,
                c.getDescricao(),
                c.getMetavalor(),
                c.getDataInicio(),
                c.getDataFim(),
                c.getSituacao() != null ? c.getSituacao().getDescricao() : null
        );
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
