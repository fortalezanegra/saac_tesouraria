package br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.models.TransferenciaFinanceira;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaFinanceiraEditDTO {

    private Long transferenciaFinanceiraId;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    private String descricao;
    private String codigoTransacao;
    private Integer situacaoId;

    public static TransferenciaFinanceiraEditDTO fromTransferenciaFinanceira(TransferenciaFinanceira t){

        TransferenciaFinanceiraEditDTO dto = new TransferenciaFinanceiraEditDTO();

        dto.transferenciaFinanceiraId = t.getTransferenciaFinanceiraId();
        dto.contaOrigemId = t.getContaOrigem() != null ? t.getContaOrigem().getContaFinanceiraId() : null;
        dto.contaDestinoId = t.getContaDestino() != null ? t.getContaDestino().getContaFinanceiraId() : null;
        dto.valor = t.getValor();
        dto.dataTransferencia = t.getDataTransferencia();
        dto.descricao = t.getDescricao();
        dto.codigoTransacao = t.getCodigoTransacao();
        dto.situacaoId = t.getSituacao() != null ? t.getSituacao().getSituacaoId() : null;

        return dto;
    }

    public Long getTransferenciaFinanceiraId() {
        return transferenciaFinanceiraId;
    }

    public void setTransferenciaFinanceiraId(Long transferenciaFinanceiraId) {
        this.transferenciaFinanceiraId = transferenciaFinanceiraId;
    }

    public Long getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(Long contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public Long getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(Long contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }

    public void setCodigoTransacao(String codigoTransacao) {
        this.codigoTransacao = codigoTransacao;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
