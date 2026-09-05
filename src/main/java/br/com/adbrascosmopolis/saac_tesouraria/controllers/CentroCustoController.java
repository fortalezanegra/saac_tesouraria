package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.BancoService;
import br.com.adbrascosmopolis.saac_tesouraria.services.CentroCustoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/centro-custo")
@CrossOrigin(origins = "*")
public class CentroCustoController {

    private final CentroCustoService service;

    public CentroCustoController(CentroCustoService service){
        this.service = service;
    }

    //Dropdown Centros de Custo
    @GetMapping()
    public ResponseEntity<List<CentroCustoDropdownDTO>> dropdown(){
        List<CentroCustoDropdownDTO> centros = service.dropdownCentroCusto();
        return ResponseEntity.ok(centros);
    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CentroCustoRequestDTO dto){
        try{
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

    //Update
    @PutMapping()
    public ResponseEntity<CentroCustoResponseDTO> update(@RequestBody CentroCustoEditDTO dto){
        CentroCustoResponseDTO centro = service.update(dto);
        return ResponseEntity.ok(centro);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<CentroCustoResponseDTO> delete(@RequestBody CentroCustoEditDTO dto){
        CentroCustoResponseDTO centro = service.delete(dto);
        return ResponseEntity.ok(centro);
    }

}
