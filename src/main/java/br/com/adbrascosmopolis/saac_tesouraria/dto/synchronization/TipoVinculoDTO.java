package br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization;

public class TipoVinculoDTO {

    private Integer tipoVinculoId;
    private String descricao;

    public TipoVinculoDTO(Integer tipoVinculoId, String descricao){
        this.tipoVinculoId = tipoVinculoId;
        this.descricao = descricao;
    }

    public Integer getTipoVinculoId() {
        return tipoVinculoId;
    }

    public void setTipoVinculoId(Integer tipoVinculoId) {
        this.tipoVinculoId = tipoVinculoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
