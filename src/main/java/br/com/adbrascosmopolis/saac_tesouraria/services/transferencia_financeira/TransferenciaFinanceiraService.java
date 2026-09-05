package br.com.adbrascosmopolis.saac_tesouraria.services.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoTransferenciaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.models.Categoria;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.models.LivroCaixa;
import br.com.adbrascosmopolis.saac_tesouraria.models.MovimentoFinanceiro;
import br.com.adbrascosmopolis.saac_tesouraria.models.Situacao;
import br.com.adbrascosmopolis.saac_tesouraria.models.TransferenciaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.repository.*;
import br.com.adbrascosmopolis.saac_tesouraria.services.MovimentoFinanceiroService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaFinanceiraService {

    private final TransferenciaFinanceiraRepository transferenciaFinanceiraRepository;
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final LivroCaixaRepository livroCaixaRepository;
    private final CategoriaRepository categoriaRepository;
    private final MovimentoFinanceiroService movimentoService;
    private final TransferenciaFinanceiraValidacaoService validacaoService;
    private final SituacaoRepository situacaoRepository;

    public TransferenciaFinanceiraService(
            TransferenciaFinanceiraRepository transferenciaFinanceiraRepository,
            ContaFinanceiraRepository contaFinanceiraRepository,
            LivroCaixaRepository livroCaixaRepository,
            CategoriaRepository categoriaRepository,
            MovimentoFinanceiroService movimentoService,
            TransferenciaFinanceiraValidacaoService validacaoService,
            SituacaoRepository situacaoRepository
    ) {

        this.transferenciaFinanceiraRepository = transferenciaFinanceiraRepository;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.livroCaixaRepository = livroCaixaRepository;
        this.categoriaRepository = categoriaRepository;
        this.movimentoService = movimentoService;
        this.validacaoService = validacaoService;
        this.situacaoRepository = situacaoRepository;
    }

    //Listar Todos
    public List<TransferenciaFinanceiraResponseDTO> listarTodos(){
        List<TransferenciaFinanceiraResponseDTO> response = transferenciaFinanceiraRepository.listarTodos();
        return response;
    }

    @Transactional
    public TransferenciaFinanceiraResponseDTO create(TransferenciaFinanceiraRequestDTO request) {

        ContaFinanceira contaOrigem =
                contaFinanceiraRepository.findByContaFinanceiraId(
                        request.getContaFinanceiraOrigemId()
                );

        ContaFinanceira contaDestino =
                contaFinanceiraRepository.findByContaFinanceiraId(
                        request.getContaFinanceiraDestinoId()
                );

        LivroCaixa livroOrigem =
                livroCaixaRepository.findByLivroCaixaId(
                        request.getLivroCaixaOrigemId()
                );

        LivroCaixa livroDestino =
                livroCaixaRepository.findByLivroCaixaId(
                        request.getLivroCaixaDestinoId()
                );

        Categoria categoria =
                categoriaRepository.findByCategoriaId(
                        request.getCategoriaId()
                );

//        validacaoService.validarTransferencia(
//                request,
//                contaOrigem,
//                contaDestino,
//                livroOrigem,
//                livroDestino,
//                categoria
//        );

        Situacao situacaoPendente = situacaoRepository.findByDescricao(SituacaoTransferenciaConstantes.PENDENTE);

        TransferenciaFinanceira transferencia = new TransferenciaFinanceira();

        transferencia.setDescricao(request.getDescricao());
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setContaDestino(contaDestino);

        //transferencia.setLivroOrigem(livroOrigem);
        //transferencia.setLivroDestino(livroDestino);

        transferencia.setValor(request.getValor());
        transferencia.setDataTransferencia(request.getDataTransferencia());
        transferencia.setSituacao(situacaoPendente);

        transferencia = transferenciaFinanceiraRepository.save(transferencia);

        MovimentoFinanceiro movimentoSaida =
                criarMovimentoSaida(
                        request,
                        livroOrigem,
                        contaOrigem,
                        categoria,
                        transferencia
                );

        MovimentoFinanceiro movimentoEntrada =
                criarMovimentoEntrada(
                        request,
                        livroDestino,
                        contaDestino,
                        categoria,
                        transferencia
                );

        gerarMovimentacoes(movimentoSaida, movimentoEntrada);

        Situacao situacaoEfetivada = situacaoRepository.findByDescricao(SituacaoTransferenciaConstantes.EFETIVADA);

        transferencia.setSituacao(situacaoEfetivada);

        transferencia = transferenciaFinanceiraRepository.save(transferencia);

        return TransferenciaFinanceiraResponseDTO.fromTransferenciaFinanceira(transferencia);
    }

    private void gerarMovimentacoes(MovimentoFinanceiro saida, MovimentoFinanceiro entrada){
        movimentoService.createTransference(saida);
        movimentoService.createTransference(entrada);
    }

    private MovimentoFinanceiro criarMovimentoSaida(
            TransferenciaFinanceiraRequestDTO request,
            LivroCaixa livro,
            ContaFinanceira conta,
            Categoria categoria,
            TransferenciaFinanceira transferencia
    ) {

        MovimentoFinanceiro movimento = new MovimentoFinanceiro();

        movimento.setLivroCaixa(livro);
        movimento.setContaFinanceira(conta);
        movimento.setCategoria(categoria);
        movimento.setValor(request.getValor());
        movimento.setDataMovimento(LocalDate.now());
        movimento.setHistorico(request.getDescricao());

        return movimento;
    }

    private MovimentoFinanceiro criarMovimentoEntrada(
            TransferenciaFinanceiraRequestDTO request,
            LivroCaixa livro,
            ContaFinanceira conta,
            Categoria categoria,
            TransferenciaFinanceira transferencia
    ) {

        MovimentoFinanceiro movimento = new MovimentoFinanceiro();

        movimento.setLivroCaixa(livro);
        movimento.setContaFinanceira(conta);
        movimento.setCategoria(categoria);
        movimento.setValor(request.getValor());
        movimento.setDataMovimento(LocalDate.now());
        movimento.setHistorico(request.getDescricao());

        return movimento;
    }
}