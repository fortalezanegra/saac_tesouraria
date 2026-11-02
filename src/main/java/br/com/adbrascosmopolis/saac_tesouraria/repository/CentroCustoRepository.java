package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {

    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO(
            c.centroCustoId,
            c.descricao
        )
        FROM CentroCusto c
        WHERE c.deletedAt IS NULL
          AND (:congregacaoId IS NULL OR c.congregacao.congregacaoId = :congregacaoId)
        ORDER BY c.congregacao.nome, c.descricao ASC
    """)
    List<CentroCustoDropdownDTO> listCentroCustoByCongregacao(@Param("congregacaoId") Integer congregacaoId);
}
