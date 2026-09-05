package br.com.adbrascosmopolis.saac_tesouraria.dto.extrato_bancario;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import br.com.adbrascosmopolis.saac_tesouraria.models.ExtratoBancario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExtratoBancarioEditDTO {

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

    public static ExtratoBancarioEditDTO fromExtratoBancario(ExtratoBancario e){

        ExtratoBancarioEditDTO dto = new ExtratoBancarioEditDTO();

        dto.extratoBancarioId = e.getExtratoBancarioId();
        dto.contaFinanceiraId = e.getContaFinanceira() != null ? e.getContaFinanceira().getContaFinanceiraId() : null;
        dto.dataMovimento = e.getDataMovimento();
        dto.descricao = e.getDescricao();
        dto.valor = e.getValor();
        dto.tipoMovimento = e.getTipoMovimento();
        dto.numeroDocumento = e.getNumeroDocumento();
        dto.saldoConta = e.getSaldoConta();
        dto.hashMovimento = e.getHashMovimento();
        dto.importacaoExtratoId = e.getImportacaoExtrato() != null ? e.getImportacaoExtrato().getImportacaoExtratoId() : null;

        return dto;
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
