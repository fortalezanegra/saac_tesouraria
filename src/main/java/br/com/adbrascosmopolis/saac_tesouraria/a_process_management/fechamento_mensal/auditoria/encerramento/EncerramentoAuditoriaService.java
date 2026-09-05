package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.encerramento;

import br.com.adbrascosmopolis.saac_tesouraria.dto.auditoria.AuditoriaResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.*;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.situacao.SituacaoNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.repository.AuditoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EncerramentoAuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final SituacaoRepository situacaoRepository;

    public EncerramentoAuditoriaService(
            AuditoriaRepository auditoriaRepository,
            LivroCaixaRepository livroCaixaRepository,
            SituacaoRepository situacaoRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.situacaoRepository = situacaoRepository;
    }

    @Transactional
    public AuditoriaResponseDTO encerrar(EncerramentoAuditoriaRequestDTO dto) {

        if (dto.getAuditoriaId() == null) {
            throw new AuditoriaIdObrigatorioException(
                    "O atributo auditoriaId não pode ser null!"
            );
        }

        // 1. Buscar auditoria
        Auditoria auditoria = auditoriaRepository.findById(dto.getAuditoriaId())
                .orElseThrow(() -> new AuditoriaNaoEncontradaException("Auditoria não encontrada!"));

        // 2. Validação: já encerrada?
        if (auditoria.getSituacao() != null
                && SituacaoAuditoriaConstantes.CONCLUIDA.equalsIgnoreCase(auditoria.getSituacao().getDescricao())) {
            throw new AuditoriaJaEncerradaException("Esta auditoria já foi encerrada!");
        }

        // 3. Validação: deliberação preenchida durante o andamento
        Deliberacao deliberacao = auditoria.getDeliberacao();
        if (deliberacao == null) {
            throw new DeliberacaoObrigatoriaException(
                    "É necessário informar a deliberação (aprovado/reprovado) antes de encerrar a auditoria!");
        }

        // 4. Validação: observações preenchidas durante o andamento
        if (auditoria.getObservacoes() == null || auditoria.getObservacoes().isBlank()) {
            throw new ObservacoesObrigatoriasException(
                    "É necessário preencher as observações sobre a auditoria antes de encerrá-la!");
        }

        // 5. Atualiza dataFim
        auditoria.setDataFim(LocalDateTime.now());

        // 6. Atualiza situação da Auditoria -> Concluida (independente da deliberação)
        Situacao situacaoAuditoria = situacaoRepository.buscarPorDescricaoEContexto(
                SituacaoAuditoriaConstantes.CONCLUIDA,
                SituacaoAuditoriaConstantes.CONTEXTO
        );

        if (situacaoAuditoria == null) {
            throw new SituacaoNaoEncontradoException(
                    "Situação '" + SituacaoAuditoriaConstantes.CONCLUIDA + "' não encontrada!");
        }
        auditoria.setSituacao(situacaoAuditoria);

        // 7. Busca o LivroCaixa vinculado
        LivroCaixa livroCaixa = auditoria.getLivroCaixa();
        if (livroCaixa == null) {
            throw new AuditoriaNaoPossuiLivroCaixaVinculadoException(
                    "Auditoria não possui LivroCaixa vinculado!: " + dto.getAuditoriaId()
            );
        }

        // 8. Define a situação do LivroCaixa de acordo com a deliberação
        String descricaoSituacaoLivro = switch (deliberacao) {
            case APROVADO -> SituacaoLivrosConstantes.AGUARDANDO_FECHAMENTO;
            case REPROVADO -> SituacaoLivrosConstantes.REABERTO;
        };

        Situacao situacaoLivro = situacaoRepository.findByDescricao(descricaoSituacaoLivro);
        if (situacaoLivro == null) {
            throw new SituacaoNaoEncontradoException(
                    "Situação '" + descricaoSituacaoLivro + "' não encontrada!");
        }

        livroCaixa.setSituacao(situacaoLivro);
        livroCaixaRepository.save(livroCaixa);

        // 9. Persiste auditoria
        Auditoria encerrada = auditoriaRepository.save(auditoria);

        return AuditoriaResponseDTO.fromAuditoria(encerrada);
    }
}
