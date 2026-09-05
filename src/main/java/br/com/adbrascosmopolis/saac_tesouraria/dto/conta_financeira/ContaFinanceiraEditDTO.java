package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoConta;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaFinanceiraEditDTO{

    private Long contaFinanceiraId;
    private String descricao;
    private Integer centroCustoId;
    private Integer bancoId;
    private TipoConta tipoConta;
    private String agencia;
    private String conta;
    private String digito;
    private BigDecimal saldoInicial;
    private LocalDate dataAbertura;
    private Integer situacaoId;

    public static ContaFinanceiraEditDTO fromContaFinanceira(ContaFinanceira c){
        ContaFinanceiraEditDTO dto = new ContaFinanceiraEditDTO();

        dto.contaFinanceiraId = c.getContaFinanceiraId();
        dto.descricao = c.getDescricao();
        dto.centroCustoId = c.getCentroCusto() != null ? c.getCentroCusto().getCentroCustoId() : null;
        dto.bancoId = c.getBanco() != null ? c.getBanco().getBancoId() : null;
        dto.tipoConta = c.getTipoConta();
        dto.agencia = c.getAgencia();
        dto.conta = c.getConta();
        dto.digito = c.getDigito();
        dto.saldoInicial = c.getSaldoInicial();
        dto.dataAbertura = c.getDataAbertura();
        dto.situacaoId = c.getSituacao() != null ? c.getSituacao().getSituacaoId() : null;

        return dto;
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

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
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

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
