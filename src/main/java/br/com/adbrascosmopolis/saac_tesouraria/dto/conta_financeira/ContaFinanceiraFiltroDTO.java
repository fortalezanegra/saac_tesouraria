package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

public class ContaFinanceiraFiltroDTO {

    private Integer centroCustoId;

    public ContaFinanceiraFiltroDTO(){}

    public ContaFinanceiraFiltroDTO(Integer centroCustoId){
        this.centroCustoId = centroCustoId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }
}
