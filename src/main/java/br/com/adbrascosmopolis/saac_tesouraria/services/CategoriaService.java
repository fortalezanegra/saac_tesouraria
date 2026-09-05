package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.categoria.CategoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Categoria;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CategoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private final CategoriaRepository categoriaRepository;
    private final SituacaoRepository situacaoRepository;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            SituacaoRepository situacaoRepository
    ){
        this.categoriaRepository = categoriaRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Dropdown Categorias
    public List<CategoriaDropdownDTO> dropdownCategorias(){
        List<CategoriaDropdownDTO> categorias = categoriaRepository.listarCategoriasDropdown();
        return categorias;
    }

    //Create
    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto){

        Categoria categoria = new Categoria();

       categoria.setDescricao(dto.getDescricao());
       categoria.setTipoCategoria(dto.getTipoCategoria());

        Categoria salva = categoriaRepository.save(categoria);
        Categoria completa = categoriaRepository.findById(salva.getCategoriaId())
                .orElseThrow( () -> new RuntimeException("Categoria não encontrada!") );

        return CategoriaResponseDTO.fromCategoria(completa);
    }

    //Update
    @Transactional
    public CategoriaResponseDTO update(CategoriaEditDTO dto){

        if(dto.getCategoriaId() == null){
            throw new RuntimeException("O atributo categoriaId não pode ser null!");
        }

        Categoria result = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow( () -> new RuntimeException("Categoria não encontrada!") );

        //Atribuindo novos valores
        result.setDescricao(dto.getDescricao());
        result.setTipoCategoria(dto.getTipoCategoria());

        var categoriaAtualizada = categoriaRepository.save(result);

        return CategoriaResponseDTO.fromCategoria(categoriaAtualizada);
    }

    //Delete
    @Transactional
    public CategoriaResponseDTO delete(CategoriaEditDTO dto){

        if(dto.getCategoriaId() == null){
            throw new RuntimeException("O atributo categoriaId não pode ser null!");
        }

        Categoria result = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow( () -> new RuntimeException("Categoria não encontrada!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDate.now());

        var categoriaAtualizada = categoriaRepository.save(result);

        return CategoriaResponseDTO.fromCategoria(categoriaAtualizada);
    }
}
