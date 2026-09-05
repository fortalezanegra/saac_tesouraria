package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LivroCaixaRepository extends JpaRepository<LivroCaixa, Long> {

    //FindByLivroCaixaId
    LivroCaixa findByLivroCaixaId(Long livroCaixaId);

    //Listar Todos os Livros
    @Query("""
        SELECT l
        FROM LivroCaixa l
        ORDER BY l.centroCusto.congregacao.nome ASC, l.centroCusto.descricao ASC, l.competencia DESC
    """)
    List<LivroCaixa> listarLivros();

    //Listar Livros por Congregação, Situacao e Período
    @Query("""
        SELECT l
        FROM LivroCaixa l
        WHERE l.deletedAt IS NULL
        AND l.situacao.situacaoId = :situacaoId
        AND l.competencia BETWEEN :dataInicio AND :dataFim
        ORDER BY l.centroCusto.congregacao.nome ASC, l.centroCusto.descricao ASC, l.competencia DESC
    """)
    List<LivroCaixa> listarLivrosPorSituacaoEPeriodo(
            @Param("situacaoId") Integer situacaoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    //Listar Livros por Congregação
    @Query("""
        SELECT l
        FROM LivroCaixa l
        WHERE l.deletedAt IS NULL
        AND l.centroCusto.congregacao.congregacaoId = :congregacaoId
        ORDER BY l.centroCusto.congregacao.nome ASC, l.centroCusto.descricao ASC, l.competencia DESC
    """)
    List<LivroCaixa> listarLivrosPorCongregacao(@Param("congregacaoId") Integer congregacaoId);

    //Listar Livros por Congregação e Situacao
    @Query("""
        SELECT l
        FROM LivroCaixa l
        WHERE l.deletedAt IS NULL
        AND l.centroCusto.congregacao.congregacaoId = :congregacaoId
        AND l.situacao.situacaoId = :situacaoId
        ORDER BY l.centroCusto.congregacao.nome ASC, l.centroCusto.descricao ASC, l.competencia DESC
    """)
    List<LivroCaixa> listarLivrosPorCongregacaoESituacao(
            @Param("congregacaoId") Integer congregacaoId,
            @Param("situacaoId") Integer situacaoId
    );

    //Listar Livros por Congregação, Situacao e Período
    @Query("""
        SELECT l
        FROM LivroCaixa l
        WHERE l.deletedAt IS NULL
        AND l.centroCusto.congregacao.congregacaoId = :congregacaoId
        AND l.situacao.situacaoId = :situacaoId
        AND l.competencia BETWEEN :dataInicio AND :dataFim
        ORDER BY l.centroCusto.congregacao.nome ASC, l.centroCusto.descricao ASC, l.competencia DESC
    """)
    List<LivroCaixa> listarLivrosPorCongregacaoSituacaoEPeriodo(
            @Param("congregacaoId") Integer congregacaoId,
            @Param("situacaoId") Integer situacaoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    //Buscar livros caixa para auditoria
    @Query("""
    SELECT lc
    FROM LivroCaixa lc
    WHERE lc.deletedAt IS NULL
    AND lc.situacao.descricao = :descricao
    AND lc.situacao.contexto = :contexto
    AND (
        NOT EXISTS (
            SELECT 1 FROM Auditoria a WHERE a.livroCaixa = lc
        )
        OR EXISTS (
            SELECT 1 FROM Auditoria a
            WHERE a.livroCaixa = lc
            AND a.deliberacao = br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao.REPROVADO
            AND a.auditoriaId = (
                SELECT MAX(a2.auditoriaId)
                FROM Auditoria a2
                WHERE a2.livroCaixa = lc
            )
        )
    )
""")
    List<LivroCaixa> buscarLivrosCaixaParaAuditoria(
            @Param("descricao") String descricao,
            @Param("contexto") String contexto
    );

}
