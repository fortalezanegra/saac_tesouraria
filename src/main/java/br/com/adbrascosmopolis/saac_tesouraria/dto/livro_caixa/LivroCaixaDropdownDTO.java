package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

public class LivroCaixaDropdownDTO {

    private Long livroCaixaId;
    private String label;

    public LivroCaixaDropdownDTO(Long livroCaixaId, String label) {
        this.livroCaixaId = livroCaixaId;
        this.label = label;
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
