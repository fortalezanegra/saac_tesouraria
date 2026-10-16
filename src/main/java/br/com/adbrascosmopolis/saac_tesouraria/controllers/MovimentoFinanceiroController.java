package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.movimento_financeiro.MovimentoFinanceiroResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.MovimentoFinanceiroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movimento-financeiro")
public class MovimentoFinanceiroController {

    private final MovimentoFinanceiroService service;

    public MovimentoFinanceiroController(MovimentoFinanceiroService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<MovimentoFinanceiroResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_CREATE_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_CREATE_LOCAL')")
    public ResponseEntity<MovimentoFinanceiroResponseDTO> create(@RequestBody @Valid MovimentoFinanceiroRequestDTO dto) {
        MovimentoFinanceiroResponseDTO response = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getMovimentoFinanceiroId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_UPDATE_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_UPDATE_LOCAL')")
    public ResponseEntity<MovimentoFinanceiroResponseDTO> update(@RequestBody @Valid MovimentoFinanceiroEditDTO dto) {
        MovimentoFinanceiroResponseDTO movimento = service.update(dto);
        return ResponseEntity.ok(movimento);
    }

    @PutMapping("/delete")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_DELETE_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_DELETE_LOCAL')")
    public ResponseEntity<MovimentoFinanceiroResponseDTO> delete(@RequestBody @Valid MovimentoFinanceiroEditDTO dto) {
        MovimentoFinanceiroResponseDTO movimento = service.delete(dto);
        return ResponseEntity.ok(movimento);
    }
}
