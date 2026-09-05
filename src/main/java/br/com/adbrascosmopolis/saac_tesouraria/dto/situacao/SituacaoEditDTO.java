package br.com.adbrascosmopolis.saac_tesouraria.dto.situacao;

import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class SituacaoEditDTO {

    private Integer situacaoId;
    private String descricao;
    private String contexto;
    private Boolean ativo;
    private Integer ordem;

    public static SituacaoEditDTO fromSituacao(Situacao s){

        SituacaoEditDTO dto = new SituacaoEditDTO();

        dto.situacaoId = s.getSituacaoId();
        dto.descricao = s.getDescricao();
        dto.contexto = s.getContexto();
        dto.ativo = s.getAtivo();
        dto.ordem = s.getOrdem();

        return dto;
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
