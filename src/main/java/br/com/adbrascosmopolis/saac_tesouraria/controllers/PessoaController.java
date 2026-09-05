package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.pessoa.PessoaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service){
        this.service = service;
    }

    //Dropdown Pessoas
    @GetMapping()
    public ResponseEntity<List<PessoaDropdownDTO>> dropdown(){
        List<PessoaDropdownDTO> pessoas = service.dropdownPessoas();
        return ResponseEntity.ok(pessoas);
    }
}
