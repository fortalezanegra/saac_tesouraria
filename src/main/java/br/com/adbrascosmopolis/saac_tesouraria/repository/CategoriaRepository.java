package br.com.adbrascosmopolis.saac_tesouraria.repository;

import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    //FindByCategoriaId
    Categoria findByCategoriaId(Integer categoriaId);


    //Dropdown
    @Query("""
        SELECT new br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaDropdownDTO(
            c.categoriaId,
            c.descricao,
            c.tipoCategoria
        )
        FROM Categoria c
        WHERE c.deletedAt IS NULL
        ORDER BY c.tipoCategoria, c.descricao ASC
    """)
    List<CategoriaDropdownDTO> listarCategoriasDropdown();


}
