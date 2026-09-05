package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoConta;

public class ContaFinanceiraDropdownDTO {

    private Long contaFinanceiraId;
    private String descricao;
    private String congregacao;
    private String centroCusto;
    private TipoConta tipoConta;

    public ContaFinanceiraDropdownDTO(){}

    public ContaFinanceiraDropdownDTO(
            Long contaFinanceiraId,
            String descricao,
            String congregacao,
            String centroCusto,
            TipoConta tipoConta
    ){
        this.contaFinanceiraId = contaFinanceiraId;
        this.descricao = descricao;
        this.congregacao = congregacao;
        this.centroCusto = centroCusto;
        this.tipoConta = tipoConta;
    }

    public Long getContaFinanceiraId() {
        return contaFinanceiraId;
    }

    public void setContaFinanceiraId(Long contaFinanceiraId) {
        this.contaFinanceiraId = contaFinanceiraId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
}
