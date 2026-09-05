package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaConcluidaException extends BusinessException {
    public AuditoriaConcluidaException(String mensagem){
        super(
            "AUDITORIA_CONCLUIDA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
