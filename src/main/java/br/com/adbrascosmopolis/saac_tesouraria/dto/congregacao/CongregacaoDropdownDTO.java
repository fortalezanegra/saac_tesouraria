package br.com.adbrascosmopolis.saac_tesouraria.dto.congregacao;

public class CongregacaoDropdownDTO {

    private Integer congregacaoId;
    private String nome;

    public CongregacaoDropdownDTO(Integer congregacaoId, String nome){
        this.congregacaoId = congregacaoId;
        this.nome = nome;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
