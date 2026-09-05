package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.livro_caixa.LivroCaixaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CentroCustoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LivroCaixaService {

    private final LivroCaixaRepository livroCaixaRepository;
    private final CentroCustoRepository centroCustoRepository;
    private final SituacaoRepository situacaoRepository;

    public LivroCaixaService(
            LivroCaixaRepository livroCaixaRepository,
            CentroCustoRepository centroCustoRepository,
            SituacaoRepository situacaoRepository
    ){
        this.livroCaixaRepository = livroCaixaRepository;
        this.centroCustoRepository = centroCustoRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Listar Livros
    public List<LivroCaixaResponseDTO> listarLivros(){
        return livroCaixaRepository.listarLivros()
                .stream()
                .map(LivroCaixaResponseDTO::fromLivroCaixa)
                .toList();
    }

    //Listar Livros por Situacao e Periodo
    public List<LivroCaixaResponseDTO> listarLivrosPorSituacaoEPeriodo(
            Integer situacaoId,
            LocalDate dataInicio,
            LocalDate dataFim
    ){
        return livroCaixaRepository.listarLivrosPorSituacaoEPeriodo(
                        situacaoId, dataInicio, dataFim)

                .stream()
                .map(LivroCaixaResponseDTO::fromLivroCaixa)
                .toList();
    }

    //Buscar por ID
    public LivroCaixa SolicitarAuditoria(LivroCaixa livro){
        if(livro != null){
            var situacao = situacaoRepository.findByDescricao("AGUARDANDO_AUDITORIA");
            livro.setSituacao(situacao);
        }
        return livro;
    }


    //Listar Livros por Congregacao
    public List<LivroCaixaResponseDTO> listarLivrosPorCongregacao(Integer congregacaoId){
        return livroCaixaRepository.listarLivrosPorCongregacao(congregacaoId)
                .stream()
                .map(LivroCaixaResponseDTO::fromLivroCaixa)
                .toList();
    }

    //Listar Livros por Congregacao e Situacao
    public List<LivroCaixaResponseDTO> listarLivrosPorCongregacaoESituacao(
            Integer congregacaoId,
            Integer situacaoId
    ){
        return livroCaixaRepository.listarLivrosPorCongregacaoESituacao(congregacaoId, situacaoId)
                .stream()
                .map(LivroCaixaResponseDTO::fromLivroCaixa)
                .toList();
    }

    //Listar Livros por Congregacao  Situacao e Periodo
    public List<LivroCaixaResponseDTO> listarLivrosPorCongregacaoSituacaoEPeriodo(
            Integer congregacaoId,
            Integer situacaoId,
            LocalDate dataInicio,
            LocalDate dataFim
    ){
        return livroCaixaRepository.listarLivrosPorCongregacaoSituacaoEPeriodo(
                congregacaoId, situacaoId, dataInicio, dataFim)

                .stream()
                .map(LivroCaixaResponseDTO::fromLivroCaixa)
                .toList();
    }

    //Create
    @Transactional
    public LivroCaixaResponseDTO create(LivroCaixaRequestDTO dto){

        LivroCaixa livro = new LivroCaixa();

        livro.setCompetencia(dto.getCompetencia());

        //Relacionamentos
        if(dto.getCentroCustoId() != null){
            livro.setCentroCusto(
                    centroCustoRepository.findById(dto.getCentroCustoId())
                            .orElseThrow( () -> new RuntimeException("Centro de Custo não encontrado!") )
            );
        }

        if(dto.getSituacaoId() != null){
            livro.setSituacao(
                    situacaoRepository.findById(dto.getSituacaoId())
                            .orElseThrow( () -> new RuntimeException("Situação não encontrada!") )
            );
        }

        LivroCaixa salvo = livroCaixaRepository.save(livro);

        return LivroCaixaResponseDTO.fromLivroCaixa(salvo);
    }

    //Update
    @Transactional
    public LivroCaixaResponseDTO update(LivroCaixaEditDTO dto){

        if(dto.getLivroCaixaId() == null){
            throw new RuntimeException("O atributo livroCaixaId não pode ser null!");
        }

        LivroCaixa result = livroCaixaRepository.findById(dto.getLivroCaixaId())
                .orElseThrow( () -> new RuntimeException("Livro não encontrado!") );

        //Atribuindo novos valores
        result.setCompetencia(dto.getCompetencia());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        return LivroCaixaResponseDTO.fromLivroCaixa(livroCaixaRepository.save(result));
    }

    //Delete
    @Transactional
    public LivroCaixaResponseDTO delete(LivroCaixaEditDTO dto){

        if(dto.getLivroCaixaId() == null){
            throw new RuntimeException("O atributo livroCaixaId não pode ser null!");
        }

        LivroCaixa result = livroCaixaRepository.findById(dto.getLivroCaixaId())
                .orElseThrow( () -> new EntityNotFoundException("Livro não encontrado!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDateTime.now());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        return LivroCaixaResponseDTO.fromLivroCaixa(livroCaixaRepository.save(result));
    }


}
