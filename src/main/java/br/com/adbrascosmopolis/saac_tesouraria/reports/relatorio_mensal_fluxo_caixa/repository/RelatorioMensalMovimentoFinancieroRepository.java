package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository;

import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelatorioMensalMovimentoFinancieroRepository extends JpaRepository<MovimentoFinanceiro, Long> {

    //Calculo de Totais Relatorio Mensal Fluxo Caixa
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


    //Relatorio Mensal Fluxo de Caixa - ordenado cronologicamente para permitir o cálculo do saldo acumulado
    @Query("""
    SELECT
        mf.movimentoFinanceiroId AS movimentoFinanceiroId,
        mf.dataMovimento AS dataMovimento,
        c.descricao AS categoria,
        c.tipoCategoria AS tipoMovimento,
        mf.valor AS valor,
        mf.historico AS historico,
        p.nome AS responsavel
    FROM MovimentoFinanceiro mf
    JOIN mf.categoria c
    LEFT JOIN mf.pessoa p
    WHERE mf.livroCaixa.livroCaixaId = :livroCaixaId
      AND mf.deletedAt IS NULL
    ORDER BY
        mf.dataMovimento ASC,
        mf.movimentoFinanceiroId ASC
    """)
    List<MovimentoListagemProjection> buscarMovimentosParaRelatorio(@Param("livroCaixaId") Long livroCaixaId);

}
