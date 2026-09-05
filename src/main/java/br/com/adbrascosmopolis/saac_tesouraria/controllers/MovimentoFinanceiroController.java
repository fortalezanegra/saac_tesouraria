package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.MovimentoFinanceiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movimento-financeiro")
@CrossOrigin(origins = "*")
public class MovimentoFinanceiroController {

    private final MovimentoFinanceiroService service;

    public MovimentoFinanceiroController(MovimentoFinanceiroService service){
        this.service = service;
    }

    //Listar Todos
    @GetMapping()
    public ResponseEntity<List<MovimentoFinanceiroResponseDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody MovimentoFinanceiroRequestDTO dto){
        try{
            MovimentoFinanceiroResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getMovimentoFinanceiroId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping()
    public ResponseEntity<MovimentoFinanceiroResponseDTO> update(@RequestBody MovimentoFinanceiroEditDTO dto){
        MovimentoFinanceiroResponseDTO movimento = service.update(dto);
        return ResponseEntity.ok(movimento);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<MovimentoFinanceiroResponseDTO> delete(@RequestBody MovimentoFinanceiroEditDTO dto){
        MovimentoFinanceiroResponseDTO movimento = service.delete(dto);
        return ResponseEntity.ok(movimento);
    }

}
