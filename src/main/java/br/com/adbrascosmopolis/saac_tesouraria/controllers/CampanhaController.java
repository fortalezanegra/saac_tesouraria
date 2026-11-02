package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.*;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoFiltroDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.CampanhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/campanha")
@CrossOrigin(origins = "*")
public class CampanhaController {

    private final CampanhaService campanhaService;

    public CampanhaController(CampanhaService campanhaService){
        this.campanhaService = campanhaService;
    }

    //Campanhas por Centro de Custo
    @PostMapping("/dropdown")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<CampanhaDropdownDTO>> dropdownCongregacao(
            @RequestBody CampanhaFiltroDTO dto) {
        List<CampanhaDropdownDTO> centros = campanhaService.campanhasPorCongregacao(dto.getCentroCustoId());
        return ResponseEntity.ok(centros);
    }


    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CampanhaRequestDTO dto){
        try{
            CampanhaResponseDTO response = campanhaService.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getCampanhaId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping()
    public ResponseEntity<CampanhaResponseDTO> update(@RequestBody CampanhaEditDTO dto){
        CampanhaResponseDTO campanha = campanhaService.update(dto);
        return ResponseEntity.ok(campanha);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<CampanhaResponseDTO> delete(@RequestBody CampanhaEditDTO dto){
        CampanhaResponseDTO campanha = campanhaService.delete(dto);
        return ResponseEntity.ok(campanha);
    }

}
