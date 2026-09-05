package br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro;

import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovimentoFinanceiroResponseDTO {

    private Long movimentoFinanceiroId;
    private String livroCaixa;
    private String contaFinanceira;
    private String categoria;
    private BigDecimal valor;
    private Boolean eletronico;
    private LocalDate dataMovimento;
    private String historico;
    private String responsavel;
    private String campanha;

    public MovimentoFinanceiroResponseDTO(
            Long movimentoFinanceiroId,
            String livroCaixa,
            String contaFinanceira,
            String categoria,
            BigDecimal valor,
            Boolean eletronico,
            LocalDate dataMovimento,
            String historico,
            String responsavel,
            String campanha
    ){
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.livroCaixa = livroCaixa;
        this.contaFinanceira = contaFinanceira;
        this.categoria = categoria;
        this.valor = valor;
        this.eletronico = eletronico;
        this.dataMovimento = dataMovimento;
        this.historico = historico;
        this.responsavel = responsavel;
        this.campanha = campanha;
    }

    public static MovimentoFinanceiroResponseDTO fromMovimentoFinanceiro(MovimentoFinanceiro m){

        if(m == null){
            return null;
        }

        String livroCaixa = null;

        if(m.getLivroCaixa() != null){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

            livroCaixa =
                    m.getLivroCaixa()
                            .getCentroCusto()
                            .getDescricao()
                            + " - "
                            + m.getLivroCaixa()
                            .getCompetencia()
                            .format(formatter);
        }

        return new MovimentoFinanceiroResponseDTO(
                m.getMovimentoFinanceiroId(),
                livroCaixa,
                m.getContaFinanceira() != null ? m.getContaFinanceira().getDescricao() : null,
                m.getCategoria() != null ? m.getCategoria().getDescricao() : null,
                m.getValor(),
                m.getEletronico(),
                m.getDataMovimento(),
                m.getHistorico(),
                m.getPessoa() != null ? m.getPessoa().getNome() : null,
                m.getCampanha() != null ? m.getCampanha().getDescricao() : null
        );
    }

    public Long getMovimentoFinanceiroId() {
        return movimentoFinanceiroId;
    }

    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
    }

    public String getLivroCaixa() {
        return livroCaixa;
    }

    public void setLivroCaixa(String livroCaixa) {
        this.livroCaixa = livroCaixa;
    }

    public String getContaFinanceira() {
        return contaFinanceira;
    }

    public void setContaFinanceira(String contaFinanceira) {
        this.contaFinanceira = contaFinanceira;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getEletronico() {
        return eletronico;
    }

    public void setEletronico(Boolean eletronico) {
        this.eletronico = eletronico;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }
}
