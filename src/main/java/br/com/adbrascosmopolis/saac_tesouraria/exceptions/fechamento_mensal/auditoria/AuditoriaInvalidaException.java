package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaInvalidaException extends BusinessException {
    public AuditoriaInvalidaException(String mensagem){
        super(
                "AUDITORIA_INVALIDA",
                mensagem,
                HttpStatus.NOT_FOUND
        );
    }
}
