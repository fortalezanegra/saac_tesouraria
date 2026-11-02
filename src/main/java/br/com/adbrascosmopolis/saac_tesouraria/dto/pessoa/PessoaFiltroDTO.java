package br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa;

public class PessoaFiltroDTO {

    private Integer congregacaoId;

    public PessoaFiltroDTO(){}

    public PessoaFiltroDTO(Integer congregacaoId){
        this.congregacaoId = congregacaoId;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }
}
