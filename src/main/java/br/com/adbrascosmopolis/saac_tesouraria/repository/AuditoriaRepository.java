package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    //LivroCaixaID
    Auditoria findByLivroCaixaLivroCaixaId(Long livroCaixaId);

    //AuditoriaId
    Auditoria findByAuditoriaId(Long auditoriaId);

    //SituacaoId
    Auditoria findBySituacaoDescricao(String descricao);

    //Response
    @Query("""
    SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO(
        a.auditoriaId,
        cc.descricao,
        lc.competencia,
        p.nome,
        a.dataInicio,
        a.observacoes,
        a.deliberacao,
        a.dataFim,
        s.descricao
    )
    FROM Auditoria a
    LEFT JOIN a.livroCaixa lc
    LEFT JOIN lc.centroCusto cc
    LEFT JOIN a.auditor p
    LEFT JOIN a.situacao s
    WHERE a.deletedAt IS NULL
    ORDER BY cc.descricao ASC
""")
    List<AuditoriaResponseDTO> listarTodasAuditorias();


    @Query("""
    SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO(
        a.auditoriaId,
        a.livroCaixa.centroCusto.descricao,
        a.livroCaixa.competencia,
        a.auditor.nome,
        a.dataInicio,
        a.observacoes,
        a.deliberacao,
        a.dataFim,
        a.situacao.descricao
    )
    FROM Auditoria a
    WHERE a.deletedAt IS NULL
    AND a.situacao.situacaoId = :situacaoId
    
    ORDER BY a.livroCaixa.centroCusto.descricao ASC
    """)
    List<AuditoriaResponseDTO> listarAuditoriasPorSituacaoId(@Param("situacaoId") Integer situacaoId);


    // Verifica se existe auditoria PENDENTE ou EM_ANALISE para o livro
    @Query("""
    SELECT a FROM Auditoria a
    WHERE a.livroCaixa.livroCaixaId = :livroCaixaId
    AND a.deletedAt IS NULL
    AND a.situacao.descricao IN ('Pendente', 'Em Analise')
""")
    List<Auditoria> findAuditoriasAtivasByLivroCaixaId(Long livroCaixaId);

    // Verifica se existe auditoria CONCLUIDA + APROVADO para o livro
    @Query("""
    SELECT a FROM Auditoria a
    WHERE a.livroCaixa.livroCaixaId = :livroCaixaId
    AND a.deletedAt IS NULL
    AND a.situacao.descricao = 'Concluida'
    AND a.deliberacao = :deliberacao
""")
    List<Auditoria> findAuditoriasAprovadasByLivroCaixaId(
            @Param("livroCaixaId") Long livroCaixaId,
            @Param("deliberacao") Deliberacao deliberacao
    );

}


