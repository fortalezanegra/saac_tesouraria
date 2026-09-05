package br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;

public class LivroCaixaResponseDTO {

    private Long livroCaixaId;
    private String congregacao;
    private String centroCusto;
    private String competencia;
    private String label;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private LocalDateTime dataFechamento;
    private String situacao;

    public LivroCaixaResponseDTO(
            Long livroCaixaId,
            String congregacao,
            String centroCusto,
            String competencia,
            String label,
            BigDecimal saldoInicial,
            BigDecimal saldoFinal,
            LocalDateTime dataFechamento,
            String situacao
    ){
        this.livroCaixaId = livroCaixaId;
        this.congregacao = congregacao;
        this.centroCusto = centroCusto;
        this.competencia = competencia;
        this.label = centroCusto + " - " + competencia;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.dataFechamento = dataFechamento;
        this.situacao = situacao;
    }

    public static LivroCaixaResponseDTO fromLivroCaixa(LivroCaixa l){

        if(l == null){
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        return new LivroCaixaResponseDTO(
                l.getLivroCaixaId(),
                l.getCentroCusto() != null ? l.getCentroCusto().getCongregacao().getNome() : null,
                l.getCentroCusto() != null ? l.getCentroCusto().getDescricao() : null,
                l.getCompetencia() != null ? l.getCompetencia().format(formatter) : null,
                l.getLabel(),
                l.getSaldoInicial(),
                l.getSaldoFinal(),
                l.getDataFechamento(),
                l.getSituacao() != null ? l.getSituacao().getDescricao() : null
        );
    }

    public Long getLivroCaixaId() {
        return livroCaixaId;
    }

    public void setLivroCaixaId(Long livroCaixaId) {
        this.livroCaixaId = livroCaixaId;
    }

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String congregacao) {
        this.congregacao = congregacao;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
