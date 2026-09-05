package br.com.adbrascosmopolis.saac_tesouraria.services.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.dto.conta_financeira.ContaFinanceiraSaldoDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.ContaFinanceira;
import br.com.adbrascosmopolis.saac_tesouraria.repository.ContaFinanceiraRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.MovimentoFinanceiroRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.projection.ContaFinanceiraSaldoProjection;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class ContaFinanceiraSaldoService {

    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final MovimentoFinanceiroRepository movimentoFinanceiroRepository;

    public ContaFinanceiraSaldoService(
            ContaFinanceiraRepository contaFinanceiraRepository,
            MovimentoFinanceiroRepository movimentoFinanceiroRepository
    ){
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
    }

//    public ContaFinanceiraSaldoDTO calcularSaldo(Long contaFinanceiraId) {
//
//        ContaFinanceira conta = contaFinanceiraRepository
//                .findById(contaFinanceiraId)
//                .orElseThrow(
//                        () -> new RuntimeException(
//                                "Conta financeira não encontrada."
//                        )
//                );
//
//        ContaFinanceiraSaldoProjection saldo =
//                movimentoFinanceiroRepository
//                        .calcularSaldo(contaFinanceiraId);
//
//        BigDecimal saldoInicial =
//                conta.getSaldoInicial();
//
//        BigDecimal totalEntradas =
//                saldo.getTotalEntradas();
//
//        BigDecimal totalSaidas =
//                saldo.getTotalSaidas();
//
//        BigDecimal saldoAtual =
//                saldoInicial
//                        .add(totalEntradas)
//                        .subtract(totalSaidas);
//
//        return new ContaFinanceiraSaldoDTO(
//                conta.getContaFinanceiraId(),
//                conta.getDescricao(),
//                saldoInicial,
//                totalEntradas,
//                totalSaidas,
//                saldoAtual
//        );
//    }

}
