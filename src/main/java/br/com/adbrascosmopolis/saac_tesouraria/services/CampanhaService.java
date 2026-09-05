package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.campanha.CampanhaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Campanha;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CampanhaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CentroCustoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CampanhaService {

    @Autowired
    private final CampanhaRepository campanhaRepository;
    private final CentroCustoRepository centroCustoRepository;
    private final SituacaoRepository situacaoRepository;

    public CampanhaService(
            CampanhaRepository campanhaRepository,
            CentroCustoRepository centroCustoRepository,
            SituacaoRepository situacaoRepository
    ){
        this.campanhaRepository = campanhaRepository;
        this.centroCustoRepository = centroCustoRepository;
        this.situacaoRepository = situacaoRepository;
    }
    //Listar Todas Campanhas
    public List<CampanhaResponseDTO> listarTodas(){
        List<Campanha> campanhas = campanhaRepository.findAll();

        return campanhas.stream()
                .map(c -> new CampanhaResponseDTO(
                        c.getCampanhaId(),
                        c.getCentroCusto() != null ? c.getCentroCusto().getDescricao() : null,
                        c.getDescricao(),
                        c.getMetavalor(),
                        c.getDataInicio(),
                        c.getDataFim(),
                        c.getSituacao() != null ? c.getSituacao().getDescricao() : null
                )).toList();
    }

    //Lista Campanhas Ativas
    public List<CampanhaDropdownDTO> campanhasAtivas(){
        List<CampanhaDropdownDTO> campanhas = campanhaRepository.listarCampanhasAtivas();
        return campanhas;
    }

    //Dropdown Campanhas por Congregação
    public List<CampanhaDropdownDTO> dropdownCampanhasPorCongregacao(Integer id){
        List<CampanhaDropdownDTO> campanhas = campanhaRepository.listarCampanhasPorCongregacao(id);
        return campanhas;
    }


    //Create
    @Transactional
    public CampanhaResponseDTO create(CampanhaRequestDTO dto){

        Campanha campanha = new Campanha();

        campanha.setDescricao(dto.getDescricao());
        campanha.setMetavalor(dto.getMetaValor());
        campanha.setDataInicio(dto.getDataInicio());
        campanha.setDataFim(dto.getDataFim());

        //Relacionamentos
        if(dto.getCentroCustoId() != null){
            campanha.setCentroCusto(
                    centroCustoRepository.findById(dto.getCentroCustoId())
                            .orElseThrow( () -> new RuntimeException("Centro de Custo não encontrado!") )
            );
        }

        if(dto.getSituacaoId() != null){
            campanha.setSituacao(
                    situacaoRepository.findById(dto.getSituacaoId())
                            .orElseThrow( () -> new RuntimeException("Situação não encontrada!") )
            );
        }

        Campanha salva = campanhaRepository.save(campanha);
        Campanha completa = campanhaRepository.findById(salva.getCampanhaId())
                .orElseThrow( () -> new RuntimeException("Campanha não encontrada!") );

        return CampanhaResponseDTO.fromCampanha(completa);
    }

    //Update
    @Transactional
    public CampanhaResponseDTO update(CampanhaEditDTO dto){

        if(dto.getCampanhaId() == null){
            throw new RuntimeException("O atributo campanhaId não pode ser null!");
        }

        Campanha result = campanhaRepository.findById(dto.getCampanhaId())
                .orElseThrow( () -> new RuntimeException("Campanha não encontrado!") );

        //Atribuindo novos valores
        result.setDescricao(dto.getDescricao());
        result.setMetavalor(dto.getMetaValor());
        result.setDataInicio(dto.getDataInicio());
        result.setDataFim(dto.getDataFim());

        //Relacionamentos
        if(dto.getCentroCustoId() != null){
            result.setCentroCusto(
                    centroCustoRepository.getReferenceById(dto.getCentroCustoId())
            );
        }

        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var campanhaAtualizada = campanhaRepository.save(result);

        return CampanhaResponseDTO.fromCampanha(campanhaAtualizada);
    }

    //Delete
    @Transactional
    public CampanhaResponseDTO delete(CampanhaEditDTO dto){

        if(dto.getCampanhaId() == null){
            throw new RuntimeException("O atributo campanhaId não pode ser null!");
        }

        Campanha result = campanhaRepository.findById(dto.getCampanhaId())
                .orElseThrow( () -> new RuntimeException("Campanha não encontrada!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDate.now());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var campanhaAtualizada = campanhaRepository.save(result);

        return CampanhaResponseDTO.fromCampanha(campanhaAtualizada);
    }

}
