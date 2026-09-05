package br.com.adbrascosmopolis.saac_tesouraria.dto.conta_pagar;

import br.com.adbrascosmopolis.saac_tesouraria.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPagarRequestDTO {

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

    public ContaPagarRequestDTO(
            Long contaPagarId,
            Integer centroCustoId,
            Integer categoriaId,
            Long favorecidoId,
            String descricao,
            BigDecimal valorOriginal,
            BigDecimal valorPago,
            LocalDate dataEmissao,
            LocalDate dataVencimeno,
            LocalDate dataPagamento,
            Long movimentoFinanceiroId,
            Integer situacaoId
    ){
        this.contaPagarId = contaPagarId;
        this.centroCustoId = centroCustoId;
        this.categoriaId = categoriaId;
        this.favorecidoId = favorecidoId;
        this.descricao = descricao;
        this.valorOriginal = valorOriginal;
        this.valorPago = valorPago;
        this.dataEmissao = dataEmissao;
        this.dataVencimeno = dataVencimeno;
        this.dataPagamento = dataPagamento;
        this.movimentoFinanceiroId = movimentoFinanceiroId;
        this.situacaoId = situacaoId;
    }

    public ContaPagar mapToContaPagar(ContaPagar c){
        ContaPagar conta = new ContaPagar();

        conta.setDescricao(this.descricao);
        conta.setValorOriginal(this.valorOriginal);
        conta.setValorPago(this.valorPago);
        conta.setDataEmissao(this.dataEmissao);
        conta.setDataVencimento(this.dataVencimeno);
        conta.setDataPagamento(this.dataPagamento);

        //Relacionamentps
        if(this.centroCustoId != null){
            CentroCusto centro = new CentroCusto();
            centro.setCentroCustoId(this.centroCustoId);
            conta.setCentroCusto(centro);
        }

        if(this.categoriaId != null){
            Categoria categoria = new Categoria();
            categoria.setCategoriaId(this.categoriaId);
            conta.setCategoria(categoria);
        }

        if(this.favorecidoId != null){
            Favorecido favorecido = new Favorecido();
            favorecido.setFavorecidoId(this.favorecidoId);
            conta.setFavorecido(favorecido);
        }

        if(this.movimentoFinanceiroId != null){
            MovimentoFinanceiro movimento = new MovimentoFinanceiro();
            movimento.setMovimentoFinanceiroId(this.movimentoFinanceiroId);
            conta.setMovimentoFinanceiro(movimento);
        }

        if(this.situacaoId != null){
            Situacao situacao = new Situacao();
            situacao.setSituacaoId(this.situacaoId);
            conta.setSituacao(situacao);
        }

        return conta;
    }

}
