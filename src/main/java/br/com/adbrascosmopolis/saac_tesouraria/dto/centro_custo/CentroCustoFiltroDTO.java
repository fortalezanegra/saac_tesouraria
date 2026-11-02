package br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo;

public class CentroCustoFiltroDTO {

    private Integer congregacaoId;

    public CentroCustoFiltroDTO() {
    }

    public CentroCustoFiltroDTO(Integer congregacaoId){
        this.congregacaoId = congregacaoId;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }
}
