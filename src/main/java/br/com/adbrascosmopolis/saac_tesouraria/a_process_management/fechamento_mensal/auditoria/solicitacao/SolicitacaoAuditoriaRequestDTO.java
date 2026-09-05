package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.solicitacao;

public class SolicitacaoAuditoriaRequestDTO {

    private Long livroCaixaId;

    public SolicitacaoAuditoriaRequestDTO(Long livroCaixaId){
        this.livroCaixaId = livroCaixaId;
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }
}
