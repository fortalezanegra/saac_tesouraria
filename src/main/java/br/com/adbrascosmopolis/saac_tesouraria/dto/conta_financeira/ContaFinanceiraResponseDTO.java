package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoConta;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaFinanceiraResponseDTO {

    private Long contaFinanceiraId;
    private String descricao;
    private String centroCusto;
    private String banco;
    private TipoConta tipoConta;
    private String agencia;
    private String conta;
    private String digito;
    private BigDecimal saldoInicial;
    private LocalDate dataAbertura;
    private String situacao;

    public ContaFinanceiraResponseDTO(
            Long contaFinanceiraId,
            String descricao,
            String centroCusto,
            String banco,
            TipoConta tipoConta,
            String agencia,
            String conta,
            String digito,
            BigDecimal saldoInicial,
            LocalDate dataAbertura,
            String situacao
    ){
        this.contaFinanceiraId = contaFinanceiraId;
        this.descricao = descricao;
        this.centroCusto = centroCusto;
        this.banco = banco;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.conta = conta;
        this.digito = digito;
        this.saldoInicial = saldoInicial;
        this.dataAbertura = dataAbertura;
        this.situacao = situacao;
    }

    public static ContaFinanceiraResponseDTO fromContaFinanceira(ContaFinanceira c){
        if(c == null){
            return null;
        }

        return new ContaFinanceiraResponseDTO(
                c.getContaFinanceiraId(),
                c.getDescricao(),
                c.getCentroCusto() != null ? c.getCentroCusto().getDescricao() : null,
                c.getBanco() != null ? c.getBanco().getNome() : null,
                c.getTipoConta(),
                c.getAgencia(),
                c.getConta(),
                c.getDigito(),
                c.getSaldoInicial(),
                c.getDataAbertura(),
                c.getSituacao() != null ? c.getSituacao().getDescricao() : null
        );
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

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
