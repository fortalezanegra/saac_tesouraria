package br.com.adbrascosmopolis.saac_tesouraria.a_process_management.fechamento_mensal.auditoria.solicitacao;

import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoPodeSolicitarAuditoriaException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria.SolicitacaoAuditoriaJaRealizadaException;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.reports.relatorio_mensal_fluxo_caixa.service.RelatorioMensalFluxoCaixaService;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitacaoAuditoriaService {

    private final RelatorioMensalFluxoCaixaService relatorioService;
    private final SituacaoRepository situacaoRepository;
    private final LivroCaixaRepository livroCaixaRepository;

    public SolicitacaoAuditoriaService(
            RelatorioMensalFluxoCaixaService relatorioService,
            SituacaoRepository situacaoRepository,
            LivroCaixaRepository livroCaixaRepository
    ){
        this.relatorioService = relatorioService;
        this.situacaoRepository = situacaoRepository;
        this.livroCaixaRepository = livroCaixaRepository;
    }

    //Solicitação de Auditoria
    @Transactional
    public byte[] solicitarAuditoria(Long livroCaixaId){

        LivroCaixa livro = livroCaixaRepository.findById(livroCaixaId)
                .orElseThrow(() -> new LivroCaixaNaoEncontradoException(
                        "O livro informado não é válido para solicitação de auditoria."
                ));

        if(livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.AGUARDANDO_AUDITORIA)){
            throw new SolicitacaoAuditoriaJaRealizadaException(
                    "O Livro Caixa: " + livro.getCentroCusto().getDescricao() + " - " + livro.getCompetencia() + " já se encontra AGUARDANDO_AUDITORIA"
            );
        }

        if(!livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.ABERTO)
                && !livro.getSituacao().getDescricao().equals(SituacaoLivrosConstantes.REABERTO)){
            throw new LivroCaixaNaoPodeSolicitarAuditoriaException(
                    "Somente Livros Caixa com situação igual a ABERTO ou REABERTO, podem solicitar auditoria. " +
                            "Livro Caixa: " + livro.getSituacao().getDescricao()
            );
        }

        // Agora é necessário informar o contexto, pois Situacao é compartilhada entre múltiplos domínios
        var situacao = situacaoRepository.buscarPorDescricaoEContexto(
                SituacaoLivrosConstantes.AGUARDANDO_AUDITORIA,
                SituacaoLivrosConstantes.CONTEXTO
        );
        livro.setSituacao(situacao);

        //Persistir alteração no banco de dados
        livroCaixaRepository.save(livro);

        // Gera o PDF do relatorio e retorna
        return relatorioService.gerarRelatorioFechamentoPdf(livro.getLivroCaixaId());
    }
}
