package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.*;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira.ContaFinanceiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta-financeira")
@CrossOrigin(origins = "*")
public class ContaFinanceiraController {

    private final ContaFinanceiraService service;

    public ContaFinanceiraController(ContaFinanceiraService service) {
        this.service = service;
    }

    @PostMapping("/filtrar")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<ContaFinanceiraDropdownDTO>> filterContasFinanceira(
            @RequestBody ContaFinanceiraFiltroDTO dto) {
        List<ContaFinanceiraDropdownDTO> contas = service.filterContasByCentroCusto(dto.getCentroCustoId());
        return ResponseEntity.ok(contas);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<ContaFinanceiraDropdownDTO>> getAll() {
        List<ContaFinanceiraDropdownDTO> contas = service.listContas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<ContaFinanceiraResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


//    @PostMapping()
//    public ResponseEntity<Object> create(@RequestBody ContaFinanceiraRequestDTO dto) {
//        try {
//            ContaFinanceiraResponseDTO response = service.create(dto);
//
//            URI location = ServletUriComponentsBuilder
//                    .fromCurrentRequest()
//                    .path("/{id}")
//                    .buildAndExpand(response.getContaFinanceiraId())
//                    .toUri();
//
//            return ResponseEntity.created(location).body(response);
//
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().body(ex.getMessage());
//        }
//    }
//
//    @PutMapping
//    public ResponseEntity<ContaFinanceiraResponseDTO> update(@RequestBody ContaFinanceiraEditDTO dto) {
//        ContaFinanceiraResponseDTO conta = service.update(dto);
//        return ResponseEntity.ok(conta);
//    }
//
//    @PutMapping("/delete")
//    public ResponseEntity<ContaFinanceiraResponseDTO> delete(@RequestBody ContaFinanceiraEditDTO dto) {
//        ContaFinanceiraResponseDTO conta = service.delete(dto);
//        return ResponseEntity.ok(conta);
//    }
}
