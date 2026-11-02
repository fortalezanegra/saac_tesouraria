package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

public class CampanhaDropdownDTO {
    
    private Long campanhaId;
    private String descricao;

    public CampanhaDropdownDTO(){}

    public CampanhaDropdownDTO(
            Long campanhaId,
            String descricao
    ){
        this.campanhaId = campanhaId;
        this.descricao = descricao;
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
