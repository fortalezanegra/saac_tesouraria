package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LivroCaixaRequestDTO {

    private Long livroCaixaId;
    private Integer centroCustoId;
    private LocalDate competencia;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private LocalDateTime dataFechamento;
    private Integer situacaoId;

    public LivroCaixaRequestDTO(
            Long livroCaixaId,
            Integer centroCustoId,
            LocalDate competencia,
            BigDecimal saldoInicial,
            BigDecimal saldoFinal,
            LocalDateTime dataFechamento,
            Integer situacaoId
    ){
        this.livroCaixaId = livroCaixaId;
        this.centroCustoId = centroCustoId;
        this.competencia = competencia;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.dataFechamento = dataFechamento;
        this.situacaoId = situacaoId;
    }

    public LivroCaixa mapToLivroCaixa(){

        LivroCaixa livro = new LivroCaixa();

        livro.setCompetencia(this.competencia);
        livro.setSaldoInicial(this.saldoInicial);
        livro.setSaldoFinal(this.saldoFinal);
        livro.setDataFechamento(this.dataFechamento);

        //Relacionamentos
        if(this.centroCustoId != null){
            CentroCusto centro = new CentroCusto();
            centro.setCentroCustoId(this.centroCustoId);
            livro.setCentroCusto(centro);
        }

        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            livro.setSituacao(situacao);
        }

        return livro;
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public Integer getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Integer centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public LocalDate getCompetencia() {
        return competencia;
    }

    public void setCompetencia(LocalDate competencia) {
        this.competencia = competencia;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getSituacaoId() {
        return situacaoId;
    }

    public void setSituacaoId(Integer situacaoId) {
        this.situacaoId = situacaoId;
    }
}
