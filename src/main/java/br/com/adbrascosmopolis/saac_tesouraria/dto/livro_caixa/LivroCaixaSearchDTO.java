package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

import java.time.LocalDate;

public class LivroCaixaSearchDTO {
    private Integer congregacaoId;
    private Integer situacaoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public LivroCaixaSearchDTO(){}

    public LivroCaixaSearchDTO(
            Integer congregacaoId,
            Integer situacaoId,
            LocalDate dataInicio,
            LocalDate dataFim
    ){
        this.congregacaoId = congregacaoId;
        this. situacaoId = situacaoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
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
}
