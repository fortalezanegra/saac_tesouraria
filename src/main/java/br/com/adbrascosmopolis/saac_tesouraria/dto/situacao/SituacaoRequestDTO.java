package br.com.adbrascosmopolis.saac_tesouraria.dto.situacao;

import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class SituacaoRequestDTO {

    private Integer situacaoId;
    private String descricao;
    private String contexto;
    private Boolean ativo;
    private Integer ordem;

    public SituacaoRequestDTO(
            Integer situacaoId,
            String descricao,
            String contexto,
            Boolean ativo,
            Integer ordem
    ){
        this.situacaoId = situacaoId;
        this.descricao = descricao;
        this.contexto = contexto;
        this.ativo = ativo;
        this.ordem = ordem;
    }

    public Situacao mapToSituacao(){
        Situacao situacao = new Situacao();

        situacao.setSituacaoId(this.situacaoId);
        situacao.setDescricao(this.descricao);
        situacao.setContexto(this.contexto);
        situacao.setAtivo(this.ativo);
        situacao.setOrdem(this.ordem);

        return situacao;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}
