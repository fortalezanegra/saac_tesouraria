package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.services.synchronization.SecretariaSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synchronize")
public class SynchronizeController {

    private final SecretariaSyncService service;

    public SynchronizeController(SecretariaSyncService service){
        this.service = service;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_REPORT_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_REPORT_LOCAL')")
    public ResponseEntity<Void> synchronize(){
        service.synchronizeAll();
        return ResponseEntity.ok().build();
    }
}
