package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceira, Long> {

    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraDropdownDTO(
            c.contaFinanceiraId,
            c.descricao,
            c.centroCusto.congregacao.nome,
            c.centroCusto.descricao,
            c.tipoConta   
        )
        FROM ContaFinanceira c
        WHERE c.deletedAt IS NULL
          AND (:centroCustoId IS NULL OR c.centroCusto.centroCustoId = :centroCustoId)
        ORDER BY c.centroCusto.congregacao.nome, c.centroCusto.descricao, c.descricao ASC
    """)
    List<ContaFinanceiraDropdownDTO> listContasByCentroCusto(@Param("centroCustoId") Integer centroCustoId);

    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraDropdownDTO(
            c.contaFinanceiraId,
            c.descricao,
            c.centroCusto.congregacao.nome,
            c.centroCusto.descricao,
            c.tipoConta   
        )
        FROM ContaFinanceira c
        ORDER BY c.centroCusto.congregacao.nome, c.centroCusto.descricao, c.descricao ASC
    """)
    List<ContaFinanceiraDropdownDTO> listContas();


    ContaFinanceira findByContaFinanceiraId(Long id);
}
