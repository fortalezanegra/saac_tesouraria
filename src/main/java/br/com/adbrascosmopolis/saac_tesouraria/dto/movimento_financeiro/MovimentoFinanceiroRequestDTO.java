package br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro;

import br.com.adbrascosmopolis.saac_tesouraria.models.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoFinanceiroRequestDTO {

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

    public MovimentoFinanceiroRequestDTO(
            Long movimentoFinanceiroId,
            Long livroCaixaId,
            Long contaFinanceiraId,
            Integer categoriaId,
            BigDecimal valor,
            Boolean eletronico,
            LocalDate dataMovimento,
            String historico,
            Long pessoaId,
            Long campanhaId
    ){
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.livroCaixaId = livroCaixaId;
        this.contaFinanceiraId = contaFinanceiraId;
        this.categoriaId = categoriaId;
        this.valor = valor;
        this.eletronico = eletronico;
        this.dataMovimento = dataMovimento;
        this.historico = historico;
        this.pessoaId = pessoaId;
        this.campanhaId = campanhaId;
    }

    public MovimentoFinanceiro mapToMovimentoFinanceiro(){

        MovimentoFinanceiro mov = new MovimentoFinanceiro();

        mov.setValor(this.valor);
        mov.setEletronico(this.eletronico);
        mov.setDataMovimento(this.dataMovimento);
        mov.setHistorico(this.historico);

        //Relacionamentos
        if(this.livroCaixaId != null){
            LivroCaixa livro = new LivroCaixa();
            livro.setLivroCaixaId(this.livroCaixaId);
            mov.setLivroCaixa(livro);
        }

        if(this.contaFinanceiraId != null){
            ContaFinanceira conta = new ContaFinanceira();
            conta.setContaFinanceiraId(this.contaFinanceiraId);
            mov.setContaFinanceira(conta);
        }

        if(this.categoriaId != null){
            Categoria categoria = new Categoria();
            categoria.setCategoriaId(this.categoriaId);
            mov.setCategoria(categoria);
        }

        if(this.pessoaId != null){
            Pessoa pessoa = new Pessoa();
            pessoa.setPessoaId(this.pessoaId);
            mov.setPessoa(pessoa);
        }

        if(this.campanhaId != null){
            Campanha campanha = new Campanha();
            campanha.setCampanhaId(this.campanhaId);
            mov.setCampanha(campanha);
        }

        return mov;
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

    public Long getCampanhaId() { return campanhaId; }

    public void setCampanhaId(Long campanhaId) { this.campanhaId = campanhaId; }
}
