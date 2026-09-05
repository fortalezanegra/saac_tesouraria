package br.com.adbrascosmopolis.saac_tesouraria.exceptions.transferencia_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class SaldoInsuficienteException extends BusinessException {

    public SaldoInsuficienteException(
            BigDecimal saldoDisponivel,
            BigDecimal valorTransferencia
    ) {

        super(
                "SALDO_INSUFICIENTE",
                String.format(
                        "Saldo insuficiente para realizar a transferência. " +
                                "Saldo disponível: %s | Valor solicitado: %s",
                        formatarMoeda(saldoDisponivel),
                        formatarMoeda(valorTransferencia)
                ),
                HttpStatus.BAD_REQUEST
        );
    }


    private static String formatarMoeda(BigDecimal valor){

        if(valor == null){
            return "R$ 0,00";
        }

        return NumberFormat
                .getCurrencyInstance(new Locale("pt", "BR"))
                .format(valor);
    }
}