package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.*;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.AuditoriaIdNaoPodeSerNullException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.AuditoriaInvalidaException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.AuditoriaNaoEncontradaException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.situacao.SituacaoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Pessoa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.AuditoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.PessoaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final PessoaRepository pessoaRepository;
    private final SituacaoRepository situacaoRepository;

    public AuditoriaService(
            AuditoriaRepository auditoriaRepository,
            LivroCaixaRepository livroCaixaRepository,
            PessoaRepository pessoaRepository,
            SituacaoRepository situacaoRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.pessoaRepository = pessoaRepository;
        this.situacaoRepository = situacaoRepository;
    }

    /* List All */
    public List<AuditoriaResponseDTO> listarTodasAuditorias() {
        List<AuditoriaResponseDTO> auditorias = auditoriaRepository.listarTodasAuditorias();
        return auditorias;
    }

    //GetBySituacaoId
    public List<AuditoriaResponseDTO> listarAuditoriasSituacaoId(Integer situacaoId){

        List<AuditoriaResponseDTO> auditorias = auditoriaRepository.listarAuditoriasPorSituacaoId(situacaoId);
        return auditorias;
    }

    /* Create */
    @Transactional
    public AuditoriaResponseDTO create(AuditoriaRequestDTO dto) {



        Auditoria auditoria = new Auditoria();

        auditoria.setDataInicio(dto.getDataInicio());
        auditoria.setObservacoes(dto.getObservacoes());
        auditoria.setDeliberacao(dto.getDeliberacao());
        auditoria.setDataFim(dto.getDataFim());

        // relacionamentos
        if (dto.getLivroCaixaId() != null) {
            LivroCaixa lc = livroCaixaRepository.findById(dto.getLivroCaixaId())
                    .orElseThrow(() -> new LivroCaixaNaoEncontradoException("Livro de caixa não encontrado!"));
            auditoria.setLivroCaixa(lc);
        }

        if (dto.getAuditorId() != null) {
            Pessoa p = pessoaRepository.findById(dto.getAuditorId())
                    .orElseThrow(() -> new RuntimeException("Auditor não encontrado!"));
            auditoria.setAuditor(p);
        }

        if (dto.getSituacaoId() != null) {
            Situacao s = situacaoRepository.findById(dto.getSituacaoId())
                    .orElseThrow(() -> new SituacaoNaoEncontradoException("Situação não encontrada!"));
            auditoria.setSituacao(s);
        }

        Auditoria salvo = auditoriaRepository.save(auditoria);
        Auditoria completo = auditoriaRepository.findById(salvo.getAuditoriaId())
                .orElseThrow(() -> new AuditoriaNaoEncontradaException("Auditoria não encontrada!"));

        return AuditoriaResponseDTO.fromAuditoria(completo);
    }

    /* Update */
    @Transactional
    public AuditoriaResponseDTO update(AuditoriaEditDTO dto) {

        if (dto.getAuditoriaId() == null) {
            throw new AuditoriaIdNaoPodeSerNullException("O atributo auditoriaId não pode ser null!");
        }

        Auditoria auditoria = auditoriaRepository.findById(dto.getAuditoriaId())
                .orElseThrow(() -> new AuditoriaNaoEncontradaException("Auditoria não encontrada!"));

        if(auditoria.getAuditor() == null){
            throw new AuditoriaInvalidaException(
                    "Não é possivel atualizar uma auditoria que não foi inicializada."
            );
        }

        if(auditoria.getSituacao().getDescricao().equalsIgnoreCase(SituacaoAuditoriaConstantes.CONCLUIDA)){
            throw new AuditoriaInvalidaException(
                    "Não é possivel atualizar uma auditoria que já foi concluida."
            );
        }

        // campos simples
        auditoria.setObservacoes(dto.getObservacoes());
        auditoria.setDeliberacao(dto.getDeliberacao());

        // relacionamentos
        if (dto.getAuditorId() != null) {
            Pessoa p = pessoaRepository.getReferenceById(dto.getAuditorId());
            auditoria.setAuditor(p);
        }

        Auditoria atualizado = auditoriaRepository.save(auditoria);
        return AuditoriaResponseDTO.fromAuditoria(atualizado);
    }

    /* Delete (soft‑delete) */
    @Transactional
    public AuditoriaResponseDTO delete(AuditoriaEditDTO dto) {

        if (dto.getAuditoriaId() == null) {
            throw new AuditoriaIdNaoPodeSerNullException(
                    "O atributo auditoriaId não pode ser null!"
            );
        }

        Auditoria auditoria = auditoriaRepository.findById(dto.getAuditoriaId())
                .orElseThrow(() -> new AuditoriaNaoEncontradaException("Auditoria não encontrada!"));

        // soft delete
        auditoria.setDeletedAt(LocalDateTime.now());

        Auditoria excluida = auditoriaRepository.save(auditoria);
        return AuditoriaResponseDTO.fromAuditoria(excluida);
    }
}
