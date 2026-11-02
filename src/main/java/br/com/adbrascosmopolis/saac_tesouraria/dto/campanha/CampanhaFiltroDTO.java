package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

public class CampanhaFiltroDTO {

    private Integer centroCustoId;

    public CampanhaFiltroDTO(){}

    public CampanhaFiltroDTO(Integer centroCustoId){
        this.centroCustoId = centroCustoId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }
}
