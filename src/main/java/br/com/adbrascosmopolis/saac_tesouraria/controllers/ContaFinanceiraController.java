package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.*;
import br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira.ContaFinanceiraSaldoService;
import br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira.ContaFinanceiraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/conta-financeira")
@CrossOrigin(origins = "*")
public class ContaFinanceiraController {

    private final ContaFinanceiraService service;
    private final ContaFinanceiraSaldoService saldoService;

    public ContaFinanceiraController(
            ContaFinanceiraService service,
            ContaFinanceiraSaldoService saldoService
    ){
        this.service = service;
        this.saldoService = saldoService;
    }

    //Dropdown Contas Financeira
    @GetMapping("/dropdown")
    public ResponseEntity<List<ContaFinanceiraDropdownDTO>> dropdown(){
        List<ContaFinanceiraDropdownDTO> contas = service.dropdownContas();
        return ResponseEntity.ok(contas);
    }

    //Testar Calculo de Saldo para Conta Financeira
//    @GetMapping("/saldo/{id}")
//    public ResponseEntity<ContaFinanceiraSaldoDTO> obterSaldo(
//            @PathVariable Long id
//    ) {
//
//        return ResponseEntity.ok(
//                saldoService.calcularSaldo(id)
//        );
//    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody ContaFinanceiraRequestDTO dto){
        try{
            ContaFinanceiraResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getContaFinanceiraId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping
    public ResponseEntity<ContaFinanceiraResponseDTO> update(@RequestBody ContaFinanceiraEditDTO dto){
        ContaFinanceiraResponseDTO conta = service.update(dto);
        return ResponseEntity.ok(conta);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<ContaFinanceiraResponseDTO> delete(@RequestBody ContaFinanceiraEditDTO dto){
        ContaFinanceiraResponseDTO conta = service.delete(dto);
        return ResponseEntity.ok(conta);
    }
}
