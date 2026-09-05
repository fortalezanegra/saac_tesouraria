package br.com.adbrascosmopolis.saac_tesouraria.dto.situacao;

import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

public class SituacaoResponseDTO {

    private Integer situacaoId;
    private String descricao;
    private String contexto;
    private Boolean ativo;
    private Integer ordem;

    public SituacaoResponseDTO(
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

    public static SituacaoResponseDTO fromSituacao(Situacao s){
        if(s == null){
            return null;
        }

        return new SituacaoResponseDTO(
                s.getSituacaoId(),
                s.getDescricao(),
                s.getContexto(),
                s.getAtivo(),
                s.getOrdem()
        );
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
