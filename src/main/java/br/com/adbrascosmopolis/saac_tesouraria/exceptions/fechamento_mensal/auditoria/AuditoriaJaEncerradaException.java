package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaJaEncerradaException extends BusinessException {
    public AuditoriaJaEncerradaException(String message) {
        super(
                "AUDITORIA_JA_ENCERRADA",
                message,
                HttpStatus.BAD_REQUEST
        );
    }
}
