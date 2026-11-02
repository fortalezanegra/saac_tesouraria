package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    //Dropdown Campanhas por Centro de Custo
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO(
            c.campanhaId,
            c.descricao
        )
        FROM Campanha c
        WHERE c.deletedAt IS NULL
          AND c.situacao.situacaoId = 1
          AND c.centroCusto.centroCustoId = :id
        ORDER BY c.descricao, c.centroCusto.descricao ASC
    """)
    List<CampanhaDropdownDTO> listarCampanhasPorCentroCusto(@Param("id") Integer id);

}
