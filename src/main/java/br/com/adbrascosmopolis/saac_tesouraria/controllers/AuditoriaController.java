package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.*;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.services.AuditoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@CrossOrigin(origins = "*")
public class AuditoriaController {

    private final AuditoriaService service;

    public AuditoriaController(AuditoriaService service) {
        this.service = service;
    }

    //GetAll
    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> getAll() {
        List<AuditoriaResponseDTO> list = service.listarTodasAuditorias();
        return ResponseEntity.ok(list);
    }

    //GetById
    @GetMapping("/situacao")
    public ResponseEntity<List<AuditoriaResponseDTO>> getById(@RequestBody AuditoriaRequestDTO dto){
        List<AuditoriaResponseDTO> auditorias = service.listarAuditoriasSituacaoId(dto.getSituacaoId());
        return ResponseEntity.ok(auditorias);
    }

    //Create
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AuditoriaRequestDTO dto) {
        try {
            AuditoriaResponseDTO created = service.create(dto);

            // Monta a URI do recurso recém‑criado (ex.: /api/auditoria/12)
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(created.getAuditoriaId())
                    .toUri();

            return ResponseEntity.created(location).body(created);
        } catch (Exception ex) {
            // Em caso de validação ou erro de negócio, devolve Bad Request
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping
    public ResponseEntity<AuditoriaResponseDTO> update(@RequestBody AuditoriaEditDTO dto) {
        AuditoriaResponseDTO updated = service.update(dto);
        return ResponseEntity.ok(updated);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<AuditoriaResponseDTO> delete(@RequestBody AuditoriaEditDTO dto) {
        AuditoriaResponseDTO deleted = service.delete(dto);
        return ResponseEntity.ok(deleted);
    }

}
