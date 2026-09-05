package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    //Campanhas Ativas
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO(
            c.campanhaId,
            c.centroCusto.congregacao.nome,
            c.centroCusto.descricao,
            c.descricao
        )
        FROM Campanha c
        WHERE c.deletedAt IS NULL
          AND c.situacao.descricao = 'Ativo'
        ORDER BY c.centroCusto.congregacao.nome, c.centroCusto.descricao, c.descricao ASC
    """)
    List<CampanhaDropdownDTO> listarCampanhasAtivas();

    //Dropdown Campanhas por Congregação
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO(
            c.campanhaId,
            c.centroCusto.congregacao.nome,
            c.centroCusto.descricao,
            c.descricao
        )
        FROM Campanha c
        WHERE c.deletedAt IS NULL
          AND c.situacao.situacaoId = 1
          AND c.centroCusto.congregacao.congregacaoId = :id
        ORDER BY c.centroCusto.congregacao.nome, c.centroCusto.descricao, c.descricao ASC
    """)
    List<CampanhaDropdownDTO> listarCampanhasPorCongregacao(@Param("id") Integer id);

}
