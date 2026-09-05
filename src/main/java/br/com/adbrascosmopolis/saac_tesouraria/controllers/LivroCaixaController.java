package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaSearchDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.LivroCaixaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/livro-caixa")
@CrossOrigin(origins = "*")
public class LivroCaixaController {

    private final LivroCaixaService service;

    public LivroCaixaController(LivroCaixaService service){
        this.service = service;
    }

    //Listar todos os Livros
    @GetMapping()
    public ResponseEntity<List<LivroCaixaResponseDTO>> listarLivros(){
        List<LivroCaixaResponseDTO> livros = service.listarLivros();
        return ResponseEntity.ok(livros);
    }

    //Listar Livros por Situacao e Periodo
    @GetMapping("/situacao/periodo")
    public ResponseEntity<List<LivroCaixaResponseDTO>> listarLivrosPorSituacaoEPeriodo(@ModelAttribute LivroCaixaSearchDTO dto){
        List<LivroCaixaResponseDTO> livros =
                service.listarLivrosPorSituacaoEPeriodo(dto.getSituacaoId(), dto.getDataInicio(), dto.getDataFim());
        return ResponseEntity.ok(livros);
    }

    //Listar Livros por Congregacao
    @GetMapping("/congregacao")
    public ResponseEntity<List<LivroCaixaResponseDTO>> listarLivrosPorCongregacao(
            @RequestBody Integer congregacaoId){
        List<LivroCaixaResponseDTO> livros = service.listarLivrosPorCongregacao(congregacaoId);
        return ResponseEntity.ok(livros);
    }

    //Listar Livros por Congregacao e Situação
    @GetMapping("/congregacao/situacao")
    public ResponseEntity<List<LivroCaixaResponseDTO>> listarLivrosPorCongregacaoESituacao(){
        List<LivroCaixaResponseDTO> livros = service.listarLivrosPorCongregacaoESituacao(7, 1);
        return ResponseEntity.ok(livros);
    }

    //Listar Livros por Congregacao Situação e Periodo
    @GetMapping("/congregacao/situacao/periodo")
    public ResponseEntity<List<LivroCaixaResponseDTO>> listarLivrosPorCongregacaoSituacaoEPeriodo(
            @RequestBody Integer congregacaoId,
            @RequestBody Integer situacaoId,
            @RequestBody LocalDate dataInicio,
            @RequestBody LocalDate dataFim
    ){
        List<LivroCaixaResponseDTO> livros =
                service.listarLivrosPorCongregacaoSituacaoEPeriodo(congregacaoId, situacaoId, dataInicio, dataFim);
        return ResponseEntity.ok(livros);
    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody LivroCaixaRequestDTO dto){
        try{
            LivroCaixaResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getLivroCaixaId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping()
    public ResponseEntity<LivroCaixaResponseDTO> update(@RequestBody LivroCaixaEditDTO dto){
        LivroCaixaResponseDTO livro = service.update(dto);
        return ResponseEntity.ok(livro);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<LivroCaixaResponseDTO> delete(@RequestBody LivroCaixaEditDTO dto){
        LivroCaixaResponseDTO livro = service.delete(dto);
        return ResponseEntity.ok(livro);
    }

}
