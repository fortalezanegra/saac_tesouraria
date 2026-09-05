package br.com.adbrascosmopolis.saac_tesouraria.dto.extrato_bancario;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.models.ExtratoBancario;
import br.com.adbrascosmopolis.saac_tesouraria.models.ImportacaoExtrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExtratoBancarioRequestDTO {

    private Long extratoBancarioId;
    private Long contaFinanceiraId;
    private LocalDate dataMovimento;
    private String descricao;
    private BigDecimal valor;
    private TipoMovimento tipoMovimento;
    private String numeroDocumento;
    private BigDecimal saldoConta;
    private String hashMovimento;
    private Long importacaoExtratoId;

    public ExtratoBancarioRequestDTO(
            Long extratoBancarioId,
            Long contaFinanceiraId,
            LocalDate dataMovimento,
            String descricao,
            BigDecimal valor,
            TipoMovimento tipoMovimento,
            String numeroDocumento,
            BigDecimal saldoConta,
            String hashMovimento,
            Long importacaoExtratoId
    ){
        this.extratoBancarioId = extratoBancarioId;
        this.contaFinanceiraId = contaFinanceiraId;
        this.dataMovimento = dataMovimento;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoMovimento = tipoMovimento;
        this.numeroDocumento = numeroDocumento;
        this.saldoConta = saldoConta;
        this.hashMovimento = hashMovimento;
        this.importacaoExtratoId = importacaoExtratoId;
    }

    public ExtratoBancario mapToExtratoBancario(){
        ExtratoBancario extrato = new ExtratoBancario();

        extrato.setDataMovimento(this.dataMovimento);
        extrato.setDescricao(this.descricao);
        extrato.setValor(this.valor);
        extrato.setTipoMovimento(this.tipoMovimento);
        extrato.setNumeroDocumento(this.numeroDocumento);
        extrato.setSaldoConta(this.saldoConta);
        extrato.setHashMovimento(this.hashMovimento);

        //Relacionamentos
        if(this.contaFinanceiraId != null){
            ContaFinanceira conta = new ContaFinanceira();
            conta.setContaFinanceiraId(this.contaFinanceiraId);
            extrato.setContaFinanceira(conta);
        }

        if(this.importacaoExtratoId != null){
            ImportacaoExtrato importacao = new ImportacaoExtrato();
            importacao.setImportacaoExtratoId(this.importacaoExtratoId);
            extrato.setImportacaoExtrato(importacao);
        }

        return extrato;
    }

    public Long getExtratoBancarioId() {
        return extratoBancarioId;
    }

    public void setExtratoBancarioId(Long extratoBancarioId) {
        this.extratoBancarioId = extratoBancarioId;
    }

    public Long getContaFinanceiraId() {
        return contaFinanceiraId;
    }

    public void setContaFinanceiraId(Long contaFinanceiraId) {
        this.contaFinanceiraId = contaFinanceiraId;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getHashMovimento() {
        return hashMovimento;
    }

    public void setHashMovimento(String hashMovimento) {
        this.hashMovimento = hashMovimento;
    }

    public Long getImportacaoExtratoId() {
        return importacaoExtratoId;
    }

    public void setImportacaoExtratoId(Long importacaoExtratoId) {
        this.importacaoExtratoId = importacaoExtratoId;
    }
}
