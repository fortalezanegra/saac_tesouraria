package br.com.adbrascosmopolis.saac_tesouraria.services.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoTransferenciaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoMovimento;
import br.com.adbrascosmopolis.saac_tesouraria.models.*;
import br.com.adbrascosmopolis.saac_tesouraria.repository.CategoriaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.ContaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.LivroCaixaRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.TransferenciaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.services.MovimentoFinanceiroService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaFinanceiraService {

    private final TransferenciaFinanceiraRepository transferenciaFinanceiraRepository;
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final CategoriaRepository categoriaRepository;
    private final MovimentoFinanceiroService movimentoService;

    private final TransferenciaFinanceiraValidacaoService transferenciaFinanceiraValidacaoService;

    public TransferenciaFinanceiraService(
            TransferenciaFinanceiraRepository transferenciaFinanceiraRepository,
            ContaFinanceiraRepository contaFinanceiraRepository,
            LivroCaixaRepository livroCaixaRepository,
            CategoriaRepository categoriaRepository,
            MovimentoFinanceiroService movimentoService,
            TransferenciaFinanceiraValidacaoService transferenciaFinanceiraValidacaoService
    ){
        this.transferenciaFinanceiraRepository = transferenciaFinanceiraRepository;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.categoriaRepository = categoriaRepository;
        this.movimentoService = movimentoService;
        this.transferenciaFinanceiraValidacaoService = transferenciaFinanceiraValidacaoService;
    }

    @Transactional
    public TransferenciaFinanceiraResponseDTO create(TransferenciaFinanceiraRequestDTO request){

        ContaFinanceira contaOrigem = contaFinanceiraRepository.findByContaFinanceiraId(request.getContaFinanceiraOrigemId());
        ContaFinanceira contaDestino = contaFinanceiraRepository.findByContaFinanceiraId(request.getContaFinanceiraDestinoId());

        LivroCaixa livroOrigem = livroCaixaRepository.findByLivroCaixaId(request.getLivroCaixaOrigemId());
        LivroCaixa livroDestino = livroCaixaRepository.findByLivroCaixaId(request.getLivroCaixaDestinoId());

        Categoria categoria = categoriaRepository.findByCategoriaId(request.getCategoriaId());

        transferenciaFinanceiraValidacaoService.validarTransferencia(
                request,
                contaOrigem,
                contaDestino,
                livroOrigem,
                livroDestino,
                categoria
        );

        Situacao situacao = new Situacao();
        situacao.setDescricao(SituacaoTransferenciaConstantes.EFETIVADA);

        TransferenciaFinanceira transferencia = new TransferenciaFinanceira();

        transferencia.setDescricao(request.getDescricao());
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setContaDestino(contaDestino);
        transferencia.setValor(request.getValor());
        transferencia.setDataTransferencia(request.getDataTransferencia());
        //transferencia.setObservacao(request.getObservacao());
        transferencia.setSituacao(situacao);

        transferencia = transferenciaFinanceiraRepository.save(transferencia);

        MovimentoFinanceiro movimentoEntrada = new MovimentoFinanceiro();
        MovimentoFinanceiro movimentoSaida   = new MovimentoFinanceiro();

        //Movimento Financeiro de Saida
        movimentoSaida.setLivroCaixa(livroOrigem);
        movimentoSaida.setContaFinanceira(contaOrigem);
        movimentoSaida.setCategoria(categoria);
        movimentoSaida.setTipoMovimento(TipoMovimento.SAIDA);
        movimentoSaida.setValor(request.getValor());
        movimentoSaida.setDataMovimento(request.getDataTransferencia());
        movimentoSaida.setHistorico(request.getDescricao());

        //Movimento Financeiro de Entrada
        movimentoEntrada.setLivroCaixa(livroDestino);
        movimentoEntrada.setContaFinanceira(contaDestino);
        movimentoEntrada.setCategoria(categoria);
        movimentoEntrada.setTipoMovimento(TipoMovimento.ENTRADA);
        movimentoEntrada.setValor(request.getValor());
        movimentoEntrada.setDataMovimento(request.getDataTransferencia());
        movimentoEntrada.setHistorico(request.getDescricao());

        gerarMovimentacoes(movimentoSaida, movimentoEntrada);

        return TransferenciaFinanceiraResponseDTO.fromTransferenciaFinanceira(transferencia);
    }


    private void gerarMovimentacoes(
            MovimentoFinanceiro movimentoFinanceiroSaida,
            MovimentoFinanceiro movimentoFinanceiroEntrada
    ){
        movimentoService.createTransference(movimentoFinanceiroSaida);
        movimentoService.createTransference(movimentoFinanceiroEntrada);
    }


}
