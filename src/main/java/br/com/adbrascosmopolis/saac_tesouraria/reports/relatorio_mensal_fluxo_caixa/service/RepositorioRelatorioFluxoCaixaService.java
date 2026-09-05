package br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service;

import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.dto.RepositorioRelatorioFluxoCaixaDTO;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.models.RepositorioRelatorioFluxoCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.repository.RepositorioRelatorioFluxoCaixaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class RepositorioRelatorioFluxoCaixaService {

    private final RepositorioRelatorioFluxoCaixaRepository repository;

    public RepositorioRelatorioFluxoCaixaService(
            RepositorioRelatorioFluxoCaixaRepository repository
    ){
        this.repository = repository;
    }

    //Create
    @Transactional
    public RepositorioRelatorioFluxoCaixaDTO create(RepositorioRelatorioFluxoCaixaDTO dto){
        RepositorioRelatorioFluxoCaixa repositorio = new RepositorioRelatorioFluxoCaixa();

        repositorio.setNomeArquivo(dto.getNomeArquivo());
        repositorio.setUrlArquivo(dto.getUrlArquivo());

        RepositorioRelatorioFluxoCaixa salvo = repository.save(repositorio);
        RepositorioRelatorioFluxoCaixa completo = repository.findById(salvo.getRepositorioId())
                .orElseThrow( () -> new RuntimeException("Arquivo não encontrado!") );

        return RepositorioRelatorioFluxoCaixaDTO.fromRepositorioRelatorioFluxoCaixa(completo);
    }

    //Update
    @Transactional
    public RepositorioRelatorioFluxoCaixaDTO update(RepositorioRelatorioFluxoCaixaDTO dto){

        if(dto.getRepositorioId() == null){
            throw new RuntimeException("O atributo repositorioId não pode ser null!");
        }

        RepositorioRelatorioFluxoCaixa result = repository.findById(dto.getRepositorioId())
                .orElseThrow( () -> new RuntimeException("Banco não encontrado!") );

        result.setNomeArquivo(dto.getNomeArquivo());
        result.setUrlArquivo(dto.getUrlArquivo());

        var repositorioAtualizado = repository.save(result);

        return RepositorioRelatorioFluxoCaixaDTO.fromRepositorioRelatorioFluxoCaixa(repositorioAtualizado);
    }

    //Delete
    @Transactional
    public RepositorioRelatorioFluxoCaixaDTO delete(RepositorioRelatorioFluxoCaixaDTO dto){

        if(dto.getRepositorioId() == null){
            throw new RuntimeException("O atributo repositorioId não pode ser null!");
        }

        RepositorioRelatorioFluxoCaixa result = repository.findById(dto.getRepositorioId())
                .orElseThrow( () -> new RuntimeException("Arquivo não encontrado!") );

        // deletedAt agora é DATETIME
        result.setDeletedAt(LocalDate.now());

        var repositorioAtualizado = repository.save(result);

        return RepositorioRelatorioFluxoCaixaDTO.fromRepositorioRelatorioFluxoCaixa(repositorioAtualizado);
    }
}
