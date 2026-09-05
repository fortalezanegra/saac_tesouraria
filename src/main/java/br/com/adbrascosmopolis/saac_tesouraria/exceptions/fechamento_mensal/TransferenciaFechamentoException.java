package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class TransferenciaFechamentoException extends BusinessException {
    public TransferenciaFechamentoException(String mensagem){
        super(
            "TRASFERENCIA_FECHAMENTO_EXCEPTION",
            mensagem,
            HttpStatus.BAD_REQUEST
        );
    }
}
