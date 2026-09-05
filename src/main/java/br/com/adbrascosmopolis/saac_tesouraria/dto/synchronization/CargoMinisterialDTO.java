package br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization;

public class CargoMinisterialDTO {

    private Integer cargoMinisterialId;
    private String descricao;

    public CargoMinisterialDTO(Integer cargoMinisterialId, String descricao){
        this.cargoMinisterialId = cargoMinisterialId;
        this.descricao = descricao;
    }

    public Integer getCargoMinisterialId() {
        return cargoMinisterialId;
    }

    public void setCargoMinisterialId(Integer cargoMinisterialId) {
        this.cargoMinisterialId = cargoMinisterialId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
