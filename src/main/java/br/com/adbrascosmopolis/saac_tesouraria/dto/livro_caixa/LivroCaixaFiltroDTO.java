package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

public class LivroCaixaFiltroDTO {

    private Integer centroCustoId;

    public LivroCaixaFiltroDTO(){}

    public LivroCaixaFiltroDTO(Integer centroCustoId){
        this.centroCustoId = centroCustoId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }
}
