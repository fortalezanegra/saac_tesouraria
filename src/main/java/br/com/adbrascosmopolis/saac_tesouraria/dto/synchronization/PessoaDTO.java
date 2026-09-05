package br.com.adbrascosmopolis.saac_tesouraria.dto.synchronization;

public class PessoaDTO {

    private Long pessoaId;
    private Integer tipoVinculoId;
    private Integer congregacaoId;
    private Integer cargoMinisterialId;
    private String nome;

    public PessoaDTO(
            Long pessoaId,
            Integer tipoVinculoId,
            Integer congregacaoId,
            Integer cargoMinisterialId,
            String nome
    ){
        this.pessoaId = pessoaId;
        this.tipoVinculoId = tipoVinculoId;
        this.congregacaoId = congregacaoId;
        this.cargoMinisterialId = cargoMinisterialId;
        this.nome = nome;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Integer getTipoVinculoId() {
        return tipoVinculoId;
    }

    public void setTipoVinculoId(Integer tipoVinculoId) {
        this.tipoVinculoId = tipoVinculoId;
    }

    public Integer getCongregacaoId() {
        return congregacaoId;
    }

    public void setCongregacaoId(Integer congregacaoId) {
        this.congregacaoId = congregacaoId;
    }

    public Integer getCargoMinisterialId() {
        return cargoMinisterialId;
    }

    public void setCargoMinisterialId(Integer cargoMinisterialId) {
        this.cargoMinisterialId = cargoMinisterialId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
