package br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaFinanceiraRequestDTO {

    private String descricao;
    private Long contaFinanceiraOrigemId;
    private Long contaFinanceiraDestinoId;
    private Long livroCaixaOrigemId;
    private Long livroCaixaDestinoId;
    private Integer categoriaId;
    private BigDecimal valor;
    private String observacao;
    private LocalDateTime dataTransferencia;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getContaFinanceiraOrigemId() {
        return contaFinanceiraOrigemId;
    }

    public void setContaFinanceiraOrigemId(Long contaFinanceiraOrigemId) {
        this.contaFinanceiraOrigemId = contaFinanceiraOrigemId;
    }

    public Long getContaFinanceiraDestinoId() {
        return contaFinanceiraDestinoId;
    }

    public void setContaFinanceiraDestinoId(Long contaFinanceiraDestinoId) {
        this.contaFinanceiraDestinoId = contaFinanceiraDestinoId;
    }

    public Long getLivroCaixaOrigemId() {
        return livroCaixaOrigemId;
    }

    public void setLivroCaixaOrigemId(Long livroCaixaOrigemId) {
        this.livroCaixaOrigemId = livroCaixaOrigemId;
    }

    public Long getLivroCaixaDestinoId() {
        return livroCaixaDestinoId;
    }

    public void setLivroCaixaDestinoId(Long livroCaixaDestinoId) {
        this.livroCaixaDestinoId = livroCaixaDestinoId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDateTime getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDateTime dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }
}
