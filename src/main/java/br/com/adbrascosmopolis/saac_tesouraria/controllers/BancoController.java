package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.BancoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/banco")
@CrossOrigin(origins = "*")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service){
        this.service = service;
    }

    //Dropdown Bancos
    @GetMapping()
    public ResponseEntity<List<BancoDropdownDTO>> dropdown(){
        List<BancoDropdownDTO> bancos = service.dropdownBancos();
        return ResponseEntity.ok(bancos);
    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody BancoRequestDTO dto){
        try{
            BancoResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getBancoId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping()
    public ResponseEntity<BancoResponseDTO> update(@RequestBody BancoEditDTO dto){
        BancoResponseDTO banco = service.update(dto);
        return ResponseEntity.ok(banco);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<BancoResponseDTO> delete(@RequestBody BancoEditDTO dto){
        BancoResponseDTO banco = service.delete(dto);
        return ResponseEntity.ok(banco);
    }

}
