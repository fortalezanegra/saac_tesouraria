package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_pagar;

import br.com.adbrascosmopolis.saac_tesouraria.models.ContaPagar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPagarEditDTO {

    private Long contaPagarId;
    private Integer centroCustoId;
    private Integer categoriaId;
    private Long favorecidoId;
    private String descricao;
    private BigDecimal valorOriginal;
    private BigDecimal valorPago;
    private LocalDate dataEmissao;
    private LocalDate dataVencimeno;
    private LocalDate dataPagamento;
    private Long movimentoFinanceiroId;
    private Integer situacaoId;

    public static ContaPagarEditDTO fromContaPagar(ContaPagar c){

        ContaPagarEditDTO dto = new ContaPagarEditDTO();

        dto.contaPagarId = c.getContaPagarId();
        dto.centroCustoId = c.getCentroCusto() != null ? c.getCentroCusto().getCentroCustoId() : null;
        dto.categoriaId = c.getCategoria() != null ? c.getCategoria().getCategoriaId() : null;
        dto.favorecidoId = c.getFavorecido() != null ? c.getFavorecido().getFavorecidoId() : null;
        dto.descricao = c.getDescricao();
        dto.valorOriginal = c.getValorOriginal();
        dto.valorPago = c.getValorPago();
        dto.dataEmissao = c.getDataEmissao();
        dto.dataVencimeno = c.getDataVencimento();
        dto.dataPagamento = c.getDataPagamento();
        dto.movimentoFinanceiroId = c.getMovimentoFinanceiro() != null ? c.getMovimentoFinanceiro().getMovimentoFinanceiroId() : null;
        dto.situacaoId = c.getSituacao() != null ? c.getSituacao().getSituacaoId() : null;

        return dto;
    }

    public Long getContaPagarId() {
        return contaPagarId;
    }

    public void setContaPagarId(Long contaPagarId) {
        this.contaPagarId = contaPagarId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getFavorecidoId() {
        return favorecidoId;
    }

    public void setFavorecidoId(Long favorecidoId) {
        this.favorecidoId = favorecidoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataVencimeno() {
        return dataVencimeno;
    }

    public void setDataVencimeno(LocalDate dataVencimeno) {
        this.dataVencimeno = dataVencimeno;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getMovimentoFinanceiroId() {
        return movimentoFinanceiroId;
    }

    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
