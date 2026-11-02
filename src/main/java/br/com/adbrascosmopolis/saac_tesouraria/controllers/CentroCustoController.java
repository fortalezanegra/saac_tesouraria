package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.*;
import br.com.adbrascosmopolis.saac_tesouraria.services.CentroCustoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/centro-custo")
@CrossOrigin(origins = "*")
public class CentroCustoController {

    private final CentroCustoService service;

    public CentroCustoController(CentroCustoService service) {
        this.service = service;
    }

    @PostMapping("/filtrar")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<CentroCustoDropdownDTO>> filterCentroCusto(
            @RequestBody CentroCustoFiltroDTO dto) {
        List<CentroCustoDropdownDTO> centros = service.listCentroCusto(dto.getCongregacaoId());
        return ResponseEntity.ok(centros);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CentroCustoRequestDTO dto) {
        try {
            CentroCustoResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getCentroCustoId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<CentroCustoResponseDTO> update(@RequestBody CentroCustoEditDTO dto) {
        CentroCustoResponseDTO centro = service.update(dto);
        return ResponseEntity.ok(centro);
    }

    @PutMapping("/delete")
    public ResponseEntity<CentroCustoResponseDTO> delete(@RequestBody CentroCustoEditDTO dto) {
        CentroCustoResponseDTO centro = service.delete(dto);
        return ResponseEntity.ok(centro);
    }
}
