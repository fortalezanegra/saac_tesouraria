package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentroCustoRepository extends JpaRepository<CentroCusto, Integer> {


    //Dropdown
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO(
            c.centroCustoId,
            c.descricao
        )
        FROM CentroCusto c
        WHERE c.deletedAt IS NULL
        ORDER BY c.congregacao.nome ,c.descricao ASC
    """)
    List<CentroCustoDropdownDTO> listarCentroCustoDropdown();
}
