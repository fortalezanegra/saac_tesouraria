package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.congregacao.CongregacaoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.CongregacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/congregacao")
@CrossOrigin(origins = "*")
public class CongregacaoController {

    private final CongregacaoService service;

    public CongregacaoController(CongregacaoService service){
        this.service = service;
    }

    //Dropdown Congregações
    @GetMapping()
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<CongregacaoDropdownDTO>> getAll(){
        List<CongregacaoDropdownDTO> congregacoes = service.dropdown();
        return ResponseEntity.ok(congregacoes);
    }

}
