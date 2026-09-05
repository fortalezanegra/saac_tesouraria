package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_pagar;

import br.com.adbrascosmopolis.saac_tesouraria.models.ContaPagar;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPagarResponseDTO {

    private Long contaPagarId;
    private String centroCusto;
    private String categoria;
    private String favorecido;
    private String descricao;
    private BigDecimal valorOriginal;
    private BigDecimal valorPago;
    private LocalDate dataEmissao;
    private LocalDate dataVencimeno;
    private LocalDate dataPagamento;
    private Long movimentoFinanceiroId;
    private String situacao;

    public ContaPagarResponseDTO(
            Long contaPagarId,
            String centroCusto,
            String categoria,
            String favorecido,
            String descricao,
            BigDecimal valorOriginal,
            BigDecimal valorPago,
            LocalDate dataEmissao,
            LocalDate dataVencimeno,
            LocalDate dataPagamento,
            Long movimentoFinanceiroId,
            String situacao
    ){
        this.contaPagarId = contaPagarId;
        this.centroCusto = centroCusto;
        this.categoria = categoria;
        this.favorecido = favorecido;
        this.descricao = descricao;
        this.valorOriginal = valorOriginal;
        this.valorPago = valorPago;
        this.dataEmissao = dataEmissao;
        this.dataVencimeno = dataVencimeno;
        this.dataPagamento = dataPagamento;
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.situacao = situacao;
    }

    public static ContaPagarResponseDTO fromContaPagar(ContaPagar c){

        if(c == null){
            return null;
        }

        return new ContaPagarResponseDTO(
                c.getContaPagarId(),
                c.getCentroCusto() != null ? c.getCentroCusto().getDescricao() : null,
                c.getCategoria() != null ? c.getCategoria().getDescricao() : null,
                c.getFavorecido() != null ? c.getFavorecido().getNome() : null,
                c.getDescricao(),
                c.getValorOriginal(),
                c.getValorPago(),
                c.getDataEmissao(),
                c.getDataVencimento(),
                c.getDataPagamento(),
                c.getMovimentoFinanceiro() != null ? c.getMovimentoFinanceiro().getMovimentoFinanceiroId() : null,
                c.getSituacao() != null ? c.getSituacao().getDescricao() : null
        );
    }

    public Long getContaPagarId() {
        return contaPagarId;
    }

    public void setContaPagarId(Long contaPagarId) {
        this.contaPagarId = contaPagarId;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
