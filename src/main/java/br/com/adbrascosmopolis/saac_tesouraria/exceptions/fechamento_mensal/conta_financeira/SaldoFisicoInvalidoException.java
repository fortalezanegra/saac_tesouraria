package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.conta_financeira;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class SaldoFisicoInvalidoException extends BusinessException {
    public SaldoFisicoInvalidoException(String mensagem){
        super(
                "SALDO_FISICO_INVALIDO",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
