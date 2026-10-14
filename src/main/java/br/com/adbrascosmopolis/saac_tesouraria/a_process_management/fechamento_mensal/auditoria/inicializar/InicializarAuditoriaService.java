package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.inicializar;

import br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.preparar.PrepararAuditoriaRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.preparar.PrepararAuditoriaValidacaoService;
import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.AuditorNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.AuditoriaNaoEncontradaException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Pessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.AuditoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InicializarAuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final PessoaRepository pessoaRepository;
    private final SituacaoRepository situacaoRepository;
    private final PrepararAuditoriaValidacaoService validacaoService;

    public InicializarAuditoriaService(
            AuditoriaRepository auditoriaRepository,
            LivroCaixaRepository livroCaixaRepository,
            PessoaRepository pessoaRepository,
            SituacaoRepository situacaoRepository,
            PrepararAuditoriaValidacaoService validacaoService) {
        this.auditoriaRepository = auditoriaRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.pessoaRepository = pessoaRepository;
        this.situacaoRepository = situacaoRepository;
        this.validacaoService = validacaoService;
    }

    /**
     * ETAPA — Início da Auditoria (análise individual)
     * Auditoria: PENDENTE -> EM_ANALISE, auditorId + dataInicio preenchidos
     */
    @Transactional
    public AuditoriaResponseDTO iniciarAuditoria(PrepararAuditoriaRequestDTO dto) {

        Auditoria auditoria = auditoriaRepository.findById(dto.getAuditoriaId())
                .orElseThrow(() -> new AuditoriaNaoEncontradaException(
                        "Auditoria não encontrada."
                ));

        validacaoService.validarInicio(auditoria);

        Pessoa auditor = pessoaRepository.findById(dto.getAuditorId())
                .orElseThrow(() -> new AuditorNaoEncontradoException("Auditor não encontrado!"));

        Situacao situacaoAuditoriaEmAnalise =
                situacaoRepository.buscarPorDescricaoEContexto(
                        SituacaoAuditoriaConstantes.EM_ANALISE,
                        "AUDITORIA"
                );

        auditoria.setAuditor(auditor);
        auditoria.setDataInicio(LocalDateTime.now());
        auditoria.setSituacao(situacaoAuditoriaEmAnalise);

        Auditoria atualizado = auditoriaRepository.save(auditoria);

        return AuditoriaResponseDTO.fromAuditoria(atualizado);
    }
}
