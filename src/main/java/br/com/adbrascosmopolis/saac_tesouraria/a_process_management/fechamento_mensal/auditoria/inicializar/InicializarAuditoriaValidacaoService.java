package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.inicializar;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.*;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.repository.AuditoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InicializarAuditoriaValidacaoService {

    private final AuditoriaRepository auditoriaRepository;

    public InicializarAuditoriaValidacaoService(AuditoriaRepository auditoriaRepository) {
        this.auditoriaRepository = auditoriaRepository;
    }

    /**
     * Validações para SOLICITAR uma nova auditoria (etapa 1 do fluxo).
     */
    public void validarSolicitacao(LivroCaixa livroCaixa) {

        validarLivroCaixaExiste(livroCaixa);

        validarSemAuditoriaAtiva(livroCaixa.getLivroCaixaId());

        validarSemAuditoriaAprovada(livroCaixa.getLivroCaixaId());
    }

    /**
     * Validações para INICIAR a análise de uma auditoria (etapa 2 do fluxo).
     */
    public void validarInicio(Auditoria auditoria) {

        if (auditoria == null) {
            throw new AuditoriaNaoEncontradaException(
                    "A auditoria informada não foi encontrada."
            );
        }

        if (auditoria.getSituacao() == null) {
            throw new AuditoriaInvalidaException("A auditoria não possui situação definida.");
        }

        String situacaoDescricao = auditoria.getSituacao().getDescricao();

        boolean auditoriaInicializada =
                auditoria.getAuditor() != null && auditoria.getDataInicio() != null;

        if (auditoriaInicializada
                && situacaoDescricao.equals(SituacaoAuditoriaConstantes.EM_ANALISE)) {
            throw new AuditoriaAtivaException(
                    "Esta auditoria já foi iniciada e possui um auditor designado."
            );
        }

        if (situacaoDescricao.equals(SituacaoAuditoriaConstantes.CONCLUIDA)) {
            throw new AuditoriaConcluidaException(
                    "Esta auditoria já foi concluída."
            );
        }
    }


    private void validarLivroCaixaExiste(LivroCaixa livroCaixa) {
        if (livroCaixa == null) {
            throw new LivroCaixaNaoEncontradoException(
                    "Livro Caixa não encontrado."
            );
        }
    }

//    private void validarSituacaoLivroParaSolicitacao(LivroCaixa livroCaixa) {
//
//        String situacaoAtual = livroCaixa.getSituacao().getDescricao();
//
//        if (!SituacaoLivrosConstantes.ABERTO.equalsIgnoreCase(situacaoAtual)) {
//
//            throw new LivroCaixaNaoPermiteAuditoriaException(
//                    String.format(
//                            "Não é possível solicitar auditoria. O Livro Caixa está com situação: %s",
//                            situacaoAtual
//                    )
//            );
//        }
//    }

    private void validarSemAuditoriaAtiva(Long livroCaixaId) {

        List<Auditoria> ativas =
                auditoriaRepository.findAuditoriasAtivasByLivroCaixaId(livroCaixaId);

        if (!ativas.isEmpty()) {
            throw new AuditoriaJaExistenteException(
                    "Já existe uma auditoria PENDENTE ou EM ANALISE para este Livro Caixa."
            );
        }
    }

    private void validarSemAuditoriaAprovada(Long livroCaixaId) {

        List<Auditoria> aprovadas =
                auditoriaRepository.findAuditoriasAprovadasByLivroCaixaId(livroCaixaId, Deliberacao.APROVADO);

        if (!aprovadas.isEmpty()) {
            throw new AuditoriaJaAprovadaException(
                    "Este Livro Caixa já possui uma auditoria concluída e APROVADA. " +
                            "Não é possível abrir uma nova auditoria."
            );
        }
    }
}
