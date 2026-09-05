package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.CampanhaService;
import org.springframework.http.ResponseEntity;
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

    //Listar Todas as Campanhas
    @GetMapping()
    public ResponseEntity<List<CampanhaResponseDTO>> listarTodas(){
        List<CampanhaResponseDTO> campanhas = campanhaService.listarTodas();
        return ResponseEntity.ok(campanhas);
    }

    //Campanhas Ativas
    @GetMapping("/ativas")
    public ResponseEntity<List<CampanhaDropdownDTO>> campanhasAtivas(){
        List<CampanhaDropdownDTO> campanhas = campanhaService.campanhasAtivas();
        return ResponseEntity.ok(campanhas);
    }


    //Campanhas por Congregacao
    @GetMapping("/dropdown/{id}")
    public ResponseEntity<List<CampanhaDropdownDTO>> dropdownCampanhasPorCongregacao(@PathVariable("id") Integer id){
        List<CampanhaDropdownDTO> campanhas = campanhaService.dropdownCampanhasPorCongregacao(id);
        return ResponseEntity.ok(campanhas);
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
