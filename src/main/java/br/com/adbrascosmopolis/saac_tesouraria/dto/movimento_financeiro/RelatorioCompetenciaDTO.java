package br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RelatorioCompetenciaDTO {

    private Long movimentoFinanceiroId;
    private LocalDate dataMovimento;
    private String contaFinanceiraDescricao;
    private String centroCustoDescricao;
    private String categoriaDescricao;
    private String tipoCategoria;
    private BigDecimal valor;
    private String historico;
    private String pessoaNome;
    private String campanhaDescricao;
    private Boolean eletronico;

    public RelatorioCompetenciaDTO(
            Long movimentoFinanceiroId,
            LocalDate dataMovimento,
            String contaFinanceiraDescricao,
            String centroCustoDescricao,
            String categoriaDescricao,
            String tipoCategoria,
            BigDecimal valor,
            String historico,
            String pessoaNome,
            String campanhaDescricao,
            Boolean eletronico
    ) {
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.dataMovimento = dataMovimento;
        this.contaFinanceiraDescricao = contaFinanceiraDescricao;
        this.centroCustoDescricao = centroCustoDescricao;
        this.categoriaDescricao = categoriaDescricao;
        this.tipoCategoria = tipoCategoria;
        this.valor = valor;
        this.historico = historico;
        this.pessoaNome = pessoaNome;
        this.campanhaDescricao = campanhaDescricao;
        this.eletronico = eletronico;
    }

    public Long getMovimentoFinanceiroId() { return movimentoFinanceiroId; }
    public LocalDate getDataMovimento() { return dataMovimento; }
    public String getContaFinanceiraDescricao() { return contaFinanceiraDescricao; }
    public String getCentroCustoDescricao() { return centroCustoDescricao; }
    public String getCategoriaDescricao() { return categoriaDescricao; }
    public String getTipoCategoria() { return tipoCategoria; }
    public BigDecimal getValor() { return valor; }
    public String getHistorico() { return historico; }
    public String getPessoaNome() { return pessoaNome; }
    public String getCampanhaDescricao() { return campanhaDescricao; }
    public Boolean getEletronico() { return eletronico; }
}
