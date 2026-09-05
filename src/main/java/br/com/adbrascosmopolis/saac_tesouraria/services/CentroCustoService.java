package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.centro_custo.CentroCustoResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CentroCustoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CongregacaoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CentroCustoService {

    @Autowired
    private final CentroCustoRepository centroCustoRepository;
    private final CongregacaoRepository congregacaoRepository;
    private final SituacaoRepository situacaoRepository;

    public CentroCustoService(
            CentroCustoRepository centroCustoRepository,
            CongregacaoRepository congregacaoRepository,
            SituacaoRepository situacaoRepository
    ){
        this.centroCustoRepository = centroCustoRepository;
        this.congregacaoRepository = congregacaoRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Dropdown Centros de Custo
    public List<CentroCustoDropdownDTO> dropdownCentroCusto(){
        List<CentroCustoDropdownDTO> centros = centroCustoRepository.listarCentroCustoDropdown();
        return centros;
    }

    //Create
    @Transactional
    public CentroCustoResponseDTO create(CentroCustoRequestDTO dto){

        CentroCusto centro = new CentroCusto();

        centro.setTipoCentro(dto.getTipoCentro());
        centro.setDescricao(dto.getDescricao());

        //Relacionamentos
        if(dto.getCongregacaoId() != null){
            centro.setCongregacao(
                    congregacaoRepository.findById(dto.getCongregacaoId())
                            .orElseThrow( () -> new RuntimeException("Congregação não encontrada!") )
            );
        }

        if(dto.getSituacaoId() != null){
            centro.setSituacao(
                    situacaoRepository.findById(dto.getSituacaoId())
                            .orElseThrow( () -> new RuntimeException("Situação não encontrada!") )
            );
        }

        CentroCusto salvo = centroCustoRepository.save(centro);
        CentroCusto completo = centroCustoRepository.findById(salvo.getCentroCustoId())
                .orElseThrow( () -> new RuntimeException("Centro de Custo não encontrado!") );

        return CentroCustoResponseDTO.fromCentroCusto(completo);
    }

    //Update
    @Transactional
    public CentroCustoResponseDTO update(CentroCustoEditDTO dto){

        if(dto.getCentroCustoId() == null){
            throw new RuntimeException("O atributo centroCustoId não pode ser null!");
        }

        CentroCusto result = centroCustoRepository.findById(dto.getCentroCustoId())
                .orElseThrow( () -> new RuntimeException("Centro de Custo não encontrado!") );

        //Atribuindo novos valores
        result.setTipoCentro(dto.getTipoCentro());
        result.setDescricao(dto.getDescricao());

        //Relacionamentos
        if(dto.getCongregacaoId() != null){
            result.setCongregacao(
                    congregacaoRepository.getReferenceById(dto.getCongregacaoId())
            );
        }

        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var centroAtualizado = centroCustoRepository.save(result);

        return CentroCustoResponseDTO.fromCentroCusto(centroAtualizado);
    }

    //Delete
    @Transactional
    public CentroCustoResponseDTO delete(CentroCustoEditDTO dto){

        if(dto.getCentroCustoId() == null){
            throw new RuntimeException("O atributo centroCustoId não pode ser null!");
        }

        CentroCusto result = centroCustoRepository.findById(dto.getCentroCustoId())
                .orElseThrow( () -> new RuntimeException("Centro de Custo não encontrado!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDate.now());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var centroAtualizado = centroCustoRepository.save(result);

        return CentroCustoResponseDTO.fromCentroCusto(centroAtualizado);
    }

}
