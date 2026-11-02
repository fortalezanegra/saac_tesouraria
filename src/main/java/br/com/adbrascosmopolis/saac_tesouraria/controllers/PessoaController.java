package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaFiltroDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/dropdown")
    @PreAuthorize("hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_GLOBAL') or hasAuthority('PERMISSION_TESOURARIA_MOVIMENTO_FINANCEIRO_READ_LOCAL')")
    public ResponseEntity<List<PessoaDropdownDTO>> dropdown(@RequestBody PessoaFiltroDTO dto) {
        List<PessoaDropdownDTO> pessoas = pessoaService.filtrar(dto.getCongregacaoId());
        return ResponseEntity.ok(pessoas);
    }
}
