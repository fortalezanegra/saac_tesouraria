package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.validacoes_fechamento;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.Deliberacao;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoAuditoriaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.*;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoPodeSolicitarAuditoriaException;
import br.com.adbrascosmopolis.saac_tesouraria.models.Auditoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.CentroCusto;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.repository.AuditoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class FechamentoMensalValidacaoService {

    private final LivroCaixaRepository livroCaixaRepository;
    private final AuditoriaRepository auditoriaRepository;

    public FechamentoMensalValidacaoService(
            LivroCaixaRepository livroCaixaRepository,
            AuditoriaRepository auditoriaRepository
    ){
        this.livroCaixaRepository = livroCaixaRepository;
        this.auditoriaRepository = auditoriaRepository;
    }

    public void validarSolicitacaoAuditoria(LivroCaixa livro){

        if(livro == null){
            throw new LivroCaixaNaoEncontradoException("O Livro Caixa informado não foi encontrado.");
        }

        if(
                !livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.ABERTO)
                && !livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.REABERTO)
        ){
            throw new LivroCaixaNaoPodeSolicitarAuditoriaException(
                    "O Livro Caixa informado não possui situação iqual a ABERTO ou REABERTO. A Solicitação" +
                    " de auditoria não pode ser realizada: " +
                            livro.getCentroCusto().getDescricao() + " - " + livro.getCompetencia()
            );
        }
    }

    public void validarInicializacaoAuditoria(LivroCaixa livro){
        if(livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.AGUARDANDO_AUDITORIA)){
            throw new LivroCaixaNaoPodeSolicitarAuditoriaException(
                    "A auditoria do Livro Caixa não pode ser inicializada, pois não apresenta a" +
                            " situação AGUARDANDO_AUDITORIA: " +
                            livro.getCentroCusto().getDescricao() + " - " + livro.getCompetencia()
            );
        }
    }

    public void validarAuditoriaExistente(LivroCaixa livro) {

        var auditoria = auditoriaRepository.findByLivroCaixaLivroCaixaId(livro.getLivroCaixaId());

        if(auditoria == null){
            throw new AuditoriaNaoEncontradaException(
                    "Não foi encontrada nenhuma auditoria para o Livro Caixa: " +
                            livro.getCentroCusto().getDescricao() + " - " +
                            livro.getCompetencia()
            );
        }

        String situacao = auditoria.getSituacao().getDescricao();

        if (situacao.equals(SituacaoAuditoriaConstantes.PENDENTE)) {
            throw new AuditoriaAtivaException(
                    "Já existe uma auditoria PENDENTE para este Livro Caixa: " +
                            "auditoriaId: " + auditoria.getAuditoriaId() + "Livro Caixa:" +
                            livro.getCentroCusto().getDescricao() + " - " +
                            livro.getCompetencia()
            );
        } else if (situacao.equals(SituacaoAuditoriaConstantes.EM_ANALISE)) {

            throw new AuditoriaAtivaException(
                    "Já existe uma auditoria EM_ANALISE para este Livro Caixa: " +
                            "auditoriaId: " + auditoria.getAuditoriaId() + "Livro Caixa:" +
                            livro.getCentroCusto().getDescricao() + " - " + livro.getCompetencia() +
                            " - Auditor: " + auditoria.getAuditor().getNome()
            );
        } else if (
                situacao.equals(SituacaoAuditoriaConstantes.CONCLUIDA)
                        && auditoria.getDeliberacao().equals(Deliberacao.APROVADO)
        ) {
            throw new AuditoriaConcluidaException(
                    "Já existe uma auditoria CONCLUIDA e APROVADA para este Livro Caixa: " +
                            livro.getCentroCusto().getDescricao() + " - " + livro.getCompetencia() +
                            " - Auditor: " + auditoria.getAuditor().getNome()
            );
        }
    }

    public void validarAssumirAuditoria(Auditoria auditoria){

        if(!auditoria.getSituacao().getDescricao().equals(SituacaoAuditoriaConstantes.PENDENTE)){
            throw new AuditoriaNaoPodeSerAssumidaException(
                    "Não é possível assumir uma auditoria com  situacao diferente de PENDENTE. " +
                            "A auditoria informada está com situação igual a " +
                            auditoria.getSituacao().getDescricao()
            );
        }
    }

    public void validarAuditoriaParaConclusao(Auditoria auditoria){

        if(!auditoria.getSituacao().getDescricao().equals(SituacaoAuditoriaConstantes.EM_ANALISE)){
            throw new AuditoriaNaoPodeSerConcluidaException(
                    "Não é possível concluir uma auditoria com situacao diferente de EM_ANALISE."
            );
        }
    }

    public void validarAprovacaoLivroCaixa(LivroCaixa livro){

        var auditoria = auditoriaRepository.findByLivroCaixaLivroCaixaId(livro.getLivroCaixaId());

        if(auditoria == null){
            throw new AuditoriaNaoEncontradaException(
                    "Não foi encontrada nenhuma auditoria EM_ANALISE para o Livro Caixa: " +
                            livro.getCentroCusto().getDescricao() + " - " +
                            livro.getCompetencia()
            );
        }

        if(auditoria.getAuditor() == null){
            throw new AuditoriaValidacaoCamposException(
                    "O campo auditorId não foi preenchido."
            );
        } else if(auditoria.getDataInicio() == null){
            throw new AuditoriaValidacaoCamposException(
                    "O campo dataInicio não foi preenchido."
            );
        } else if(auditoria.getObservacoes() == null || auditoria.getObservacoes().isEmpty()){
            throw new AuditoriaValidacaoCamposException(
                    "O campo observações não foi preenchido."
            );
        } else if(!auditoria.getDeliberacao().equals(Deliberacao.APROVADO)){
            throw new AuditoriaValidacaoCamposException(
                    "O campo deliberação deve ser APROVADO."
            );
        } else if(!auditoria.getSituacao().getDescricao().equals(SituacaoAuditoriaConstantes.EM_ANALISE)){
            throw new AuditoriaValidacaoCamposException(
                    "O campo situação não está marcado como EM_ANALISE."
            );
        }
    }

    public void validarReaberturaLivroCaixa(LivroCaixa livro){

        var auditoria = auditoriaRepository.findByLivroCaixaLivroCaixaId(livro.getLivroCaixaId());

        if(auditoria == null){
            throw new AuditoriaNaoEncontradaException(
                    "Não foi encontrada nenhuma auditoria EM_ANALISE para o Livro Caixa: " +
                            livro.getCentroCusto().getDescricao() + " - " +
                            livro.getCompetencia()
            );
        }

        if(auditoria.getAuditor() == null){
            throw new AuditoriaValidacaoCamposException(
                    "O campo auditorId não foi preenchido."
            );
        } else if(auditoria.getDataInicio() == null){
            throw new AuditoriaValidacaoCamposException(
                    "O campo dataInicio não foi preenchido."
            );
        } else if(auditoria.getObservacoes() == null || auditoria.getObservacoes().isEmpty()){
            throw new AuditoriaValidacaoCamposException(
                    "O campo observações não foi preenchido."
            );
        } else if(auditoria.getDeliberacao() != Deliberacao.REPROVADO){
            throw new AuditoriaValidacaoCamposException(
                    "O campo deliberação deve ser REPROVADO."
            );
        } else if(!auditoria.getSituacao().getDescricao().equals(SituacaoAuditoriaConstantes.EM_ANALISE)){
            throw new AuditoriaValidacaoCamposException(
                    "O campo situação não está marcado como EM_ANALISE."
            );
        }
    }

    public void validarFechamentoMensal(List<LivroCaixa> livros){


    }

    public void validarTransferenciaFechamento(
            BigDecimal saldoFinal,
            BigDecimal valoresEletronicos
    ){

    }

    public void validarCriacaoNovaCompetencia(
            CentroCusto centro,
            LocalDate competencia
    ){

    }
}
