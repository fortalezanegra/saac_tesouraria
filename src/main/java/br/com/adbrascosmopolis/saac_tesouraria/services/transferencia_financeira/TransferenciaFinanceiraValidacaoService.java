package br.com.adbrascosmopolis.saac_tesouraria.services.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraSaldoDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.transferencia_financeira.TransferenciaFinanceiraRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoEntidadesConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoLivrosConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.SituacaoTransferenciaConstantes;
import br.com.adbrascosmopolis.saac_tesouraria.enumeration.TipoCategoria;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaFechadoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaInvalidoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.livro_caixa.LivroCaixaNaoEncontradoException;
import br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira.*;
import br.com.adbrascosmopolis.saac_tesouraria.models.*;
import br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira.ContaFinanceiraSaldoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciaFinanceiraValidacaoService {

    private final ContaFinanceiraSaldoService saldoService;

    public TransferenciaFinanceiraValidacaoService(
            ContaFinanceiraSaldoService saldoService
    ){
        this.saldoService = saldoService;
    }

//    public void validarTransferencia(
//            TransferenciaFinanceiraRequestDTO request,
//            ContaFinanceira contaOrigem,
//            ContaFinanceira contaDestino,
//            LivroCaixa livroOrigem,
//            LivroCaixa livroDestino,
//            Categoria categoria
//    ){
//
//        validarValor( request.getValor() );
//
//        validarContasFinanceiras(contaOrigem, contaDestino);
//
//        validarLivrosCaixa(livroOrigem, livroDestino);
//
//        validarCentroCusto(
//                contaOrigem,
//                contaDestino,
//                livroOrigem,
//                livroDestino
//        );
//
//        validarCategoria(categoria);
//
//        validarSaldo(contaOrigem, request.getValor());
//    }

    public void validarEstorno(
            TransferenciaFinanceira transferencia,
            LivroCaixa livroOrigem,
            LivroCaixa livroDestino
    ) {

        validarSituacaoTransferencia( transferencia );

        validarLivrosParaEstorno( livroOrigem, livroDestino );
    }

    private void validarContasFinanceiras(
            ContaFinanceira origem,
            ContaFinanceira destino
    ) {

        if(origem == null){
            throw new ContaFinanceiraNaoEncontradaException(
                    "Conta financeira de origem não encontada."
            );
        }

        if(destino == null){
            throw new ContaFinanceiraNaoEncontradaException(
                    "Conta financeira de destino não encontrada."
            );
        }

        if(origem.getContaFinanceiraId().equals(destino.getContaFinanceiraId())) {

            throw new ContaFinanceiraInvalidaException(
                    "A conta financeira de origem deve ser diferente da conta financeira de destino."
            );
        }

        if(origem.getSituacao() == null ||
                !SituacaoEntidadesConstantes.ATIVO.equals(origem.getSituacao().getDescricao())){

            throw new ContaFinanceiraInvalidaException(
                    "A conta financeira de origem está inativa."
            );
        }

        if(destino.getSituacao() == null ||
                !SituacaoEntidadesConstantes.ATIVO.equals(destino.getSituacao().getDescricao())){

            throw new ContaFinanceiraInvalidaException(
                    "A conta financeira de destino está inativa."
            );
        }
    }

    private void validarLivrosCaixa(
            LivroCaixa origem,
            LivroCaixa destino
    ) {
        if(origem == null){
            throw new LivroCaixaNaoEncontradoException(
                    "Livro Caixa de origem não encontado."
            );
        }

        if(destino == null){
            throw new LivroCaixaNaoEncontradoException(
                    "Livro Caixa de destino não encontado."
            );
        }

        if(origem.getLivroCaixaId().equals(destino.getLivroCaixaId())) {

            throw new LivroCaixaInvalidoException(
                    "A livro caixa de origem deve ser diferente do livro de destino.\n" +
                            "ID Livro Caixa de Origem : " + origem.getLivroCaixaId() + "\n" +
                            "ID Livro Caixa de Destino: " + destino.getLivroCaixaId()
            );
        }

        if(!SituacaoLivrosConstantes.ABERTO.equals(origem.getSituacao().getDescricao())){

            throw new LivroCaixaInvalidoException(
                    "O livro caixa de origem está inativo: " + origem.getLivroCaixaId()
            );
        }

        if(!SituacaoLivrosConstantes.ABERTO.equals(destino.getSituacao().getDescricao())){

            throw new LivroCaixaInvalidoException(
                    "O livro caixa de destino está inativo: " + destino.getLivroCaixaId()
            );
        }

    }

    private void validarCentroCusto(
            ContaFinanceira contaOrigem,
            ContaFinanceira contaDestino,
            LivroCaixa livroOrigem,
            LivroCaixa livroDestino
    ) {

        if (!contaOrigem.getCentroCusto().getCentroCustoId()
                .equals(livroOrigem.getCentroCusto().getCentroCustoId())) {

            throw new ContaFinanceiraELivroCaixaIncompativeisException(

                    String.format(
                            "O centro de custo da conta financeira de origem '%s' é diferente do centro de custo do livro caixa de origem '%s'.",
                            contaOrigem.getCentroCusto().getDescricao(),
                            livroOrigem.getCentroCusto().getDescricao()
                    )
            );
        }

        if (!contaDestino.getCentroCusto().getCentroCustoId()
                .equals(livroDestino.getCentroCusto().getCentroCustoId())) {

            throw new ContaFinanceiraELivroCaixaIncompativeisException(

                    String.format(
                            "O centro de custo da conta financeira de destino '%s' é diferente do centro de custo do livro caixa de destino '%s'.",
                            contaDestino.getCentroCusto().getDescricao(),
                            livroDestino.getCentroCusto().getDescricao()
                    )
            );




        }
    }

    private void validarValor(BigDecimal valor) {
        if(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new ValorTransferenciaInvalidoException(
                    "O valor da transferência deve ser maior que zero."
            );
        }
    }

//    private void validarSaldo(ContaFinanceira contaOrigem, BigDecimal valor){
//
//        ContaFinanceiraSaldoDTO dto =
//                saldoService.calcularSaldo(
//                        contaOrigem.getContaFinanceiraId()
//                );
//
//        BigDecimal saldoDisponivel =
//                dto != null && dto.getSaldoAtual() != null
//                        ? dto.getSaldoAtual()
//                        : BigDecimal.ZERO;
//
//        if(valor.compareTo(saldoDisponivel) > 0){
//
//            throw new SaldoInsuficienteException(
//                    saldoDisponivel,
//                    valor
//            );
//        }
//    }

    private void validarCategoria(Categoria categoria) {

        if(categoria == null || categoria.getDescricao() == null){

            throw new CategoriaInvalidaException(
                    "A categoria da transferência informada não foi encontrada."
            );
        }

    }

    private void validarSituacaoTransferencia(TransferenciaFinanceira transferencia) {

        if (transferencia == null) {

            throw new TransferenciaNaoPodeSerEstornadaException(
                    "A transferência informada não foi encontrada."
            );
        }

        if (transferencia.getSituacao() == null) {

            throw new TransferenciaNaoPodeSerEstornadaException(
                    "A transferência não possui situação definida."
            );
        }

        String situacaoAtual = transferencia.getSituacao().getDescricao();

        if (!SituacaoTransferenciaConstantes.EFETIVADA.equalsIgnoreCase(situacaoAtual)) {

            throw new TransferenciaNaoPodeSerEstornadaException(
                    String.format(
                            "A transferência não pode ser estornada. Situação atual: %s",
                            situacaoAtual
                    )
            );
        }
    }

    private void validarLivrosParaEstorno(LivroCaixa origem, LivroCaixa destino) {

        if (origem == null) {

            throw new LivroCaixaNaoEncontradoException(
                    "Livro Caixa de origem não encontrado."
            );
        }

        if (destino == null) {

            throw new LivroCaixaNaoEncontradoException(
                    "Livro Caixa de destino não encontrado."
            );
        }

        if (origem.getSituacao() == null ||
                !SituacaoLivrosConstantes.ABERTO.equalsIgnoreCase(
                        origem.getSituacao().getDescricao())) {

            throw new LivroCaixaFechadoException(
                    "O Livro Caixa de origem não permite estorno, pois está fechado."
            );
        }

        if (destino.getSituacao() == null ||
                !SituacaoLivrosConstantes.ABERTO.equalsIgnoreCase(
                        destino.getSituacao().getDescricao())) {

            throw new LivroCaixaFechadoException(
                    "O Livro Caixa de destino não permite estorno, pois está fechado."
            );
        }
    }

}
