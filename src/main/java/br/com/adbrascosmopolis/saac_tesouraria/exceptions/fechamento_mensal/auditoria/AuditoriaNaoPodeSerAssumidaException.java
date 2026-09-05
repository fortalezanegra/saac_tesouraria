package br.com.adbrascosmopolis.saac_tesouraria.exceptions.fechamento_mensal.auditoria;

import br.com.adbrascosmopolis.saac_tesouraria.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class AuditoriaNaoPodeSerAssumidaException extends BusinessException {
    public AuditoriaNaoPodeSerAssumidaException(String mensagem){
        super(
            "AUDITORIA_NAO_PODE_SER_ASSUMIDA",
                mensagem,
                HttpStatus.BAD_REQUEST
        );
    }
}
