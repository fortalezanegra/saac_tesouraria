package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/dropdown")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<CategoriaDropdownDTO>> dropdown() {
        return ResponseEntity.ok(categoriaService.dropdownCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@Valid @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.create(dto));
    }

    @PutMapping
    public ResponseEntity<CategoriaResponseDTO> update(@Valid @RequestBody CategoriaEditDTO dto) {
        return ResponseEntity.ok(categoriaService.update(dto));
    }

    @DeleteMapping
    public ResponseEntity<CategoriaResponseDTO> delete(@Valid @RequestBody CategoriaEditDTO dto) {
        return ResponseEntity.ok(categoriaService.delete(dto));
    }
}
