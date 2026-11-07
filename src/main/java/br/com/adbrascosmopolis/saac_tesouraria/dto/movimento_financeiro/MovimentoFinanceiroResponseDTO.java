package br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro;

import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovimentoFinanceiroResponseDTO {

    private Long movimentoFinanceiroId;
    private Long livroCaixaId;
    private String livroCaixa;
    private Long contaFinanceiraId;
    private String contaFinanceira;
    private Integer categoriaId;
    private String categoria;
    private BigDecimal valor;
    private Boolean eletronico;
    private LocalDate dataMovimento;
    private String historico;
    private Long pessoaId;
    private String pessoaNome;
    private Long campanhaId;
    private String campanha;

    public MovimentoFinanceiroResponseDTO(
            Long movimentoFinanceiroId,
            Long livroCaixaId,
            String livroCaixa,
            Long contaFinanceiraId,
            String contaFinanceira,
            Integer categoriaId,
            String categoria,
            BigDecimal valor,
            Boolean eletronico,
            LocalDate dataMovimento,
            String historico,
            Long pessoaId,
            String pessoaNome,
            Long campanhaId,
            String campanha
    ){
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.livroCaixaId = livroCaixaId;
        this.livroCaixa = livroCaixa;
        this.contaFinanceiraId = contaFinanceiraId;
        this.contaFinanceira = contaFinanceira;
        this.categoriaId = categoriaId;
        this.categoria = categoria;
        this.valor = valor;
        this.eletronico = eletronico;
        this.dataMovimento = dataMovimento;
        this.historico = historico;
        this.pessoaId = pessoaId;
        this.pessoaNome = pessoaNome;
        this.campanhaId = campanhaId;
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
                m.getLivroCaixa() != null ? m.getLivroCaixa().getLivroCaixaId() : null,
                livroCaixa,
                m.getContaFinanceira() != null ? m.getContaFinanceira().getContaFinanceiraId() : null,
                m.getContaFinanceira() != null ? m.getContaFinanceira().getDescricao() : null,
                m.getCategoria() != null ? m.getCategoria().getCategoriaId() : null,
                m.getCategoria() != null ? m.getCategoria().getDescricao() : null,
                m.getValor(),
                m.getEletronico(),
                m.getDataMovimento(),
                m.getHistorico(),
                m.getPessoa() != null ? m.getPessoa().getPessoaId() : null,
                m.getPessoa() != null ? m.getPessoa().getNome() : null,
                m.getCampanha() != null ? m.getCampanha().getCampanhaId() : null,
                m.getCampanha() != null ? m.getCampanha().getDescricao() : null
        );
    }

    public Long getMovimentoFinanceiroId() { return movimentoFinanceiroId; }
    public void setMovimentoFinanceiroId(Long movimentoFinanceiroId) { this.movimentoFinanceiroId = movimentoFinanceiroId; }

    public Long getLivroCaixaId() { return livroCaixaId; }
    public void setLivroCaixaId(Long livroCaixaId) { this.livroCaixaId = livroCaixaId; }

    public String getLivroCaixa() { return livroCaixa; }
    public void setLivroCaixa(String livroCaixa) { this.livroCaixa = livroCaixa; }

    public Long getContaFinanceiraId() { return contaFinanceiraId; }
    public void setContaFinanceiraId(Long contaFinanceiraId) { this.contaFinanceiraId = contaFinanceiraId; }

    public String getContaFinanceira() { return contaFinanceira; }
    public void setContaFinanceira(String contaFinanceira) { this.contaFinanceira = contaFinanceira; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Boolean getEletronico() { return eletronico; }
    public void setEletronico(Boolean eletronico) { this.eletronico = eletronico; }

    public LocalDate getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDate dataMovimento) { this.dataMovimento = dataMovimento; }

    public String getHistorico() { return historico; }
    public void setHistorico(String historico) { this.historico = historico; }

    public Long getPessoaId() { return pessoaId; }
    public void setPessoaId(Long pessoaId) { this.pessoaId = pessoaId; }

    public String getPessoaNome() { return pessoaNome; }
    public void setPessoaNome(String pessoaNome) { this.pessoaNome = pessoaNome; }

    public Long getCampanhaId() { return campanhaId; }
    public void setCampanhaId(Long campanhaId) { this.campanhaId = campanhaId; }

    public String getCampanha() { return campanha; }
    public void setCampanha(String campanha) { this.campanha = campanha; }
}
