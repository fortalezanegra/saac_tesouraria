package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoConta;
import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaFinanceiraRequestDTO {

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

    public ContaFinanceiraRequestDTO(
            Long contaFinanceiraId,
            String descricao,
            Integer centroCustoId,
            Integer bancoId,
            TipoConta tipoConta,
            String agencia,
            String conta,
            String digito,
            BigDecimal saldoInicial,
            LocalDate dataAbertura,
            Integer situacaoId
    ){
        this.contaFinanceiraId = contaFinanceiraId;
        this.descricao = descricao;
        this.centroCustoId = centroCustoId;
        this.bancoId = bancoId;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.conta = conta;
        this.digito = digito;
        this.saldoInicial = saldoInicial;
        this.dataAbertura = dataAbertura;
        this.situacaoId = situacaoId;
    }

    public ContaFinanceira mapToContaFinanceira(){

        ContaFinanceira conta = new ContaFinanceira();

        conta.setDescricao(this.descricao);
        conta.setTipoConta(this.tipoConta);
        conta.setAgencia(this.agencia);
        conta.setConta(this.conta);
        conta.setDigito(this.digito);
        conta.setSaldoInicial(this.saldoInicial);
        conta.setDataAbertura(this.dataAbertura);

        //Relacionamentos
        if(this.centroCustoId != null){
            CentroCusto centro = new CentroCusto();
            centro.setCentroCustoId(this.centroCustoId);
            conta.setCentroCusto(centro);
        }

        if(this.bancoId != null){
            Banco banco = new Banco();
            banco.setBancoId(this.bancoId);
            conta.setBanco(banco );
        }

        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            conta.setSituacao(situacao);
        }

        return conta;
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
