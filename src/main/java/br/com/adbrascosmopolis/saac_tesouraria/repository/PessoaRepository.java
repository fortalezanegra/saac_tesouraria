package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO(
            p.pessoaId,
            p.nome
        )
        FROM Pessoa p
        WHERE (:congregacaoId IS NULL OR p.congregacao.congregacaoId = :congregacaoId)
        ORDER BY p.nome ASC
    """)
    List<PessoaDropdownDTO> filtrarPorCongregacao(@Param("congregacaoId") Integer congregacaoId);
}
