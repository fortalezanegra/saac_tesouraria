package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface SituacaoRepository extends JpaRepository<Situacao, Integer> {

    Situacao findBySituacaoId(Integer situacaoId);

    Situacao findByDescricao(String descricao);

    Optional<Situacao> findByDescricaoAndContexto(
            String descricao,
            String contexto
    );

    @Query("""
    SELECT s
    FROM Situacao s
    WHERE s.deletedAt IS NULL
    AND s.descricao = :descricao
    AND s.contexto = :contexto
""")
    Situacao buscarPorDescricaoEContexto(
            @Param("descricao") String descricao,
            @Param("contexto") String contexto
    );

}
