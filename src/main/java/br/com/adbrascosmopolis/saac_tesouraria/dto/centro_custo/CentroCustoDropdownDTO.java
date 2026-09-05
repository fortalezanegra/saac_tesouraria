package br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo;

public class CentroCustoDropdownDTO {

    private Integer centroCustoId;
    private String descricao;

    public CentroCustoDropdownDTO(Integer centroCustoId, String descricao){
        this.centroCustoId = centroCustoId;
        this.descricao = descricao;
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
}
