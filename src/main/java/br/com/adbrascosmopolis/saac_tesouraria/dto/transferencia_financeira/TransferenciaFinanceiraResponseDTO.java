package br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.models.TransferenciaFinanceira;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransferenciaFinanceiraResponseDTO {

    private Long transferenciaFinanceiraId;
    private String descricao;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;
    private String codigoTransacao;
    private String situacao;

    public TransferenciaFinanceiraResponseDTO(
            Long transferenciaFinanceiraId,
            String descricao,
            String contaOrigem,
            String contaDestino,
            BigDecimal valor,
            LocalDateTime dataTransferencia,
            String codigoTransacao,
            String situacao
    ){
        this.transferenciaFinanceiraId = transferenciaFinanceiraId;
        this.descricao = descricao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
        this.codigoTransacao = codigoTransacao;
        this.situacao = situacao;
    }

    public static TransferenciaFinanceiraResponseDTO fromTransferenciaFinanceira(TransferenciaFinanceira t){

        if(t == null){
            return null;
        }

        return new TransferenciaFinanceiraResponseDTO(
                t.getTransferenciaFinanceiraId(),
                t.getDescricao(),
                t.getContaOrigem() != null ? t.getContaOrigem().getDescricao() : null,
                t.getContaDestino() != null ? t.getContaDestino().getDescricao() : null,
                t.getValor(),
                t.getDataTransferencia(),
                t.getCodigoTransacao(),
                t.getSituacao() != null ? t.getSituacao().getDescricao() : null
        );
    }

    public Long getTransferenciaFinanceiraId() {
        return transferenciaFinanceiraId;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public String getCodigoTransacao() {
        return codigoTransacao;
    }

    public String getSituacao() {
        return situacao;
    }
}
