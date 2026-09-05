package br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovimentoFinanceiroEditDTO {

    private Long movimentoFinanceiroId;
    private Long livroCaixaId;
    private Long contaFinanceiraId;
    private Integer categoriaId;
    private BigDecimal valor;
    private Boolean eletronico;
    private LocalDate dataMovimento;
    private String historico;
    private Long pessoaId;
    private Long campanhaId;

    public static MovimentoFinanceiroEditDTO fromMovimentoFinanceiro(MovimentoFinanceiro m){

        MovimentoFinanceiroEditDTO dto = new MovimentoFinanceiroEditDTO();

        dto.movimentoFinanceiroId = m.getMovimentoFinanceiroId();
        dto.livroCaixaId = m.getLivroCaixa() != null ? m.getLivroCaixa().getLivroCaixaId() : null;
        dto.contaFinanceiraId = m.getContaFinanceira() != null ? m.getContaFinanceira().getContaFinanceiraId() : null;
        dto.categoriaId = m.getCategoria() != null ? m.getCategoria().getCategoriaId() : null;
        dto.valor = m.getValor();
        dto.eletronico = m.getEletronico();
        dto.dataMovimento = m.getDataMovimento();
        dto.historico = m.getHistorico();
        dto.pessoaId = m.getPessoa() != null ? m.getPessoa().getPessoaId() : null;
        dto.campanhaId = m.getCampanha() != null ? m.getCampanha().getCampanhaId() : null;

        return dto;
    }

    public Long getMovimentoFinanceiroId() {
        return movimentoFinanceiroId;
    }

    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public Long getContaFinanceiraId() {
        return contaFinanceiraId;
    }

    public void setContaFinanceiraId(Long contaFinanceiraId) {
        this.contaFinanceiraId = contaFinanceiraId;
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

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }
}
