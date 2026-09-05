package br.com.adbrascosmopolis.saac_tesouraria.controllers;

import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.services.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    //Dropdown Categorias
    @GetMapping("/dropdown")
    public ResponseEntity<List<CategoriaDropdownDTO>> dropdown(){
        List<CategoriaDropdownDTO> categorias = service.dropdownCategorias();
        return ResponseEntity.ok(categorias);
    }

    //Create
    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody CategoriaRequestDTO dto){
        try{
            CategoriaResponseDTO response = service.create(dto);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getCategoriaId())
                    .toUri();

            return ResponseEntity.created(location).body(response);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    //Update
    @PutMapping()
    public ResponseEntity<CategoriaResponseDTO> update(@RequestBody CategoriaEditDTO dto){
        CategoriaResponseDTO categoria = service.update(dto);
        return ResponseEntity.ok(categoria);
    }

    //Delete
    @PutMapping("/delete")
    public ResponseEntity<CategoriaResponseDTO> delete(@RequestBody CategoriaEditDTO dto){
        CategoriaResponseDTO categoria = service.delete(dto);
        return ResponseEntity.ok(categoria);
    }

}
