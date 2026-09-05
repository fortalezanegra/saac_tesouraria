package br.com.adbrascosmopolis.saac_tesouraria.dto.campanha;

public class CampanhaDropdownDTO {
    
    private Long campanhaId;
    private String congregacao;
    private String centroCusto;
    private String descricao;

    public CampanhaDropdownDTO(){}

    public CampanhaDropdownDTO(
            Long campanhaId,
            String congregacao,
            String centroCusto,
            String descricao
    ){
        this.campanhaId = campanhaId;
        this.congregacao = congregacao;
        this.centroCusto = centroCusto;
        this.descricao = descricao;
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String congregacao) {
        this.congregacao = congregacao;
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
}
