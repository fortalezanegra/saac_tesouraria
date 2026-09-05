package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.transferencia_financeira.TransferenciaFinanceiraService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transferencia-financeira")
@CrossOrigin(origins = "*")
public class TransferenciaFinanceiraController {

    private final TransferenciaFinanceiraService service;

    public TransferenciaFinanceiraController(
            TransferenciaFinanceiraService service
    ){
        this.service = service;
    }

    //Listar Todas as Transferencias
    @GetMapping()
    public ResponseEntity<List<TransferenciaFinanceiraResponseDTO>> getAll(){
        List<TransferenciaFinanceiraResponseDTO> transferencias = service.listarTodos();
        return ResponseEntity.ok(transferencias);
    }

    //Transferencia entre Contas
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody TransferenciaFinanceiraRequestDTO dto){
        TransferenciaFinanceiraResponseDTO response = service.create(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getTransferenciaFinanceiraId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }



}
