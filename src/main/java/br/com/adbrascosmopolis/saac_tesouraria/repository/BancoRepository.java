package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface BancoRepository extends JpaRepository<Banco, Integer> {

    //Dropdown
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoDropdownDTO(
            b.bancoId,
            b.codigoBanco,
            b.nome
        )
        FROM Banco b
        WHERE b.deletedAt IS NULL
        ORDER BY b.nome ASC
    """)
    List<BancoDropdownDTO> listarBancosDropdown();


}
