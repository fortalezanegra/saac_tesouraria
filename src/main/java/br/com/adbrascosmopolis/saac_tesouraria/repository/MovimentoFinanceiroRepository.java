package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import br.com.adbrascosmopolis.saac_tesouraria.repository.projection.ContaFinanceiraSaldoProjection;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.MovimentoListagemProjection;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.TotaisCaixaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {

    @Query("""
        SELECT m
        FROM MovimentoFinanceiro m
        JOIN FETCH m.livroCaixa lc
        JOIN FETCH lc.centroCusto cc
        JOIN FETCH cc.congregacao c
        WHERE (:congregacaoId IS NULL OR c.congregacaoId = :congregacaoId)
          AND m.deletedAt IS NULL
        ORDER BY m.dataMovimento, m.movimentoFinanceiroId ASC
    """)
    List<MovimentoFinanceiro> listarMovimentosFiltrado(@Param("congregacaoId") Long congregacaoId);


    @Query("""
        SELECT
            COALESCE(
                SUM(
                    CASE
                        WHEN m.categoria.tipoCategoria = 'ENTRADA'
                        THEN m.valor
                        ELSE 0
                    END
                ),
                0
            ) AS totalEntradas,
            COALESCE(
                SUM(
                    CASE
                        WHEN m.categoria.tipoCategoria = 'SAIDA'
                        THEN m.valor
                        ELSE 0
                    END
                ),
                0
            ) AS totalSaidas
        FROM MovimentoFinanceiro m
        WHERE m.contaFinanceira.contaFinanceiraId = :contaFinanceiraId
          AND m.deletedAt IS NULL
    """)
    ContaFinanceiraSaldoProjection calcularSaldo(
            @Param("contaFinanceiraId") Long contaFinanceiraId
    );

    @Query("""
    SELECT
        COALESCE(SUM(CASE WHEN mf.categoria.tipoCategoria = 'ENTRADA' THEN mf.valor ELSE 0 END), 0) AS totalEntradas,
        COALESCE(SUM(CASE WHEN mf.categoria.tipoCategoria = 'SAIDA' THEN mf.valor ELSE 0 END), 0) AS totalSaidas,
        COALESCE(SUM(CASE WHEN mf.categoria.tipoCategoria = 'ENTRADA' AND mf.eletronico = true THEN mf.valor ELSE 0 END), 0) AS saldoEnviado
    FROM MovimentoFinanceiro mf
    WHERE mf.livroCaixa.livroCaixaId = :livroCaixaId
      AND mf.deletedAt IS NULL
    """)
    TotaisCaixaProjection buscarResumoFechamento(@Param("livroCaixaId") Long livroCaixaId);

    @Query("""
    SELECT
        mf.dataMovimento AS dataMovimento,
        c.descricao AS categoria,
        c.tipoCategoria AS tipoMovimento,
        mf.valor AS valor,
        mf.pessoa.nome AS nomeMembro
    FROM MovimentoFinanceiro mf
    JOIN mf.categoria c
    WHERE mf.livroCaixa.livroCaixaId = :livroCaixaId
      AND mf.deletedAt IS NULL
    ORDER BY
        CASE WHEN c.tipoCategoria = 'ENTRADA' THEN 0 ELSE 1 END,
        mf.dataMovimento ASC
    """)
    List<MovimentoListagemProjection> buscarMovimentosParaRelatorio(@Param("livroCaixaId") Long livroCaixaId);

}
